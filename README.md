Movie Management App

Overview This application allows the admin to manage movies fetched from the OMDB API, and the regular user can view and rate the movies. It is built using Angular 18+ for the frontend and Spring Boot for the backend. Features: • Admin Dashboard: o Login functionality for admin users and normal users. o Fetch movie data from OMDB API and display in the admin dashboard. o Ability for the admin to add or remove movies from the database. • Regular User Dashboard: o Login functionality for regular users. o Ability to view the full list of movies. o View movie details. o Search and rate movies. Technologies: • Frontend: Angular 18+ • Backend: Spring Boot • Database: MySQL

Project Structure:
Backend: Located in the Movie_System folder (Spring Boot) Frontend: Located in the frontend folder (Angular)


Prerequisites
• Java 17 or higher • Node.js (for Angular) • MySQL Database (or any other database) • Git

Clone the Repository Clone the project from GitHub: git clone  https://github.com/mahmoudashraf043/Movie_System.git
Set Up the Backend (Spring Boot) • Navigate to the backend directory. cd Movie_System 
• Ensure the necessary properties are set for the database connection in application.properties
. Example configuration for MySQL: spring.datasource.url=jdbc:mysql://127.0.0.1:3306/Movie_System spring.datasource.username=root spring.datasource.password=yourpassword spring.jpa.hibernate.ddl-auto=update
Set Up the Frontend (Angular) • Install dependencies: npm install -g @angular/cli
• Navigate to the frontend directory . cd frontend\myAngularapp 
• Install dependencies: npm install 
• Start the Angular app: ng serve The Angular application will now be running at http://localhost:4200.



The Main admin is created automatically is
Username : mahmoud043
password: 123456789
