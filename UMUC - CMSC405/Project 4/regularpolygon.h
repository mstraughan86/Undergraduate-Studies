/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

class RegularPolygon : public ConvexPolygon
{
public:
	virtual void draw() const override;
	RegularPolygon(Color color, GLint vertices, Point point, GLdouble radius);
protected:
private:
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
};