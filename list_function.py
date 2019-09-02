import json
from bson.json_util import default
from db import get_connection

def list_all():
  collection = 'modelos'
  connection = get_connection()[collection]

  lista_modelos = []
  for modelo in connection.find():
      lista_modelos.append(modelo)

  return json.dumps(lista_modelos, default=default)

#def list_filter(arg1=None, arg2=None):
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
