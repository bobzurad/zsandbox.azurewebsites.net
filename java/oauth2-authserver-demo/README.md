Custom authserver implemented while following section 16 of this Udemy course: https://www.udemy.com/course/spring-security-zero-to-master/

Authorization Server configuration can be found at: http://localhost:9000/.well-known/openid-configuration

### Requires:
* Java 17
* Docker

### Instructions
* Run `docker-compose up` to start the MySQL server
* Execute the SQL setup code in [db-setup-script.sql](db-setup-script.sql)
  * Only do this when setting up the project for the first time!
* Start the `AuthServerApplication` (via command line, Maven, IDE, etc)
  * runs at port 9000
* Start the `ResourceServerApplication`
  * runs at port 8080

Tokens can be validated at: https://jwt.io/

### TODO:
* Fix controllers returning empty objects