/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


class Scaling : public Transformation
{
public:
	virtual void transform(Polyhedron* polyhedron, GLint currentStep);
	Scaling(GLdouble scaleX, GLdouble scaleY, GLdouble scaleZ, GLint beginStep, GLint endStep);
protected:
private:
	GLdouble scaleX, scaleY, scaleZ;
};