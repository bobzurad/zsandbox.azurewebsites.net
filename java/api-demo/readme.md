A Spring Boot REST API

to run:

* start mysql:
  * `docker-compose up`

* start the web server:
  * `mvn spring-boot:run`

to connect to mysql instance:
* `docker exec -it api-demo_mysql_1 /bin/bash`
* `mysql -uroot -p`

references:
* https://medium.com/geekculture/build-a-spring-boot-rest-api-with-java-maven-and-mysql-92bdb654caa9
  * https://geshan.com.np/blog/2022/02/mysql-docker-compose/
* https://medium.com/geekculture/dockerizing-a-spring-boot-application-with-maven-122286e9f582