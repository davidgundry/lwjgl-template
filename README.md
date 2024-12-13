# LWJGL Boilerplate Project

This project demonstrates a basic LWJGL starter project. It is intended for learning the basics of LWJGL and writing small rendering demos. Write your own implementations of the abstract class `Game`

* Testing with JUnit5
* Command line parameters are parsed in `Main` using JCommander
* GLFW window-manager separated from game code in class `Window` 

## Getting Started

To install dependencies through Maven run

    mvn install

Tests can be run with the Java Test Runner extension, or using the command:

    mvn test

Build a `.jar` for release with 

    mvn install

**WARNING:** This command will create two `.jar` files in `target/`. The correct one has the suffix `jar-with-dependencies`. This bundles the necessary dependencies for executing the project into the jar.

Test your bundled `jar` 

    java -jar target/lwjgl-demo-1.0-SNAPSHOT-jar-with-dependencies.jar

## Debugging

When this repository is opened in VS code it will prompt to install recommended extensions for Java. Once these are installed, the debugger can be launched in VS Code by pressing F5.
