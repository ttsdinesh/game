# CLI base Role Playing Game

1) Create a character based on set of available options. The so created character is denoted by 'U'.
2) Move the character around to explore the area.
3) When swept through an enemy (element of a different clan) U loses health.
4) When swept through character of same clan (element from the same clan), U gains health.
5) Game ends (stage completes) when swept through all the characters or when the health becomes less than or equal to 0.

## Getting Started

### Prerequisites

- Maven
- JDK 1.8

### Compile and package
```
mvn clean package -DskipTests
```

### Execute  
```
java -jar target/rpg-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

### Execute test cases
 
```
mvn clean test
```