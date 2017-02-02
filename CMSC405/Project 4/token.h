// CMSC 405 Computer Graphics
// Project 4
// Duane J. Jarc
// August 1, 2013

// List of tokens that can be returned by the lexical analyzer

enum Token {ANGLE, AXIS, COLOR, CUBE, DODECAHEDRON, END, ROTATE, SCALE, SCENE_, STEP, TETRAHEDRON, TO,
	TRANSLATE, COMMA, SEMICOLON, PERIOD, LEFT_PAREN, RIGHT_PAREN, IDENTIFIER, NUMBER, STRING, LEXICAL_ERROR};

// Number of tokens that are keywords

static const GLint KEYWORDS = 13;

// Names that correspond to the above tokens

static string tokenNames[] = {"Angle", "Axis", "Color", "Cube", "Dodecahedron", "End", "Rotate", "Scale", "Scene", "Step", "Tetrahedron",
	"to", "Translate", ",", ";", ".", "(", ")", "identifier", "number", "string", "lexical error"};
