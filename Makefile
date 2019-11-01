venv_path ?=.venv
source_venv =. $(venv_path)/bin/activate && 

init:
	pip install -r requirements.txt

virtualenv:
	test -d $(venv_path) || python3 -m venv $(venv_path)

run: virtualenv
	$(source_venv) gunicorn app:app --chdir src

test: virtualenv
	$(source_venv) python3 -m unittest discover --verbose test 
