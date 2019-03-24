from .db import get_connection
from bson import ObjectId


class Modelo():
    """docstring for Modelo."""
    def __init__(self, nome, data_nascimento, email, telefone, endereco, genero, escolaridade, ocupacao, moradia, \
                 moradores, filhos, etnia, renda):
        self.nome = nome
        self.data_nascimento = data_nascimento
        self.email = email
        self.telefone = telefone
        self.endereco = endereco
        self.genero = genero
        self.escolaridade = escolaridade
        self.ocupacao = ocupacao
        self.moradia = moradia
        self.moradores = moradores
        self.filhos = filhos
        self.etnia = etnia
        self.renda = renda

class Cadastro(Modelo):
  collection = get_connection()['modelos']

  def __init__(self):
    super().__init__()

  @staticmethod
  def init_with_document(document):
    _id = document.get('_id', None)
    nome = document.get('nome', None)
    data_nascimento = document.get('data_nascimento', None)
    email = document.get('email', None)
    telefone = document.get('telefone', None)
    endereco = document.get('endereco', None)
    genero = document.get('genero', None)
    escolaridade = document.get('escolaridade', None)
    ocupacao = document.get('ocupacao', None)
    moradia = document.get('moradia', None)
    moradores = document.get('moradores', None)
    filhos = document.get('filhos', None)
    etnia = document.get('etnia', None)
    renda = document.get('renda', None)
    result = Cadastro(nome=nome, data_nascimento=data_nascimento, email=email, telefone=telefone, endereco=endereco,\
                    genero=genero, escolaridade=escolaridade, ocupacao=ocupacao, moradia=moradia, moradores=moradores,\
                    filhos=filhos, etnia=etnia, renda=renda)
    result._id = _id
    return result
