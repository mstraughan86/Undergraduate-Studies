// CMSC 405 Computer Graphics
// Project 1
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
// graphic -> isoceles | parallelogram | regular_polygon |text
// isosceles -> ISOSCELES COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
// parallelogram -> PARALLELOGRAM COLOR number_list AT number_list number_list OFFSET NUMBER ';'
// regular_polygon -> REGULAR_POLYGON COLOR number_list AT number_list SIDES NUMBER RADIUS NUMBER ';' 
// text -> TEXT COLOR number_list AT number_list STRING ';'

void Parser::parseGraphics(Scene& scene, Token graphicToken)
{
	GLdouble height, width, offset, radius;

	verifyNextToken(COLOR);
	GLdouble* colors = getNumberList(3);
	Color color = {colors[0], colors[1], colors[2]};
	verifyNextToken(AT);
	GLdouble* location = getNumberList(2);
	Point point = {location[0], location[1]};
	if (graphicToken == ISOSCELES)
	{
		verifyNextToken(HEIGHT);
		verifyNextToken(NUMBER);
		height = lexer.getNumber();
		verifyNextToken(WIDTH);
		verifyNextToken(NUMBER);
		width = lexer.getNumber();
		IsoscelesTriangle* triangle = new IsoscelesTriangle(color, point, height, width);
		scene.addGraphic(triangle);
	}
	else if	(graphicToken == PARALLELOGRAM_)
	{
		GLdouble* lowerRight = getNumberList(2);
		Point otherPoint = {lowerRight[0], lowerRight[1]};
		verifyNextToken(OFFSET);
		verifyNextToken(NUMBER);
		offset = lexer.getNumber();
		Parallelogram* parallelogram = new Parallelogram(color, point, otherPoint, offset);
		scene.addGraphic(parallelogram);
	}
	else if (graphicToken == REGULAR_POLYGON_)
	{
		verifyNextToken(SIDES);
		verifyNextToken(NUMBER);
		GLint vertices = (GLint)lexer.getNumber();
		verifyNextToken(RADIUS);
		verifyNextToken(NUMBER);
		radius = lexer.getNumber();
		RegularPolygon* polygon = new RegularPolygon(color, vertices, point, radius);
		scene.addGraphic(polygon);
	}
	else if (graphicToken == TEXT)
	{
		verifyNextToken(STRING);
		Text* text = new Text(color, point, lexer.getLexeme());
		scene.addGraphic(text);
	}
	else if (graphicToken == COLOR_DOT_)
	{
		verifyNextToken(RADIUS);
		verifyNextToken(NUMBER);
		radius = lexer.getNumber();

		for (double r = 0; r < 256; r += radius)
		{
			for (double g = 0; g < 256; g += radius)
			{
				for (double b = 0; b < 256; b += radius)
				{
					color = { r / (double)255.0, g / (double)255.0, b / (double)255.0 };
					point = { ((b + g) / 2.0 - r) + location[0], ((g - b)) + location[1] };
					RegularPolygon* polygon = new RegularPolygon(color, 32, point, radius);
					scene.addGraphic(polygon);
				}

			}
		}
	}
	else
	{
		stringstream message;
		message << "Line: " << lexer.getLineNo() <<  "Unexpecting graphic name " << tokenNames[graphicToken];
		throw SyntaxError(message.str());
	}
	verifyNextToken(SEMICOLON);
	token = lexer.getNextToken();
	if (token != END)
		parseGraphics(scene, token);
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
// Throws a syntax error expection is they do not match

void Parser::verifyCurrentToken(Token expectedToken)
{	
	stringstream message;
	message << "Line: " << lexer.getLineNo() << " Expecting token " << tokenNames[expectedToken] << " not " << tokenNames[token];
	if (token != expectedToken)
		throw SyntaxError(message.str());
}

