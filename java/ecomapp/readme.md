An API written in Java.

## Prerequisites
* Java 21
* Maven 3

## Dev Setup

Install Java and Maven. If you have [asdf](https://asdf-vm.com) installed, you can run `asdf install java` and `asdf install maven` to make sure that the proper versions are installed.

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