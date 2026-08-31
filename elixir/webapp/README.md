# webapp

This project is a web app that uses the [Phoenix Framework](https://www.phoenixframework.org), and was bootstrapped with `mix phx.new webapp`

## Prerequisites and Dev Setup

This project requires the following to be installed:

* [Erlang](https://www.erlang.org/)
* [Elixir](https://elixir-lang.org/)
* [Docker](https://docs.docker.com/get-started/get-docker/)

Note: This project contains a `.tool-versions` file. So if you have [asdf](https://asdf-vm.com) installed, you can run `asdf install erlang` and `asdf install elixir` to ensure that you're using the proper versions of Erlang and Elixir.

### Configuring Environment Variables

Environment variables are expected to be in a file named `.env` in the root of this project. To protect sensitive values that we don't want commited into source control, `.env` is intentionally included in [.gitignore](.gitignore). 

To setup the environment variables, perform the following steps:

* Create a file named `.env` in the root folder of this project
* Copy the variables from [.env.example](.env.example)
* Supply appropriate values to `.env` where no values exist

### Setting up the Database

For data persistence, this project uses Docker containers for PostgreSQL and pgAdmin. To start the docker containers, run: `docker compose up`

* PostgreSQL is available at localhost:5432
* pgAdmin is accessible at http://localhost:5433
  * Create a connection to the PostgreSQL server at `postgres:5432`
  * If running Linux, to access PostgreSQL from pgAdmin, connect to the IP address listed when inspecting the postgresql container `docker inspect <container-name>`

* see `.env` for credentials

To create the database for this project, run `mix ecto.create` (The configuration that Phoenix requires for the database can be found in [config/dev.exs](./config/dev.exs))

## Running webapp

First run `docker compose up` to start the PostgreSQL database.

To start the Phoenix server:

* Run `mix setup` to install and setup dependencies
* Start Phoenix endpoint with `mix phx.server` or inside IEx with `iex -S mix phx.server`

Now you can visit http://localhost:4000 from your browser.

Ready to run in production? Please [check our deployment guides](https://phoenix.hexdocs.pm/deployment.html).

## Learn more

* Official website: https://www.phoenixframework.org/
* Guides: https://phoenix.hexdocs.pm/overview.html
* Docs: https://phoenix.hexdocs.pm
* Forum: https://elixirforum.com/c/phoenix-forum
* Source: https://github.com/phoenixframework/phoenix
