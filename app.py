from flask import Flask
from flask import request
from flask import Response
from create_function import create_function
from list_function import list_all

app = Flask(__name__)

@app.route('/list', methods=['GET', 'POST'])
def list():
    if request.method == 'GET':
        return Response(list_all(), status=200, mimetype='application/json')
    elif request.method == 'POST':
        # Filtra o conteúdo antes de listar
        pass

@app.route('/create', methods=['POST'])
def create():
    requestData = request.get_json()

    id, dados_resultado = create_function(requestData)

    if id:
        return Response(dados_resultado, status=201, mimetype='application/json')

    return Response(status=400)

if __name__ == '__main__':
    app.run()
