from db import get_connection
import json
from bson.json_util import default
from modelo import Modelo

def create_function(requestData):
    novo_modelo = criar_modelo(requestData)

    collection = 'modelos'
    connection = get_connection()[collection]

    resultado = connection.insert_one(novo_modelo)

    return resultado.inserted_id, json.dumps(novo_modelo, default=default)

def criar_modelo(requestData):

    nome = requestData.get('nome', None)
    data_nascimento = requestData.get('data_nascimento', None)
    email = requestData.get('email', None)
    telefone = requestData.get('telefone', None)
    endereco_logradouro = requestData.get('endereco_logradouro', None)
    endereco_numero = requestData.get('endereco_numero', None)
    endereco_complemento = requestData.get('endereco_complemento', None)
    endereco_bairro = requestData.get('endereco_bairro', None)
    endereco_cidade = requestData.get('endereco_cidade', None)
    endereco_estado = requestData.get('endereco_estado', None)
    endereco_cep = requestData.get('endereco_cep', None)
    responsavel_nome = requestData.get('responsavel_nome', None)
    responsavel_nascimento = requestData.get('responsavel_nascimento', None)
    responsavel_email = requestData.get('responsavel_email', None)
    responsavel_telefone = requestData.get('responsavel_telefone', None)
    genero = requestData.get('genero', None)
    lgbtqia = requestData.get('lgbtqia', None)
    escolaridade = requestData.get('escolaridade', None)
    ocupacao = requestData.get('ocupacao', None)
    moradia = requestData.get('moradia', None)
    moradores = requestData.get('moradores', None)
    filhos = requestData.get('filhos', None)
    etnia = requestData.get('etnia', None)
    disponibilidade = requestData.get('disponibilidade',None)
    renda = requestData.get('renda', None)
    medida_altura = requestData.get('medida_altura', None)
    medida_busto = requestData.get('medida_busto', None)
    medida_cintura = requestData.get('medida_cintura', None)
    medida_quadril = requestData.get('medida_quadril', None)
    observacoes = requestData.get('observacoes', None)

    novo_modelo = Modelo(nome, data_nascimento, email, telefone, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, endereco_cidade, endereco_estado, endereco_cep, \
                         responsavel_nome, responsavel_nascimento, responsavel_email, responsavel_telefone, genero,
                         lgbtqia, escolaridade, ocupacao, moradia, moradores, filhos, etnia, disponibilidade, renda, \
                         medida_altura, medida_busto, medida_cintura, medida_quadril, observacoes)
    return novo_modelo
