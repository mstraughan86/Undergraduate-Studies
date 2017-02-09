// CMSC 405 Computer Graphics
// Project 3
// Duane J. Jarc
// August 1, 2013

// List of tokens that can be returned by the lexical analyzer

enum Token {ANGLE, AT, COLOR, END, ISOSCELES, OFFSET, PARALLELOGRAM_,  REGULAR_POLYGON_, ROTATE, SCALE, SCENE_, SIDES, STEP, TEXT, TO,
	TRANSLATE, COMMA, SEMICOLON, PERIOD, LEFT_PAREN, RIGHT_PAREN, IDENTIFIER, NUMBER, STRING, LEXICAL_ERROR};

// Number of tokens that are keywords

static const GLint KEYWORDS = 16;

// Names that correspond to the above tokens

static string tokenNames[] = {"Angle", "at", "Color", "End", "Isosceles", "Offset", "Parallelogram", "RegularPolygon", 
	"Rotate", "Scale", "Scene", "Sides", "Step", "Text", "to", "Translate", ",", ";", ".", "(", ")", "identifier", "number", "string", "lexical error"};
