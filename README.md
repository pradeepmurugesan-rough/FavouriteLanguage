# FavouriteLanguage

#####Technologies used

server side : Java, Jersey
client side : Angular Js, Bootstrap.

The application is deployed [here] (https://sheltered-hamlet-1151.herokuapp.com/)

To run this locally just do a maven install from project root

1. Local Tomcat
	mvn install

	once done deploy the target/favourite_language-1.0-SNAPSHOT.war into tomcat's web app folder.
2. Webapp-runner
	You need a jvm alone to run this.
	after maven install run the following command

	```
	java -jar target\dependency\webapp-runner.jar target\favourite_language-1.0-SNAPSHOT.war
  ```
