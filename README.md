# Hollomon Java Client

A Java command-line client for the Hollomon trading card game. This was originally developed as a university Java networking assignment and has been adapted and documented as a portfolio project.

## Features

- Connects to the Hollomon server using Java sockets
- Logs in with a username and password
- Reads and displays a player's card collection
- Checks the player's credits
- Lists cards currently available for sale
- Buys cards by ID
- Lists cards for sale
- Sorts cards by rarity, name and ID
- Handles basic invalid menu and number input

## Project structure

- `Main.java` - handles the command-line menu and user input
- `ConnectionManager.java` - handles the socket connection and server commands
- `Card.java` - represents a trading card and defines its sorting order
- `Rank.java` - defines the four card rarities

## Running the project

The project uses only the Java standard library.

Compile:

```bash
javac *.java
```

Run:

```bash
java Main
```

The original Hollomon server is a university service, so the server may not be available outside the university network/VPN.

## Technologies

- Java
- Java Sockets
- Object-Oriented Programming
- Command-line interface

## Notes

This is a learning project rather than a production-ready trading system. The server uses a simple text-based protocol, and the authentication method is the one provided by the original assignment.
