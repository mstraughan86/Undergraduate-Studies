/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

class Rotation : public Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon, GLint currentStep);
	Rotation(GLdouble angle, GLint beginStep, GLint endStep);
protected:
private:
	GLdouble angle;
};