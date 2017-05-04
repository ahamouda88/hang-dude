# Hangdude
This web application is a simple version of the hangman game.

## Implementation:
- This project is implemented using AngularJS and Bootstrap as the front-end technology, while for the back-end and the restful API are implemented using Java 8, Spring Boot, and Spring MVC. 
- Maven is used as the building tool. 
- For unit and integration testing, JUnit, and Mockito frameworks are used. 

## Running the application:
- You will need Maven tool, to build the application
- Build the application using Maven using the following command: mvn clean install
- Two ways for running the application:
	-- A war file will be create at './target' folder, copy the file and deploy it to a web server
	-- Simply you can either run the application by executing '/src/main/java/com/hangdude/config/spring/SpringBootConfig.java' or use the following command: mvn spring-boot:run
- Then you can access the game from the following url: 

## Pages End Points 
|              URI                   |                               Description                               |   Method   |
|------------------------------------|-------------------------------------------------------------------------|------------|
| /                                  | Welcome Page (Where you can choose a word)                              |     GET    |
| /board-page                        | The current game page by current user                                   |     GET    |
| /all-board-page                    | Display all games currently being played by all users                   |     GET    |
