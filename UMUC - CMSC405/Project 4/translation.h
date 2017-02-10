/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


class Translation : public Transformation
{
public:
	virtual void transform(Polyhedron* polyhedron, GLint currentStep);
	Translation(GLdouble translateX, GLdouble translateY, GLdouble translateZ, GLint beginStep, GLint endStep);
protected:
private:
	GLdouble translateX, translateY, translateZ;
};