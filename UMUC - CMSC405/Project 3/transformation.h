/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

// Interface that defines a transformation

class Transformation
{
public:
	void transform(ConvexPolygon* polygon);
	virtual void transform(ConvexPolygon* polygon, GLint currentStep) = 0;
	Transformation(GLint beginStep, GLint endStep);
protected:
	GLint beginStep;
	GLint endStep;
};