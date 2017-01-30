/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

class ConvexPolygon : public Graphic
{
public:
	virtual void draw() const override;
protected:
	ConvexPolygon(GLint vertexNumber, Color color);
	void SetVertexArray(Point vertexArray[]);
	void SetCenterPoint(Point center);
private:
	GLint vertexNumber;
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
	Point center;
};