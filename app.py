import os
from pymongo import MongoClient
from flask import Flask
from flask import jsonify
from flask import request
app = Flask(__name__)

MONGODB_URL = os.environ.get('MONGODB_URL', 'mongodb://localhost:27017')

def get_connection():
  return MongoClient(MONGODB_URL)["agcompromisso"]

@app.route('/')
def hello_world():
    get_connection()
    return jsonify(string_teste='Hello, World!')

@app.route('/list', methods=['GET', 'POST'])
def list():
    if request.method == 'GET':
        # Apenas lista o conteúdo
        pass
    elif request.method == 'POST':
        # Filtra o conteúdo antes de listar
        pass

@app.route('/create', methods=['POST'])
def create():
    pass

if __name__ == '__main__':
    app.run()
