# Booktique-Library Project

## Overview
The Booktique-Library Project is a web application that allows users to search for books, check out books, return books, view their book history, leave reviews on books, and renew book loan times. All actions require the user to be logged in through Okta for authentication.

## Features
1. **Search Books**: Users can search for books by title, author, genre, or ISBN.
2. **Check Out Books**: Users can check out available books.
3. **Return Books**: Users can return books they have borrowed.
4. **Book History**: Users can view their borrowing history.
5. **Leave Book Review**: Users can leave reviews and ratings for books they have read.
6. **Renew Book Loan Time**: Users can renew the loan period for books they have borrowed.
7. **User Authentication**: All features require users to log in through Okta.

## Tech Stack
- **Backend**: Java, Spring Boot, Maven
- **Frontend**: React, Typescript
- **Authentication**: Okta
- **Database**: MySQL

## Getting Started

### Prerequisites
- Java 11
- Node.js
- MySQL
- Okta account

### Backend Setup

1. **Clone the repository**
    ```sh
    git clone https://github.com/Nankun-Xu/Booktique-Library
    cd Booktique-Library
    ```

2. **Configure MySQL**
    - Create a database named `booktique-library`.
    - Update the `application.properties` file with your MySQL credentials.


3. **Configure Okta**
    - Register your application on Okta.
    - Update the `application.properties` file with your Okta credentials.
    
    ```properties
    okta.oauth2.client-id=YOUR_CLIENT_ID
    okta.oauth2.client-secret=YOUR_CLIENT_SECRET
    okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
    ```

4. **Run the backend**
    ```sh
    ./mvnw spring-boot:run
    ```

### Frontend Setup

1. **Navigate to the frontend directory**
    ```sh
    cd ../01-frontend
    ```

2. **Install dependencies**
    ```sh
    npm install
    ```

3. **Configure Okta**
    - Create a `.env` file in the frontend directory and add your Okta credentials.
    
    ```env
    REACT_APP_OKTA_CLIENT_ID=YOUR_CLIENT_ID
    REACT_APP_OKTA_ISSUER=https://{yourOktaDomain}/oauth2/default
    ```

4. **Run the frontend**
    ```sh
    npm start
    ```

## Project Structure
Booktique-Library/
├── 01-frontend/
│ ├── public/
│ ├── src/
│ │ ├── Auth/
│ │ ├── Images/
│ │ ├── layouts/
│ │ ├── lib/
│ │ ├── models/
│ │ ├── App.tsx
│ │ ├── index.tsx
│ ├── .env
└── README.md
├── 02-backend/
│ ├── src/main/java/com/norax/booktique-library/
│ │ ├── BooktiqueLibraryApplication.java
│ │ ├── config/
│ │ ├── controller/
│ │ ├── requestmodel/
│ │ ├── responsemodel/
│ │ ├── entity/
│ │ ├── dao(repository)/
│ │ ├── service/
│ │ ├── utils/
│ └── resources/
│ ├── application.properties



## API Endpoints

### Authentication
- `POST /login` - User login
- `POST /logout` - User logout

### Book Management
- `GET /books` - Get all books
- `GET /books/{id}` - Get book details

### BookController Endpoints

#### `GET /api/books/secure/currentloans`
- Description: Get the current loans for the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Response: List of current loans.

#### `GET /api/books/secure/currentloans/count`
- Description: Get the count of current loans for the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Response: Count of current loans.

#### `GET /api/books/secure/ischeckedout/byuser`
- Description: Check if a book is checked out by the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Query Parameter: `bookId` (Long)
- Response: Boolean indicating if the book is checked out by the user.

#### `PUT /api/books/secure/checkout`
- Description: Check out a book for the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Query Parameter: `bookId` (Long)
- Response: The checked-out book.

#### `PUT /api/books/secure/return`
- Description: Return a book for the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Query Parameter: `bookId` (Long)

#### `PUT /api/books/secure/renew/loan`
- Description: Renew the loan period for a book for the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Query Parameter: `bookId` (Long)

### ReviewController Endpoints

#### `GET /api/reviews/secure/user/book`
- Description: Check if the authenticated user has reviewed a specific book.
- Request Header: `Authorization: Bearer <token>`
- Query Parameter: `bookId` (Long)
- Response: Boolean indicating if the user has reviewed the book.

#### `POST /api/reviews/secure`
- Description: Post a review for a book by the authenticated user.
- Request Header: `Authorization: Bearer <token>`
- Request Body: `ReviewRequest` object.

## License
This project is licensed under the MIT License.

## Contact
For any questions or feedback, please contact nxu930608@gmail.com.





