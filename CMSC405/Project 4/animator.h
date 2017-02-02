/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/

// Handles animation logic

class Animator
{
public:
	static void step();
	static void reset();
	static GLint getCurrentStep();

private:
	static GLint currentStep;
};