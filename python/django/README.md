# Django Web App

This project is a web app that uses the [Django](https://www.djangoproject.com) web framework.

## Prerequisites and Dev Setup

This project requires [Python](https://www.python.org) 3. This project contains a `.tool-versions` file, so if you have [asdf](http://asdf-vm.com) installed you can run `asdf install python` to ensure that you're using the proper version of Python.

This project also requires [Docker](https://docs.docker.com) to run a PostgreSQL database.

### Data Persistence

For data persistence, this project uses Docker containers for PostgreSQL and pgAdmin. 

* PostgreSQL is available at localhost:5432
* pgAdmin is accessible at http://localhost:5433
  * create a connection to the server at `postgres:5432`
* see `.env` for credentials

Tip: If running Linux, to access PostgreSQL from pgAdmin, connect to the IP address listed when inspecting the postgresql container `docker inspect <container-name>`


### Python Virtual Environment

It is recommended to use a [python virtual environment](https://docs.python.org/3/tutorial/venv.html) to keep this project isolated from any other python projects you may be using.

To create a python virtual environment, run: `python -m venv .venv`

Then activate the python virtual environment by running: `source .venv/bin/activate`

## First Time Setup

If you are running the project for the first time, perform the following steps:

* Copy the variables in `.env.example` to `.env` and provide necessary values
* Ensure the python virtual environment is active: `source .venv/bin/activate`
* Install the python dependencies for this project: `pip install -r requirements.txt`
* Start the PostgreSQL instance: `docker compose up`
* TODO: run migrations to create the database

## Running the Project

To run the project, first start the PostgreSQL instance:
```bash
docker compose up -d
```

Activate the python virtual environment:
```bash
source .venv/bin/activate
```

You can then run the web app by running:
```bash
cd src
python manage.py runserver
```
