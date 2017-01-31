// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// Function bodies of lexical analyzer used by the parser to parse the scene definition file 

#include "stdafx.h"

static string punctuation = ",;.()";
static Token punctuationTokens[] = {COMMA, SEMICOLON, PERIOD, LEFT_PAREN, RIGHT_PAREN};
	
// Constructor which must be supplied the name of the scene definition file
	
Lexer::Lexer(string fileName)
{
	in = new ifstream(fileName);
	lineNo = 1;
}

// Returns the next token each time it is called, Tokens are defined in token.h

Token Lexer::getNextToken()
{
	stringstream ss;

	// Skips leading spaces

	while (isspace(in->peek()))
	{
		if (in->peek() == '\n')
			lineNo++;
		in->get();
	}
	
	// Checks for a string token

	if (in->peek() == '"')
	{
		in->get();
		while (in->peek() != '"')
			ss << (char)in->get();
		in->get();
		lexeme = ss.str();
		return STRING;
	}
	
	// Checks for punctuation tokens

	for (unsigned i = 0; i < punctuation.length(); i++)
		if (in->peek() == punctuation[i])
		{
			in->get();
			return punctuationTokens[i];
		}
	
	// Checks for number tokens

	if (isdigit(in->peek()) || in->peek() == '-')
	{
		*in >> number;
		return NUMBER;
	}
	
	// Reads in all alphabetic characters

	while (isalpha(in->peek()))
		ss << (char)in->get();
	lexeme = ss.str();
	if (lexeme.length() == 0)
		return LEXICAL_ERROR;
	
	// Compares lexeme to all keywords

	for (GLint i = 0; i < KEYWORDS ; i++)
		if (lexeme == tokenNames[i])
			return (Token)i;
	
	// Lexemes that are keywords are identifiers
	
	return IDENTIFIER;
}

