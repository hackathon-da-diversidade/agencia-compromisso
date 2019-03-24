from bson import ObjectId

class Modelo(dict):
    __getattr__ = dict.get
    __delattr__ = dict.__delitem__
    __setattr__ = dict.__setitem__

    def __init__(self, nome, data_nascimento, email, telefone, endereco, responsavel_nome, responsavel__data_nascimento,\
                 responsavel_email, responsavel_telefone, genero, escolaridade, ocupacao, moradia, moradores, filhos, etnia, renda,\
                 medida_altura, medida_busto, medida_cintura, medida_quadril, observacoes):
        self.nome = nome
        self.data_nascimento = data_nascimento
        self.email = email
        self.telefone = telefone
        self.endereco = endereco
        self.responsavel_nome = responsavel_nome
        self.responsavel_data_nascimento = responsavel_data_nascimento
        self.responsavel_email = responsavel_email
        self.responsavel_telefone = responsavel_telefone
        self.genero = genero
        self.escolaridade = escolaridade
        self.ocupacao = ocupacao
        self.moradia = moradia
        self.moradores = moradores
        self.filhos = filhos
        self.etnia = etnia
        self.renda = renda
        self.medida_altura = medida_altura
        self.medida_busto = medida_busto
        self.medida_cintura = medida_cintura
        self.medida_quadril = medida_quadril
        self.observacoes = observacoes
