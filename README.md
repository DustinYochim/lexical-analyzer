# Front - Lexical Analyzer for Simple Arithmetic Expressions

Front is a Java implementation of a lexical analyzer system for simple arithmetic expressions. This code was originally written by me for CS 4250 - Programming Languages at UMSL. It's a translation to Java of the lexical analyzer system written in C in Section 4.2 of "Concepts of Programming Languages 12E" by Robert W. Sebesta.

## Overview

The Front class serves as the main entry point for the lexical analyzer. It reads an input data file containing arithmetic expressions and tokenizes them into lexemes along with their respective token codes. This lexical analysis is crucial for further processing in a compiler or interpreter.

## Usage

To use this lexical analyzer:

1. Compile the `Front.java` file.
2. Prepare an input file containing arithmetic expressions (e.g., `front.in`).
3. Run the compiled Java program with the input file as an argument.

Example:

```bash
javac Front.java
java Front
```

## Token Codes

The following token codes are used:

10: Integer Literal
11: Identifier
20: Assignment Operator
21: Addition Operator
22: Subtraction Operator
23: Multiplication Operator
24: Division Operator
25: Left Parenthesis
26: Right Parenthesis
27: Semicolon

## File Structure

Front.java: Contains the main lexical analyzer implementation.
front.in: Input data file containing arithmetic expressions to be analyzed.
