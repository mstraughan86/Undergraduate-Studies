// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// Exception class that defines syntax errors in the scene definition file

class SyntaxError: public exception 
{
public:
	SyntaxError(string message) : exception(message.c_str()) {}
};
