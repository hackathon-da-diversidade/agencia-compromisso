import datetime

class Modelo(dict):
    __getattr__ = dict.get
    __delattr__ = dict.__delitem__
    __setattr__ = dict.__setitem__

    def __init__(self, nome, data_nascimento, email, telefone, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade, endereco_estado, endereco_cep, responsavel_nome, responsavel_nascimento,\
                 responsavel_email, responsavel_telefone, genero, lgbtqia, escolaridade, ocupacao, moradia, moradores, filhos, etnia, disponibilidade, renda,\
                 medida_altura, medida_busto, medida_cintura, medida_quadril, observacoes):
        self.nome = nome
        self.data_nascimento = data_nascimento
        self.email = email
        self.telefone = telefone
        self.endereco_logradouro = endereco_logradouro
        self.endereco_numero = endereco_numero
        self.endereco_complemento = endereco_complemento
        self.endereco_bairro = endereco_bairro
        self.endereco_cidade = endereco_cidade
        self.endereco_estado = endereco_estado
        self.endereco_cep = endereco_cep
        self.responsavel_nome = responsavel_nome
        self.responsavel_nascimento = responsavel_nascimento
        self.responsavel_email = responsavel_email
        self.responsavel_telefone = responsavel_telefone
        self.genero = genero
        self.lgbtqia = lgbtqia
        self.escolaridade = escolaridade
        self.ocupacao = ocupacao
        self.moradia = moradia
        self.moradores = moradores
        self.filhos = filhos
        self.etnia = etnia
        self.disponibilidade = disponibilidade
        self.renda = renda
        self.medida_altura = medida_altura
        self.medida_busto = medida_busto
        self.medida_cintura = medida_cintura
        self.medida_quadril = medida_quadril
        self.observacoes = observacoes


    def calcula_idade(self):
        hoje = datetime.date.today()
        ano_nascimento = self.converte_para_data(self.data_nascimento).date()
        return hoje.year - ano_nascimento.year - ((hoje.month, hoje.day) < (ano_nascimento.month, ano_nascimento.day))

    def converte_para_data(self, texto):
        return datetime.datetime.strptime(texto, '%Y-%m-%dT03:00:00.000Z')

