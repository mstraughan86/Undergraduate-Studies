// CMSC 405 Computer Graphics
// Project 4
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
	void parsePolyhedrons(Scene& scene, Token polyhedronToken);
	void parseTransformations(vector<Transformation*>& transformations, Token polyhedronToken);
	GLdouble* getNumberList(GLint count);
	GLint* getSteps();
	void verifyNextToken(Token expectedToken);
	void verifyCurrentToken(Token expectedToken);
	void syntaxError(string);
};