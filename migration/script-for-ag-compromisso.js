const csv = require("csvtojson");
require('isomorphic-fetch');

const GENDER_EXPRESSION = {
  'Feminino': 'FEMALE',
  'Masculino': 'MALE',
  'Prefere não dizer': 'PREFER_NOT_TO_INFORM'
}

const EDUCATION = {
  'Sem escolaridade': 'NO_EDUCATION',
  'Ensino Fundamental Incompleto': 'INCOMPLETE_MIDDLE_SCHOOL',
  'Ensino Fundamental Completo': 'MIDDLE_SCHOOL',
  'Ensino Médio Incompleto': 'INCOMPLETE_HIGH_SCHOOL',
  'Ensino Médio Completo': 'HIGH_SCHOOL',
  'Ensino Técnico Incompleto': 'INCOMPLETE_TECHNICAL_SCHOOL',
  'Ensino Técnico Completo': 'TECHNICAL_SCHOOL',
  'Ensino Superior Incompleto': 'INCOMPLETE_HIGHER_EDUCATION',
  'Ensino Superior Completo': 'HIGHER_EDUCATION'
}

const ETHNICITY = {
  'Amarela': 'EAST_ASIANS',
  'Branca': 'WHITE',
  'Indígena': 'NATIVE',
  'Parda': 'PARDO',
  'Preta': 'BLACK',
  'Prefere não declarar': 'PREFER_NOT_TO_INFORM'
}

const HOUSING = {
  'Própria': 'OWN',
  'Alugada': 'RENTED',
}

const OCCUPATION_MODE = {
  'Fixa': 'FIXED',
  'Autônomo(Bico)': 'AUTONOMOUS'
}

const FAMILY_INCOME = {
  'Menos de 1 Salário Mínimo': 'LESS_THAN_ONE_MINIMUM_WAGE',
  '1 Salário Mínimo': 'ONE_MINIMUM_WAGE',
  '2 Salários Mínimos': 'TWO_MINIMUM_WAGE',
  '3 Salários Mínimos': 'THREE_MINIMUM_WAGE',
  'Mais de 3 Salários Mínimos': 'MORE_THAN_THREE_MINIMUM_WAGE'
}

function mapAvailability(availability) {
  const availabilityTranslation = {
    'manhã': 'MORNING',
    'livre': 'ALLDAY',
    'tarde': 'AFTERNOON',
    'dia livre': 'ALLDAY',
    'dia todo': 'ALLDAY'
  }

  if (new Set(Object.keys(availabilityTranslation)).has(availability)) {
    return availabilityTranslation[availability]
  }

  return undefined
}


function populateObservations(fitModel) {
  if (!fitModel.observations) {
    fitModel.observations = ''
  }

  // Availabiolity para campo de obs
  if (!mapAvailability(fitModel.availability.toLowerCase().trim())) {
    fitModel.observations += `Disponibilidade Antiga: ${fitModel.availability}\n`
  }
  // Testa se o campo não tem um número de telefone
  // Telefone é qualquer palavra que tenha um ou mais números seguidos de 0 ou mais espaços
  if (!(/([0-9]+\s?)+/).test(fitModel.phoneNumber.trim())) {
    fitModel.observations += `Telefone Antigo: ${fitModel.phoneNumber}\n`
    fitModel.phoneNumber = ''
  }

  if (!(/([0-9]+\s?)+/).test(fitModel.guardianPhoneNumber.trim())) {
    fitModel.observations += `Telefone Antigo do responsável: ${fitModel.guardianPhoneNumber}\n`
    fitModel.guardianPhoneNumber = ''
  }


  fitModel.observations += `Idade: ${fitModel.age}
Menor?: ${fitModel.isUnderage}`;

  return fitModel;
}

function mappingEnums(fitModel) {
  fitModel.availability = mapAvailability(fitModel.availability.toLowerCase().trim())
  fitModel.genderExpression = GENDER_EXPRESSION[fitModel.genderExpression]
  fitModel.education = EDUCATION[fitModel.education]
  fitModel.socialInformation.ethnicity = ETHNICITY[fitModel.socialInformation.ethnicity]
  fitModel.socialInformation.housing = HOUSING[fitModel.socialInformation.housing]
  fitModel.socialInformation.occupationMode = OCCUPATION_MODE[fitModel.socialInformation.occupationMode]
  fitModel.socialInformation.familyIncome = FAMILY_INCOME[fitModel.socialInformation.familyIncome]
}

function deleteNullorUndefinedField(fitModel) {
  const nullOfUndefinedValueKeys = Object.entries(fitModel)
    .filter(([ _, v ]) => v === '' || v === undefined || v === null || typeof v === 'object')
    .map(([k,_]) => k)

  for (let key of nullOfUndefinedValueKeys) {
    if (typeof fitModel[key] === 'object') {
      fitModel[key] = deleteNullorUndefinedField(fitModel[key])
    } else {
      delete fitModel[key]
    }
  }

  return fitModel
}

/* TODO: 
  Arrumar os telefones
  Remover os projetos do nome e colocar em projects
  Formartar os valores com o tipo certo (EX: Sizes deve ser double)
*/
const debug = false
const csvOut = csv({
  delimiter: ',',
  headers: [
    'timestamp', 'name', 'age',
    'isUnderage', 'availability', 
    'phoneNumber', 'address', 'genderExpression', 
    'education', 'guardianName', 'guardianPhoneNumber', 
    'socialInformation.ethnicity', 'socialInformation.housing', 
    'socialInformation.numberOfResidents', 'socialInformation.occupationMode', 
    'socialInformation.occupation', 'socialInformation.familyIncome',
    'familyIncomeObservations', 'hasChildren', 
    'socialInformation.numberOfChildren', 'sizes.totalBustCircumference', 
    'sizes.totalWaistCircumference', 'sizes.totalHipCircumference', 
    'sizes.height', 'observations'
  ]
}).fromFile('./data/Dados CEA erros.csv')
  .then((fitModels) => 
    fitModels.map(fitModel => {
      populateObservations(fitModel);
      mappingEnums(fitModel);
      delete fitModel.familyIncomeObservations;
      delete fitModel.hasChildren;
      delete fitModel.age;

      deleteNullorUndefinedField(fitModel)

      if (Object.keys(fitModel.socialInformation).length == 0) {
        delete fitModel.socialInformation
      } else {
        if (!("numberOfResidents" in fitModel.socialInformation)) {
          fitModel.socialInformation["numberOfResidents"] = 1
        }
      }

      return fitModel
    })
  )

const saveModel = (model) => {
  const data = JSON.stringify(model)
  
  return fetch('https://agencia-compromisso-api.herokuapp.com/fit-model', {
    method: 'post',
    body: data,
    headers: {
      'Content-Type': 'application/json'
    }
  })
  .then(res => res.text())
}

const waitOneXSecondsAnd = (x) => 
  new Promise((resolve, reject) => {
    setTimeout(() => resolve(), 1000 * x)
  })

let c = 1
let a = 1

let modelsWithError = []

if (debug) {
  csvOut.then(x => {
    console.log(new Set(x
      .filter(a => a.socialInformation)
      .map(f => f.socialInformation)
      .filter(b => b.numberOfResidents)
      .map(c => c.numberOfResidents)))
  })
} else {
  console.log('Migrando dados...')
  csvOut
    .then(fitModels => fitModels.map(
      m => {
        a += 1
        return waitOneXSecondsAnd(a).then(() => { 
        console.log(`${c} out of ${fitModels.length}`)
        c += 1
        return saveModel(m).then(r => {
          if (r.error) {
            modelsWithError.push(m)
          }

          return r
        })
      })}
    ))
    .then(req => Promise.all(req))
    .then(res => console.log(res))
    // .then(responses => responses.filter(res => res.errors.length > 0))
    .then(errors => console.log('Modelos não salvos', modelsWithError.length, errors))
    .then(() => require('fs').writeFileSync('data/errors.txt', JSON.stringify({data: modelsWithError})))
    .catch(err => console.error(err))
}




