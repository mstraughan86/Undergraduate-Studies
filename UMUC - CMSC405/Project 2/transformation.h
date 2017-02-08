// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

// Interface that defines a transformation

class Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon) const = 0;
};