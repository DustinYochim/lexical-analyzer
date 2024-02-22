import java.io.*;

/* Front is a lexical analyzer system for simple arithmetic expressions */
/* Written by Dustin Yochim for CS 4250 - Programming Languages at UMSL */
/* The following code is a translation to Java of the lexical analyzer system written in C in Section 4.2
    of Concepts of Programming Languages 12E by Robert W. Sebesta. Every statement is an attempt to
    translate the C statement into an equivalent Java statement */

public class Front {

    /* Global decorations */
    /* Variables */
    private static int charClass;
    private static final char[] lexeme = new char[100];
    private static char nextChar;
    private static int lexLen;
    private static int token;
    private static int nextToken;
    private static BufferedReader in_fp;
    private static final int EOF = -1;


    /* Character classes */
    private static final int LETTER = 0;
    private static final int DIGIT = 1;
    private static final int UNKNOWN = 99;

    /* Token codes */
    private static final int INT_LIT = 10;
    private static final int IDENT = 11;
    private static final int ASSIGN_OP = 20;
    private static final int ADD_OP = 21;
    private static final int SUB_OP = 22;
    private static final int MULT_OP = 23;
    private static final int DIV_OP = 24;
    private static final int LEFT_PAREN = 25;
    private static final int RIGHT_PAREN = 26;
    private static final int SEMI_COLON = 27;

    /*************************************************************************/

    /* main driver */
    public static void main(String[] args) {
        /* Open the input data file and process its contents */
        try {
            in_fp = new BufferedReader(new FileReader("front.in"));
            getChar();
            do {
                lex();
            } while (nextToken != EOF);
        } catch (IOException e) {
            System.out.println("ERROR - cannot open front.in");
        }
    }
    /************************************************************************/
    /* lookup = a function to lookup operators and parentheses
        and return the token */
    static void lookup(char ch) {
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = SUB_OP;
                break;
            case '*':
                addChar();
                nextToken = MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            case ';':
                addChar();
                nextToken = SEMI_COLON;
                break;
            case '=':
                addChar();
                nextToken = ASSIGN_OP;
                break;
            default:
                addChar();
                nextToken = EOF;
                break;
        }
    }
    /**********************************************************************/
    /* addChar - a function to add nextChar to lexeme */
    static void addChar() {
        if (lexLen <= 98) {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        } else {
            System.out.println("Error - lexeme is too long.");
        }
    }
    /***********************************************************************/
    /* getChar - a function to get the next character of input and
        determine its character class */
    static void getChar() throws IOException {
        int charVal = in_fp.read();
        if (charVal == EOF) {
            charClass = EOF;
        } else {
            nextChar = (char) charVal;
            if (Character.isLetter(nextChar)) {
                charClass = LETTER;
            } else if (Character.isDigit(nextChar)) {
                charClass = DIGIT;
            } else {
                charClass = UNKNOWN;
            }
        }
    }
    /*********************************************************************/
    /* getNonBlank - a function to call getChar until it returns a non-whitespace character */
    static void getNonBlank() throws IOException {
        while (Character.isSpaceChar(nextChar)) {
            getChar();
        }
    }
    /*********************************************************************/
    /* lex - a simple lexical analyzer for arithmetic expressions */
    static void lex() throws IOException {
        lexLen = 0;
        getNonBlank();
        switch (charClass) {
            /* Parse identifiers */
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;
            /* Parse integer literals */
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;
            /* Parentheses and operators */
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;
            /* EOF */
            case EOF:
                nextToken = EOF;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 0;
                break;
        } /* End of Switch */
        if (nextToken != EOF) {
            System.out.print("Next token is: " + nextToken + ", Next lexeme is ");
            for (int i = 0; i < lexLen; i++) {
                System.out.print(lexeme[i]);
            }
            // System.out.println();
        } else {
            System.out.printf("Next token is: %d, Next lexeme is EOF\n", EOF);
        }
        System.out.println();
    } /* End of function lex */
}
