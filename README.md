# Recipe Generator Backend

This repository contains the **Spring Boot backend** for the Recipe Generator application. It provides API endpoints for managing users, recipes, dietary preferences, and ingredients.

## UML Diagram
![image](https://github.com/user-attachments/assets/0024d960-f03e-441f-b450-9fa13f9dea62)

## Tech Stack
- **Java 17**
- **Spring Boot**
- **MySQL**
- **Amazon RDS**
- **Maven**
- **JPA (Hibernate)**
- **Lombok**
- **Bycrypt Password Encoder**

## API Endpoints
POST   /api/auth/register
POST   /api/auth/login

GET    /api/user/info

POST   /api/dietary-preferences
GET    /api/dietary-preferences/{userId}
PUT    /api/dietary-preferences/{id}
DELETE /api/dietary-preferences/{userId}

POST   /api/recipes/saveRecipe
GET    /api/recipes/savedRecipes
DELETE /api/recipes/recipe/{id}
