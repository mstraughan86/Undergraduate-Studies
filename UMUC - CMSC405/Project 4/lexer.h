// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// Class definition for the lexical analyzer used by the parser

// The getLexeme function is to be called after a STRING or IDENTIFIER token is returned, It returns
// the string or identifier corresponding to that token

// The getNumber function is to be called after a NUMBER token is returned

// The getLineNo function returns the current line number which is used when syntax errors occur

class Lexer
{
public:
	Lexer() {}
	Lexer(string filename);
	Token getNextToken();
	string getLexeme() const {return lexeme;}
	GLdouble getNumber() const {return number;}
	GLint getLineNo() const {return lineNo;}
private:
	ifstream* in;
	GLint lineNo;
	string lexeme;
	GLdouble number;
};