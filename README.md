# PlatePlan

A Java web project for finding recipes by ingredient. It uses JSP and JSTL for the pages, a local SQL database, and the MealDB API for recipe data.

- Search by ingredient and browse matching recipes.
- View recipe details, ingredients and preparation steps.

## Local setup

1. Clone the repository and import it as an existing project in Eclipse IDE for Enterprise Java. Use JDK 21 and Tomcat 9.
2. Set your Tomcat runtime and replace the old `D:/eclipse-workspaceEE/` library references in Java Build Path with the matching JARs in `src/main/webapp/WEB-INF/lib`.
3. Run on Tomcat and open `http://localhost:8080/PlatePlan/landing`. Use the `PlatePlan` context path; redirects in the source depend on it.
4. To try recipe search directly, open `/PlatePlan/ingredientss?ingredients=chicken`. This needs internet access to MealDB.

The source compiles and recipe search runs on Tomcat. Account and email features are unfinished: `DbConnection()` does not open a database connection, and local database/mail setup is still needed. The separate `/api/recipes/search` route currently returns no JSON body.
