/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

// Handles animation logic

class Animator
{
public:
	static void step();
	static void reset();
	static GLint getCurrentStep();
	static void setAnimating(bool b);
	static bool getAnimating();

private:
	static GLint currentStep;
	static bool animationState;
};