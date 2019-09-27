from unittest import TestCase
from freezegun import freeze_time
from modelo import Modelo

class TestModelo(TestCase):

    def test_modelo(self):
        nome = 'Maria'
        data = '01/01/2000'
        email = 'maria@hotmail.com'
        telefone = '33333333'
        logradouro = 'Avenida ipiranga'
        endereco_numero = '1010'
        endereco_complemento = '20'
        endereco_bairro = 'Partenon'
        endereco_cidade = 'Porto Alegre'
        endereco_estado = 'RS'
        endereco_cep = '90011011'
        nome_responsavel = 'Responsavel da Maria'
        nascimento_responsavel = '01/01/1980'
        email_responsavel = 'responsavel@hotmail.com'
        telefone_responsavel = '33333333'
        genero = 'feminino'
        lgbtqia = 'true'
        escolaridade = 'ensino_medio'
        ocupacao = 'estudante'
        moradia = 'alugada'
        moradores = '2'
        filhos = '0'
        etnia = 'negro'
        disponibilidade = 'total'
        renda = '900,00'
        medida_altura = '160'
        medida_busto = '90'
        medida_cintura = '80'
        medida_quadril = '100'
        observacoes = 'nenhuma'

        modelo = Modelo(nome, data, email, telefone, logradouro,
                        endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade, endereco_estado, endereco_cep,
                        nome_responsavel,
                        nascimento_responsavel, email_responsavel, telefone_responsavel, genero, lgbtqia,
                        escolaridade, ocupacao, moradia, moradores, filhos, etnia, disponibilidade, renda, medida_altura,
                        medida_busto, medida_cintura, medida_quadril, observacoes)
        self.assertEqual(modelo.nome, '%s' % nome)
        self.assertEqual(modelo.data_nascimento, data)
        self.assertEqual(modelo.email, email)
        self.assertEqual(modelo.telefone, telefone)
        self.assertEqual(modelo.endereco_logradouro, logradouro)
        self.assertEqual(modelo.endereco_numero, endereco_numero)
        self.assertEqual(modelo.endereco_complemento, endereco_complemento)
        self.assertEqual(modelo.endereco_bairro, endereco_bairro)
        self.assertEqual(modelo.endereco_cidade, endereco_cidade)
        self.assertEqual(modelo.endereco_estado, endereco_estado)
        self.assertEqual(modelo.endereco_cep, endereco_cep)
        self.assertEqual(modelo.responsavel_nome, nome_responsavel)
        self.assertEqual(modelo.responsavel_nascimento, nascimento_responsavel)
        self.assertEqual(modelo.responsavel_email, email_responsavel)
        self.assertEqual(modelo.responsavel_telefone, telefone_responsavel)
        self.assertEqual(modelo.genero, genero)
        self.assertEqual(modelo.lgbtqia, lgbtqia)
        self.assertEqual(modelo.escolaridade, escolaridade)
        self.assertEqual(modelo.ocupacao, ocupacao)
        self.assertEqual(modelo.moradia, moradia)
        self.assertEqual(modelo.moradores, moradores)
        self.assertEqual(modelo.filhos, filhos)
        self.assertEqual(modelo.etnia, etnia)
        self.assertEqual(modelo.disponibilidade, disponibilidade)
        self.assertEqual(modelo.renda, renda)
        self.assertEqual(modelo.medida_altura, medida_altura)
        self.assertEqual(modelo.medida_busto, medida_busto)
        self.assertEqual(modelo.medida_cintura, medida_cintura)
        self.assertEqual(modelo.medida_quadril, medida_quadril)
        self.assertEqual(modelo.observacoes, observacoes)

    def test_calcula_idade(self):
        modelo = Modelo('', '1993-07-09T03:00:00.000Z', '', '',
                        '','','','','','','','', '', '','','','','','','','', '', '','','','','','','', '')
        freezer = freeze_time("2019-09-26 12:00:01")
        freezer.start()
        idade = modelo.calcula_idade()
        self.assertEqual(26, idade)
        freezer.stop()