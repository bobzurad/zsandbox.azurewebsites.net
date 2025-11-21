An API written in Java.

## Prerequisites
* Java 21
* Maven 3
* Docker

## Dev Setup

Install Java and Maven. If you have [asdf](https://asdf-vm.com) installed, you can run `asdf install java` and `asdf install maven` to make sure that the proper versions are installed.

### Data Persistence
For data persistence, this project uses Docker containers for PostgreSQL and pgAdmin. To start the docker containers, run: `docker compose up`

- PostgreSQL is available at localhost:5432
- pgAdmin is accessible at http://localhost:5433
  - create a connection to the server at `postgres:5432`
- see `.env` for credentials

Data is loaded into PostgreSQL from the `.sql` scripts in the `./db` folder. To clear and reset all sql data, run the following:

```bash
docker compose down
sudo rm -rf docker-volumes
docker volume rm ecomapp_pgadmin_config_dir
docker volume rm ecomapp_pgadmin_working_dir
```

## Building
* `mvn clean install`

## Running
* `mvn spring-boot:run`

Swagger runs at http://localhost:8080/swagger-ui/index.html

## Extensions and Styling

### VS Code Extensions:

If using VS Code, install the following extensions to make your life easier:

* [Extension Pack For Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
* [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)

### Code Style

This project is styled using [google-java-format](https://github.com/google/google-java-format)

The following extensions are available:
* IntelliJ - [google-java-format](https://plugins.jetbrains.com/plugin/8527-google-java-format)
* VS Code - [Google Java Format for VS Code](https://marketplace.visualstudio.com/items?itemName=JoseVSeb.google-java-format-for-vs-code)