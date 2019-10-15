from collections import defaultdict

class MongoClientTest():

    def __init__(self):
        self.defaultdict(lambda: self)

    def find(self):
        return [ find_one({}) ]

    def find_one(param):
        return {
            "_id": { "$oid": "5da617bca2ffac398bfd82db" },
            "nome": "Maria",
            "data_nascimento": "2019-10-15T03:00:00.000Z",
            "email": "maria@hotmail.com",
            "telefone": "123456",
            "endereco_logradouro": "Tecnopouc",
            "endereco_numero": "6618",
            "endereco_complemento": "14A",
            "endereco_bairro": "Centro",
            "endereco_cidade": "Porto Alegre",
            "endereco_estado": "Brasil",
            "endereco_cep": "92010000",
            "responsavel_nome": "Maria",
            "responsavel_nascimento": "2019-10-15T03:00:00.000Z",
            "responsavel_email": "maria@hotmail.com",
            "responsavel_telefone": "123456",
            "genero": "feminino",
            "lgbtqia": false,
            "escolaridade": "",
            "ocupacao": "",
            "moradia": "",
            "moradores": "",
            "filhos": "",
            "etnia": "",
            "disponibilidade": "",
            "renda": "",
            "medida_altura": "",
            "medida_busto": "",
            "medida_cintura": "",
            "medida_quadril": "",
            "observacoes": ""
            }