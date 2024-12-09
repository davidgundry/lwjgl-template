# LWGL Demo

This project demonstrates a basic LWJGL starter project, including unit testing with JUnit 5.

## Getting Started

## Configuration

In `pom.xml` we need to uncomment the appropriate line to use the Windows or Linux version of LWJGL

	    <lwjgl.natives>natives-windows</lwjgl.natives>
        <!-- <lwjgl.natives>natives-linux</lwjgl.natives> -->

## Debugging

When this repository is opened in VS code it will prompt to install recommended extensions for Java. Once these are installed, the debugger can be launched in VS Code by pressing F5.

## Building

To install dependencies through Maven run

    mvn install

Tests can be run with the Java Test Runner extension, or using the command:

    mvn test

Build a `.jar` for release with 

    mvn package

**WARNING:** This command will create two `.jar` files in `target/`. The correct one has the suffix `jar-with-dependencies`. This bundles the necessary dependencies for executing the project into the jar.

Test your bundled `jar` 

    java -jar target/lwjgl-demo-1.0-SNAPSHOT-jar-with-dependencies.jar