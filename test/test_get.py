from src.get_function import list_all
from bson.json_util import default
from unittest import mock, TestCase
import json

class TestGet(TestCase):

    @mock.patch("pymongo.collection.Collection.find")
    def test_get(self, mock_find):
        mock_find.return_value = [{'_id': '5deff40e9c6e5076de00d1f7', 'nome': 'John', 'data_nascimento': None, 'email': 'uk@', 'telefone': None}, {'_id': '5deff3949c6e5076de00d1f3', 'nome': 'Maria', 'data_nascimento': None, 'email': 'example@', 'telefone': None}] 
        modelos = list_all()
        self.assertEqual(modelos, json.dumps([{'_id': '5deff40e9c6e5076de00d1f7', 'nome': 'John'}, {'_id': '5deff3949c6e5076de00d1f3', 'nome': 'Maria'}], default=default))

