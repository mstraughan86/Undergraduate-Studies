// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// Function bodies of parser class that is used to parse the scene definition file

#include "stdafx.h"

// Constructor, which must be supplied the name of the scene definitiomn file

Parser::Parser(string fileName)
{
	lexer = Lexer(fileName);
}

// Parses the production

// scene -> SCENE IDENTIFIER number_list graphics END '.'

Scene Parser::parseScene()
{
	verifyNextToken(SCENE_);
	verifyNextToken(IDENTIFIER);
	string window = lexer.getLexeme();
	GLdouble* dimensions = getNumberList(2);
	Scene scene(window, (GLint)dimensions[0], (GLint)dimensions[1]);
	parseGraphics(scene, lexer.getNextToken());
	verifyNextToken(PERIOD);
	return scene;
}

// Parses the following productions

// graphics -> graphic graphics | graphic
// graphic -> text | transformable_graphic transformations END
// transformable_graphic -> isosceles | parallelogram | regular_polygon
// isosceles -> ISOSCELES COLOR number_list ANGLE NUMBER ';'
// parallelogram -> PARALLELOGRAM COLOR number_list ANGLE NUMBER ';'
// regular_polygon -> REGULAR_POLYGON COLOR number_list SIDES NUMBER RADIUS NUMBER ';' 
// text -> TEXT COLOR number_list AT number_list STRING ';'

void Parser::parseGraphics(Scene& scene, Token graphicToken)
{
	GLdouble angle;

	verifyNextToken(COLOR);
	GLdouble* colors = getNumberList(3);
	Color color = {colors[0], colors[1], colors[2]};
	if (graphicToken == ISOSCELES)
	{
		verifyNextToken(ANGLE);
		verifyNextToken(NUMBER);
		angle = lexer.getNumber();
		verifyNextToken(SEMICOLON);
		token = lexer.getNextToken();
		vector<Transformation*> transformations;
		parseTransformations(transformations);
		IsoscelesTriangle* triangle = new IsoscelesTriangle(transformations, color, angle);
		scene.addGraphic(triangle);
	}
	else if	(graphicToken == PARALLELOGRAM_)
	{
		verifyNextToken(ANGLE);
		verifyNextToken(NUMBER);
		angle = lexer.getNumber();
		verifyNextToken(SEMICOLON);
		token = lexer.getNextToken();
		vector<Transformation*> transformations;
		parseTransformations(transformations);
		Parallelogram* parallelogram = new Parallelogram(transformations, color, angle);
		scene.addGraphic(parallelogram);
	}
	else if (graphicToken == REGULAR_POLYGON_)
	{
		verifyNextToken(SIDES);
		verifyNextToken(NUMBER);
		GLint vertices = (GLint)lexer.getNumber();
		verifyNextToken(SEMICOLON);
		token = lexer.getNextToken();
		vector<Transformation*> transformations;
		parseTransformations(transformations);
		RegularPolygon* polygon = new RegularPolygon(transformations, color, vertices);
		scene.addGraphic(polygon);
	}
	else if (graphicToken == TEXT)
	{
		verifyNextToken(AT);
		GLdouble* location = getNumberList(2);
		Point point = {location[0], location[1]};
		verifyNextToken(STRING);
		verifyNextToken(SEMICOLON);
		Text* text = new Text(color, point, lexer.getLexeme());
		scene.addGraphic(text);
	}
	else
	{
		stringstream message;
		message << "Line: " << lexer.getLineNo() <<  "Unexpecting graphic name " << tokenNames[graphicToken];
		throw SyntaxError(message.str());
	}
	token = lexer.getNextToken();
	if (token != END)
		parseGraphics(scene, token);
}

// Parses the following productions

// transformations -> transformation transformations | transformation
// transformation -> rotation | scaling | translation
// rotation -> ROTATE ANGLE NUMBER ';'
// scaling -> SCALE number_list ';'
// translation -> TRANSLATE number_list ';'

void Parser::parseTransformations(vector<Transformation*>& transformations)
{
	GLdouble* numberPair;

	switch (token)
	{
		case ROTATE:
			verifyNextToken(ANGLE);
			verifyNextToken(NUMBER);
			transformations.push_back(new Rotation(lexer.getNumber()));
			break;
		case SCALE:
			numberPair = getNumberList(2);
			transformations.push_back(new Scaling(numberPair[0], numberPair[1]));
			break;
		case TRANSLATE:
			numberPair = getNumberList(2);
			transformations.push_back(new Translation(numberPair[0], numberPair[1]));
			break;
	}
	verifyNextToken(SEMICOLON);
	token = lexer.getNextToken();
	if (token != END)
		parseTransformations(transformations);
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

