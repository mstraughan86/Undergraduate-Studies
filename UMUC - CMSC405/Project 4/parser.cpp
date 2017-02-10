// CMSC 405 Computer Graphics
// Project 4
// Duane J. Jarc
// August 1, 2013

// Function bodies of parser class that is used to parse the scene definition file

#include "stdafx.h"

// Constructor, which must be supplied the name of the scene definitiomn file

Parser::Parser(string fileName)
{
	lexer = Lexer(fileName);
}

// scene -> SCENE IDENTIFIER number_list polyhedrea END '.'

Scene Parser::parseScene()
{
	verifyNextToken(SCENE_);
	verifyNextToken(IDENTIFIER);
	string window = lexer.getLexeme();
	GLdouble* dimensions = getNumberList(2);
	Scene scene(window, (GLint)dimensions[0], (GLint)dimensions[1]);
	parsePolyhedrons(scene, lexer.getNextToken());
	verifyNextToken(PERIOD);
	return scene;
}

// Parses the following productions

// graphics -> graphic graphics | graphic
// graphic -> polyhedron transformations END | polyhedron ';'
// polyhedron -> CUBE | DODECAHEDRON | TETRAHEDRON

void Parser::parsePolyhedrons(Scene& scene, Token polyhedronToken)
{
	verifyNextToken(COLOR);
	GLdouble* colors = getNumberList(3);
	Color color = {colors[0], colors[1], colors[2]};
	if (polyhedronToken == CUBE)
	{
		vector<Transformation*> transformations;
		if ((token = lexer.getNextToken()) != SEMICOLON)
			parseTransformations(transformations, CUBE);
		Cube* cube = new Cube(color, transformations);
		scene.addPolyhedron(cube);
	}
	else if	(polyhedronToken == DODECAHEDRON)
	{
		vector<Transformation*> transformations;
		if ((token = lexer.getNextToken()) != SEMICOLON)
			parseTransformations(transformations, DODECAHEDRON);
		Dodecahedron* dodecahedron = new Dodecahedron(color, transformations);
		scene.addPolyhedron(dodecahedron);
	}
	else if (polyhedronToken == TETRAHEDRON)
	{
		vector<Transformation*> transformations;
		if ((token = lexer.getNextToken()) != SEMICOLON)
			parseTransformations(transformations, TETRAHEDRON);
		Tetrahedron* tetrahedron = new Tetrahedron(color, transformations);
		scene.addPolyhedron(tetrahedron);
	}
	else
	{
		stringstream message;
		message << "Line: " << lexer.getLineNo() <<  " Unexpected graphic name " << tokenNames[polyhedronToken];
		throw SyntaxError(message.str());
	}
	token = lexer.getNextToken();
	if (token != END)
		parsePolyhedrons(scene, token);
}

// Parses the following productions

// transformations -> transformation transformations | transformation
// transformation -> action steps
// action -> rotation | scaling | translation
// rotation -> ROTATE ANGLE NUMBER AXIS number_list ';'
// scaling -> SCALE number_list ';'
// translation -> TRANSLATE number_list ';'

void Parser::parseTransformations(vector<Transformation*>& transformations, Token polyhedronToken)
{
	GLint* steps;
	GLdouble angle;
	GLdouble* numberList;
	Vector axis;
	switch (token)
	{
		case ROTATE:
			verifyNextToken(ANGLE);
			verifyNextToken(NUMBER);
			angle = lexer.getNumber();
			verifyNextToken(AXIS);
			numberList = getNumberList(3);
			axis = Vector(numberList[0], numberList[1], numberList[2]);
			steps = getSteps();
			transformations.push_back(new Rotation(angle, axis, steps[0], steps[1]));
			break;
		case SCALE:
			numberList = getNumberList(3);
			steps = getSteps();
			transformations.push_back(new Scaling(numberList[0], numberList[1], numberList[2], steps[0], steps[1]));
			break;
		case TRANSLATE:
			numberList = getNumberList(3);
			steps = getSteps();
			transformations.push_back(new Translation(numberList[0], numberList[1], numberList[2], steps[0], steps[1]));
			break;
	}
	verifyCurrentToken(SEMICOLON);
	token = lexer.getNextToken();
	if (token != END)
		parseTransformations(transformations, polyhedronToken);
}

// Parses the follwoing production

// steps -> STEP NUMBER TO NUMBER  ';' | ';'

GLint* Parser::getSteps()
{
	GLint* steps = new GLint[2];
	steps[0] = steps[1] = 0;
	token = lexer.getNextToken();
	if (token == STEP)
	{
		verifyNextToken(NUMBER);
		steps[0] = (GLint)lexer.getNumber();
		verifyNextToken(TO);
		verifyNextToken(NUMBER);
		steps[1] = (GLint)lexer.getNumber();
		token = lexer.getNextToken();
	}
	return steps;
}

// Parses the following productions

// number_list -> '(' numbers ')'
// numbers -> NUMBER | NUMBER ',' numbers

// Returns an array of the numbers in the number list

GLdouble* Parser::getNumberList(GLint count)
{
	GLdouble* list = new GLdouble[count];
	verifyNextToken(LEFT_PAREN);
	for (GLint i = 0; i < count; i++)
	{
		verifyNextToken(NUMBER);
		list[i] = lexer.getNumber();
		token = lexer.getNextToken();
		if (i < count - 1)
			verifyCurrentToken(COMMA);
		else
			verifyCurrentToken(RIGHT_PAREN);
	}
	return list;
}

// Gets the next token and verifies it is the expected token passed in

void Parser::verifyNextToken(Token expectedToken)
{
	token = lexer.getNextToken();
	verifyCurrentToken(expectedToken);
}

// Compares the current token with the expected token

void Parser::verifyCurrentToken(Token expectedToken)
{	
	if (token != expectedToken)
	{
		stringstream message;
		message << "Expecting token " << tokenNames[expectedToken] << " not " << tokenNames[token];
		syntaxError(message.str());
	}
}

// Throws a syntax error with the supplied message

void Parser::syntaxError(string error)
{
	stringstream message;
	message << "Line: " << lexer.getLineNo() << " " << error;
	throw SyntaxError(message.str());
}

