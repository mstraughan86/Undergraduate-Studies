/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


// Interface that defines a transformation

class Transformation
{
public:
	void transform(Polyhedron* polyhedron);
	virtual void transform(Polyhedron* polyhedron, GLint currentStep) = 0;
	Transformation(GLint beginStep, GLint endStep);
protected:
	GLint beginStep;
	GLint endStep;
};