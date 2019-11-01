from bson.objectid import ObjectId
from db import get_connection


def remove_model_by_id(id):
  collection = 'modelos'
  connection = get_connection()[collection]

  resultado = connection.delete_one({"_id": ObjectId(id)})

  return resultado.deleted_count
