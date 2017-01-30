/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

class IsoscelesTriangle : public ConvexPolygon
{
public:
	virtual void draw() const override;
	IsoscelesTriangle(Color color, Point point, GLdouble height, GLdouble width);
protected:
private:
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
};