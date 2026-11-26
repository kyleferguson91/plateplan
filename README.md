# PlatePlan

A Java web project for finding recipes by ingredient. It uses JSP and JSTL for the pages, a local SQL database, and the MealDB API for recipe data.

- Search by ingredient and browse matching recipes.
- View recipe details, ingredients and preparation steps.

## Local setup

1. Clone the repository and import it as an existing project in Eclipse IDE for Enterprise Java. The project targets JDK 21 and Tomcat 9.
2. Set your Tomcat runtime and fix the old `D:/eclipse-workspaceEE/` library references in Java Build Path. The matching JARs are included in `src/main/webapp/WEB-INF/lib`.
3. Create a local MySQL database named `plateplan` and configure your own connection details in `src/main/java/com/plateplan/appdao/DbConnection.java`. Keep local credentials out of Git.
4. Run the project on Tomcat and open `/PlatePlan/landing` (or use your configured context path). Recipe searches need internet access to MealDB.

This is a coursework project with machine-specific configuration, not a ready-to-deploy package. Account and email features need local database and mail setup; no separate SQL migration is included.
