from unittest import TestCase
from freezegun import freeze_time
from modelo import Modelo

class TestModelo(TestCase):

    freezer = freeze_time("2019-09-26 12:00:01")

    def setup_method(self, test_method):
        print('set up')
        self.freezer.start()

        self.nome = 'Maria'
        self.data = '1993-07-09T03:00:00.000Z'
        self.email = 'maria@hotmail.com'
        self.telefone = '33333333'
        self.logradouro = 'Avenida ipiranga'
        self.endereco_numero = '1010'
        self.endereco_complemento = '20'
        self.endereco_bairro = 'Partenon'
        self.endereco_cidade = 'Porto Alegre'
        self.endereco_estado = 'RS'
        self.endereco_cep = '90011011'
        self.nome_responsavel = 'Responsavel da Maria'
        self.nascimento_responsavel = '01/01/1980'
        self.email_responsavel = 'responsavel@hotmail.com'
        self.telefone_responsavel = '33333333'
        self.genero = 'feminino'
        self.lgbtqia = 'true'
        self.escolaridade = 'ensino_medio'
        self.ocupacao = 'estudante'
        self.moradia = 'alugada'
        self.moradores = '2'
        self.filhos = '0'
        self.etnia = 'negro'
        self.disponibilidade = 'total'
        self.renda = '900,00'
        self.medida_altura = '160'
        self.medida_busto = '90'
        self.medida_cintura = '80'
        self.medida_quadril = '100'
        self.observacoes = 'nenhuma'

        self.modelo = Modelo(self.nome, self.data, self.email, self.telefone, self.logradouro,
                        self.endereco_numero, self.endereco_complemento, self.endereco_bairro, self.endereco_cidade, self.endereco_estado, self.endereco_cep,
                        self.nome_responsavel,
                        self.nascimento_responsavel, self.email_responsavel, self.telefone_responsavel, self.genero, self.lgbtqia,
                        self.escolaridade, self.ocupacao, self.moradia, self.moradores, self.filhos, self.etnia, self.disponibilidade, self.renda, self.medida_altura,
                        self.medida_busto, self.medida_cintura, self.medida_quadril, self.observacoes)

    def test_modelo(self):
        
        self.assertEqual(self.modelo.nome, '%s' % self.nome)
        self.assertEqual(self.modelo.data_nascimento, self.data)
        self.assertEqual(self.modelo.email, self.email)
        self.assertEqual(self.modelo.telefone, self.telefone)
        self.assertEqual(self.modelo.endereco_logradouro, self.logradouro)
        self.assertEqual(self.modelo.endereco_numero, self.endereco_numero)
        self.assertEqual(self.modelo.endereco_complemento, self.endereco_complemento)
        self.assertEqual(self.modelo.endereco_bairro, self.endereco_bairro)
        self.assertEqual(self.modelo.endereco_cidade, self.endereco_cidade)
        self.assertEqual(self.modelo.endereco_estado, self.endereco_estado)
        self.assertEqual(self.modelo.endereco_cep, self.endereco_cep)
        self.assertEqual(self.modelo.responsavel_nome, self.nome_responsavel)
        self.assertEqual(self.modelo.responsavel_nascimento, self.nascimento_responsavel)
        self.assertEqual(self.modelo.responsavel_email, self.email_responsavel)
        self.assertEqual(self.modelo.responsavel_telefone, self.telefone_responsavel)
        self.assertEqual(self.modelo.genero, self.genero)
        self.assertEqual(self.modelo.lgbtqia, self.lgbtqia)
        self.assertEqual(self.modelo.escolaridade, self.escolaridade)
        self.assertEqual(self.modelo.ocupacao, self.ocupacao)
        self.assertEqual(self.modelo.moradia, self.moradia)
        self.assertEqual(self.modelo.moradores, self.moradores)
        self.assertEqual(self.modelo.filhos, self.filhos)
        self.assertEqual(self.modelo.etnia, self.etnia)
        self.assertEqual(self.modelo.disponibilidade, self.disponibilidade)
        self.assertEqual(self.modelo.renda, self.renda)
        self.assertEqual(self.modelo.medida_altura, self.medida_altura)
        self.assertEqual(self.modelo.medida_busto, self.medida_busto)
        self.assertEqual(self.modelo.medida_cintura, self.medida_cintura)
        self.assertEqual(self.modelo.medida_quadril, self.medida_quadril)
        self.assertEqual(self.modelo.observacoes, self.observacoes)

    def test_calcula_idade(self):
        idade = self.modelo.calcula_idade()
        self.assertEqual(26, idade)

    def teardown_method(self, test_method):
        self.freezer.stop()
