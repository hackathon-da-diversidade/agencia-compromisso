venv_path ?=.venv
source_venv =. $(venv_path)/bin/activate &&

init:
	test -d $(venv_path) || python3 -m venv $(venv_path)
	$(source_venv) pip install -r requirements.txt

run: init
	$(source_venv) gunicorn app:app
