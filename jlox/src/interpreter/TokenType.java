package interpreter;

public enum TokenType {
    // Single char tokens
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,
    Q_MARK, COLON,

    // One to two char tokens
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords
    AND, OR,
    CLASS, SUPER, THIS, FUN,
    IF, ELSE, FOR, WHILE,
    FALSE, TRUE,
    PRINT, RETURN, VAR, NIL,

    EOF
}
