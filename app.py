import os
from pymongo import MongoClient
from flask import Flask
from flask import jsonify
from flask import request
from flask import Response
import json
from bson.json_util import default
from list_function import list_all
from cadastro import Modelo
from db import get_connection

app = Flask(__name__)
MONGODB_URL = os.environ.get('MONGODB_URI', 'mongodb://localhost:27017')

@app.route('/')
def hello_world():
    get_connection()
    return jsonify(string_teste='Hello, World!')

@app.route('/list', methods=['GET', 'POST'])
def list():
    if request.method == 'GET':
        return Response(list_all(), status=201, mimetype='application/json')
        pass
    elif request.method == 'POST':
        # Filtra o conteúdo antes de listar
        pass

@app.route('/create', methods=['POST'])
def create():
    requestData = request.get_json()
    nome = requestData.get('nome',None)
    data_nascimento = requestData.get('data_nascimento',None)
    email = requestData.get('email',None)
    telefone = requestData.get('telefone',None)
    endereco = requestData.get('endereco',None)
    responsavel_nome = requestData.get('responsavel_nome',None)
    responsavel_data_nascimento = requestData.get('responsavel_data_nascimento',None)
    responsavel_email = requestData.get('responsavel_email',None)
    responsavel_telefone = requestData.get('responsavel_telefone',None)
    genero = requestData.get('genero',None)
    escolaridade = requestData.get('escolaridade',None)
    ocupacao = requestData.get('ocupacao',None)
    moradia = requestData.get('moradia',None)
    moradores = requestData.get('moradores',None)
    filhos = requestData.get('filhos',None)
    etnia = requestData.get('etnia',None)
    renda = requestData.get('renda',None)
    medida_altura = requestData.get('medida_altura',None)
    medida_busto = requestData.get('medida_busto',None)
    medida_cintura = requestData.get('medida_cintura',None)
    medida_quadril = requestData.get('medida_quadril',None)
    observacoes = requestData.get('observacoes',None)

    mod = get_connection()['modelos']
    result = Modelo(nome, data_nascimento, email, telefone, endereco, responsavel_nome, responsavel_data_nascimento,\
                    responsavel_email, responsavel_telefone, genero, escolaridade, ocupacao, moradia, moradores, filhos, etnia, renda,\
                    medida_altura, medida_busto, medida_cintura, medida_quadril, observacoes)
    mod.insert(result)
    return Response(json.dumps(result, default=default), status=201, mimetype='application/json')

if __name__ == '__main__':
    app.run()
