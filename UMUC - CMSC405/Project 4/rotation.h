/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


class Rotation : public Transformation
{
public:
	virtual void transform(Polyhedron* polyhedron, GLint currentStep);
	Rotation(GLdouble angle, Vector axis, GLint beginStep, GLint endStep);
protected:
private:
	GLdouble angle;
	Vector axis;
};