/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


class IsoscelesTriangle : public ConvexPolygon
{
public:
	IsoscelesTriangle(vector<Transformation*> transformations, Color color, GLdouble angle);
	virtual void translate(GLdouble translateX, GLdouble translateY) override;
	virtual void rotate(GLdouble angle) override;
	virtual void scale(GLdouble scaleX, GLdouble scaleY) override;
protected:
private:
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
	vector<Transformation*> transformations;
};