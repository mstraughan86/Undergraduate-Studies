// CMSC 405 Computer Graphics
// Project 3
// Duane J. Jarc
// August 1, 2013

// Class definition for the parser that is used to parse the scene definition file

class Parser
{
public:
	Parser(string filename);
	Scene parseScene();
private:
	Lexer lexer;
	Token token;
	void parseGraphics(Scene& scene, Token graphicToken);
	void parseTransformations(vector<Transformation*>& transformations);
	GLdouble* getNumberList(GLint count);
	GLint* getSteps();
	void verifyNextToken(Token expectedToken);
	void verifyCurrentToken(Token expectedToken);
	void syntaxError(string);
};