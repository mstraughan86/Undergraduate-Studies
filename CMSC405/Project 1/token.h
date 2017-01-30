// CMSC 405 Computer Graphics
// Project 1
// Duane J. Jarc
// August 1, 2013

// List of tokens that can be returned by the lexical analyzer

enum Token {COLOR_DOT_, AT, COLOR, END,	HEIGHT,	ISOSCELES, OFFSET, PARALLELOGRAM_, RADIUS, REGULAR_POLYGON_, SCENE_, SIDES, TEXT, WIDTH, COMMA, 
	SEMICOLON, PERIOD, LEFT_PAREN, RIGHT_PAREN, IDENTIFIER, NUMBER, STRING, LEXICAL_ERROR};

// Number of tokens that are keywords

static const GLint KEYWORDS = 14;

// Names that correspond to the above tokens

static string tokenNames[] = {"ColorDot", "at", "Color", "End", "Height", "Isosceles", "Offset", "Parallelogram", "Radius", "RegularPolygon", 
	"Scene", "Sides", "Text", "Width", ",", ";", ".", "(", ")", "identifier", "number", "string", "lexical error"};
