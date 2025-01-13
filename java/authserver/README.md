Custom authserver implemented while following section 16 of this Udemy course: https://www.udemy.com/course/spring-security-zero-to-master/

Config can be found at: http://localhost:9000/.well-known/openid-configuration

### Requires:
* Java 17
* Docker

### Instructions
* Run `docker-compose up` to start the MySQL server
* Execute the SQL setup code in [scripts.sql](src/main/resources/sql/scripts.sql)
  * Only do this when setting up the project for the first time!
* Start the `AuthServerApplication` (via command line, Maven, IDE, etc)

Tokens can be validated at: https://jwt.io/