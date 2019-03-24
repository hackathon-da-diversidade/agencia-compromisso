import os
from pymongo import MongoClient

MONGODB_URL = os.environ.get('MONGODB_URI', 'mongodb://localhost:27017')

MONGODB_COLLECTION = os.environ.get('MONGODB_COLLECTION', 'agcompromisso')

def get_connection():
  return MongoClient(MONGODB_URL)["agcompromisso"]
