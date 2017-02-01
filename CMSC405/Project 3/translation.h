/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

class Translation : public Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon, GLint currentStep);
	Translation(GLdouble translateX, GLdouble translateY, GLint beginStep, GLint endStep);
protected:
private:
	GLdouble translateX, translateY;
};