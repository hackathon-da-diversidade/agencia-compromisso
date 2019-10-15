from unittest import TestCase
from agenciacompromisso.db import get_connection
from mongo_client_test import MongoClientTest
from unittest.mock import patch
from agenciacompromisso.get_function import list_all

class TestCreate(TestCase):

    def setUp(self):
        patch(get_connection, MongoClientTest())

    def test_create(self):
        print(list_all())

        self.fail()
