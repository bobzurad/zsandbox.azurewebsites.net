Custom authserver implemented while following section 16 of this Udemy course: https://www.udemy.com/course/spring-security-zero-to-master/

This project consists of two projects:
* A Spring Authorization Server to authenticate users
* A Spring Resource Server which hosts an API to make authorized requests to.

This project does not currently contain a web client, so API calls can be made using [Postman](https://www.postman.com). Use the [OAuth2-AuthServer-Demo.postman_collection.json](OAuth2-AuthServer-Demo.postman_collection.json) Postman Collection to authenticate and make API calls.

### Requires:
* Java 17
* Docker

### Instructions
* Run `docker-compose up` to start the MySQL server
* Execute the SQL setup code in [db-setup-script.sql](db-setup-script.sql)
  * Only do this when setting up the project for the first time!
* Start the `AuthServerApplication` (via command line, Maven, IDE, etc.)
  * runs at port 9000
* Start the `ResourceServerApplication`
  * runs at port 8080

Authorization Server configuration can be found at: http://localhost:9000/.well-known/openid-configuration

When using Authorization Code Grant Type, use the following credentials to login:
* username: happy@example.com
* password: EazyBytes@54321

Tokens can be validated at: https://jwt.io/

### TODO:
* Fix controllers returning empty objects
  * might be related to 500 error being thrown when calling POST http://localhost:8080/contact