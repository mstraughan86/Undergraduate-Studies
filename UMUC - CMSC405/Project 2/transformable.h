// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// An interface that defines the three kinds of transformations

class Transformable
{
public:
	virtual void translate(GLdouble translateX, GLdouble translateY) = 0;
	virtual void rotate(GLdouble angle)	= 0;
	virtual void scale(GLdouble scaleX, GLdouble scaleY) = 0;
};