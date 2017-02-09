/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


class ConvexPolygon : public Graphic, public Transformable
{
public:
	virtual void draw() override;
protected:
	ConvexPolygon(GLint vertexNumber, Color color);
	void SetVertexArray(Point vertexArray[]);
	void SetTransformations(vector<Transformation*> transformations);
private:
	GLint vertexNumber;
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
	vector<Transformation*> transformations;
};