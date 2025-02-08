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

```http
POST   /api/auth/register                  # Register a new user and return JWT token
POST   /api/auth/login                     # Authenticate user and return JWT token

GET    /api/user/info                      # Get details of the authenticated user

POST   /api/dietary-preferences            # Add dietary preferences for a user
GET    /api/dietary-preferences/{userId}   # Get dietary preferences of a user by user ID
PUT    /api/dietary-preferences/{id}       # Update dietary preferences by ID
DELETE /api/dietary-preferences/{userId}   # Delete dietary preferences of a user by user ID

POST   /api/recipes/saveRecipe             # Save a new recipe with ingredients
GET    /api/recipes/savedRecipes           # Get all saved recipes
DELETE /api/recipes/recipe/{id}            # Delete a saved recipe by recipe ID

