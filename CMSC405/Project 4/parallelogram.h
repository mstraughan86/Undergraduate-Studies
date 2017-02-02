/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

class Parallelogram : public ConvexPolygon
{
public:
	virtual void draw() const override;
	Parallelogram(Color color, Point point, Point otherPoint, GLdouble offset);
protected:
private:
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
};