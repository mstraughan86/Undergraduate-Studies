/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

class Scaling : public Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon, GLint currentStep);
	Scaling(GLdouble scaleX, GLdouble scaleY, GLint beginStep, GLint endStep);
protected:
private:
	GLdouble scaleX, scaleY;
};