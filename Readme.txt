1. This application uses MySQL8
2. Java version - 1.8
3. Application is developed using IDE - Intellij
4. IDE can be used for building or Maven is used for building the application using the below command- 
	mvn clean install -Dmaven.test.skip=true -Dcheckstyle.skip=true
4. Deployment can be done as application from IDE(Run) or from command line(Target folder) using
	java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8082,suspend=n -jar userService-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=SWAGGER -jar  -spring.config.location=config\application.properties
5. Application once deployed , can be access via Swagger url- http://localhost:8082/user/swagger-ui/#/


6. DateBase configurations-
  
   The application's db configurations are read from application.properties file.
   We can update the configurations according to indivual setup-
	   spring.datasource.url= jdbc:mysql://localhost:3636/user?serverTimezone=UTC
	   spring.datasource.username=root
	   spring.datasource.password=root
	   
	   
	   
7. Initial scehema creation and table creation scripts are in->
      
	  userService\src\main\java\com\leaning\userApp\Db
	   
	   
	   
8. The project contains ide files and some libraries which are not required, but added in the checkin if the use is not using IDE might be of help