// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// Constants and types used throughout the program

const GLint PI_DEGREES = 180;

// Defines an RBG color object

struct Color
{
	GLdouble red;
	GLdouble green;
	GLdouble blue;
};

// Defines the x and y coordinates of a point

struct Point
{
	GLdouble x;
	GLdouble y;
};

inline GLdouble degreesToRadians(GLdouble degrees)
{
	return M_PI / PI_DEGREES * degrees;
}
