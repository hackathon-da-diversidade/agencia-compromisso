import json
from bson.json_util import default
from bson.objectid import ObjectId
from src.db import get_connection


def list_all():
    collection = 'modelos'
    connection = get_connection()[collection]

    lista_modelos = []

    for modelo in connection.find():
        lista_modelos.append({'_id' : modelo['_id'], 'nome' : modelo['nome']})
    return json.dumps(lista_modelos, default=default)


def get_model_by_id(id):
    collection = 'modelos'
    connection = get_connection()[collection]
    modelo = connection.find_one({"_id": ObjectId(id)})
    if modelo:
        return json.dumps(modelo, default=default)

    return None

# def list_filter(arg1=None, arg2=None):
#  database = get_connection()
#  modelos = database["modelos"]
#  lista_modelos = []
#  for modelo in modelos.find({"string_teste":arg1, "age":arg2}):
#      lista_modelos.append(modelo)
#   return json.dumps(lista_modelos, default=default)

# def list_filter2(filtro):
#   database = get_connection()
#   modelos = database["modelos"]
#   lista_modelos = []
#   for modelo in modelos.find(filtro):
#       lista_modelos.append(modelo)
#   return json.dumps(lista_modelos, default=default)

# def list_male():
#   database = get_connection()
#   modelos = database["modelos"]
#   lista_modelos = []
#   for modelo in modelos.find({"genero": "m"}):
#       lista_modelos.append(modelo)
#   return json.dumps(lista_modelos, default=default)

# def list_female():
#   database = get_connection()
#   modelos = database["modelos"]
#   lista_modelos = []
#   for modelo in modelos.find({"genero": "f"}):
#       lista_modelos.append(modelo)
#   return json.dumps(lista_modelos, default=default)
