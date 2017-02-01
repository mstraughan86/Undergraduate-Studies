/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

// An interface that defines the three kinds of transformations

class Transformable
{
public:
	virtual void translate(GLdouble translateX, GLdouble translateY) = 0;
	virtual void rotate(GLdouble angle)	= 0;
	virtual void scale(GLdouble scaleX, GLdouble scaleY) = 0;
};