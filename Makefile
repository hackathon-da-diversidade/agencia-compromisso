venv_path ?=.venv
source_venv =. $(venv_path)/bin/activate && 

init:
	pip install -r requirements.txt

activate:
	test -d $(venv_path) || python3 -m venv $(venv_path)

run: activate
	$(source_venv) gunicorn app:app --chdir agenciacompromisso

test: activate
	$(source_venv) python3 -m unittest discover --verbose test 
