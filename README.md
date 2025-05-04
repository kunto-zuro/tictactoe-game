# Configurable Tic-Tac-Toe Game

A flexible Tic-Tac-Toe web application built with Java Spring Boot that allows playing with various board sizes. This project demonstrates Java best practices, clean code architecture, and responsive web design.

## Features

- **Configurable Board Size**: Play on 3x3, 5x5, 7x7, or 9x9 boards
- **Responsive Web Interface**: Modern UI that works on both desktop and mobile devices
- **Real-time Gameplay**: Dynamic updates without page reloads
- **RESTful API**: Complete set of API endpoints for game operations
- **Clean Architecture**: Follows OOP principles and best practices for maintainable code

## Technologies Used

- **Backend**: Java 17, Spring Boot 3.1.0
- **Frontend**: HTML5, CSS3, JavaScript, jQuery, Bootstrap 5
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven

## Project Structure

```
project-structure/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tictactoe/
│   │   │           ├── tictactoe/
│   │   │               ├── TictactoeApplication.java
│   │   │               ├── controller/
│   │   │               │   └── GameController.java
│   │   │               ├── model/
│   │   │               │   ├── Board.java
│   │   │               │   ├── Cell.java
│   │   │               │   ├── Game.java
│   │   │               │   ├── GameStatus.java
│   │   │               │   └── Player.java
│   │   │               ├── service/
│   │   │               │   ├── GameService.java
│   │   │               │   └── GameServiceImpl.java
│   │   │               └── exception/
│   │   │                   └── GameException.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   └── js/
│   │       │       └── game.js
│   │       └── templates/
│   │           └── game.html
│   └── test/
│       └── java/
│           └── com/
│               └── tictactoe/
│                   └── tictactoe/
│                       └── TictactoeApplicationTests.java
└── README.md
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6.0 or higher
- Any modern web browser

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/kunto-zuro/tictactoe-game
   cd tictactoe-game
   ```

2. Build the project:
   ```
   mvn clean package
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

4. Access the application in your browser:
   ```
   http://localhost:8080
   ```

## How to Play

1. Open the application in your browser
2. Select the board size from the dropdown menu (3x3, 5x5, 7x7, or 9x9)
3. Click "Start New Game"
4. Players take turns placing X and O marks by clicking on empty cells
5. The game automatically detects wins and draws
6. Use the "Reset" button to start a new game with the same board size
7. Return to the main page to change the board size

## API Endpoints

The application provides a RESTful API for game operations:

- `POST /api/game?size={size}` - Create a new game with specified board size
- `GET /api/game/{id}` - Get the current state of a game
- `POST /api/game/{id}/move?row={row}&col={col}` - Make a move
- `POST /api/game/{id}/reset` - Reset a game

## Architecture

The application follows a layered architecture:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains the business logic
- **Model Layer**: Represents the domain entities
- **Exception Handling**: Custom exceptions for clear error messages

## Author

- **Kurniawan Putra Adityanto** - Backend Developer with experience in Java, Spring Boot, and web applications

## Acknowledgements

- Developed as part of the recruitment process for FWD Singapore
- Thanks to the FWD team for the opportunity to demonstrate Java skills through this project