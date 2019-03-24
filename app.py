from flask import Flask
from flask import jsonify
from flask import request
app = Flask(__name__)

@app.route('/')
def hello_world():
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
