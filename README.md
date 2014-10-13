springboot-springsecurity-example
=================================

Example of Spring Boot / Spring Security / MongoDB / Web Sockets. 

## Getting Started

Check this project out, cd into the directory and run:

    ./gradlew build

This will build the jar. Now run:

    java -jar build/libs/springboot-security-notifications-1.0.0.jar 

This will start the spring boot app. In your browser go to <http://localhost:8080>.


## Notes
*To run this app, you need MongoDB installed*

The application will always drop user and role collections in the database and setup the init data:

Username  | password  | Role
-------| ----- |------------
admin  |password | ROLE_ADMIN
user1  | password|   ROLE_USER
user2  | password|  ROLE_USER

Please take a look on UserController and RoleController to add/delete new users/roles.

This app also has web sockets support. It uses google guava library to cache the failed login attempts. After 3 failed attempts by the same user, the app will send a message. You can subscribe to the web socket in <http://localhost:8080/alerts>.

