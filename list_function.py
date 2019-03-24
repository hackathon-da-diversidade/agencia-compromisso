import os
from pymongo import MongoClient
import json
from bson.json_util import default

MONGODB_URL = os.environ.get('MONGODB_URI', 'mongodb://localhost:27017')

def get_connection():
  return MongoClient(MONGODB_URL)["agcompromisso"]

def create_test():
    database = get_connection()
    modelos = database["modelos"]
    modelos.insert({"string_teste":"Hello, World!", "genero": "f"})
    modelos.insert({"string_teste":"Ola mundo", "genero": "m"})


def list_all():
  database = get_connection()
  modelos = database["modelos"]
  lista_modelos = []
  for modelo in modelos.find():
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
