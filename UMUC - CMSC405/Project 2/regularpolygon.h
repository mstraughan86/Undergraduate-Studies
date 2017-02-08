/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

class RegularPolygon : public ConvexPolygon
{
public:
	RegularPolygon(vector<Transformation*> transformations, Color color, GLint vertices);
	virtual void translate(GLdouble translateX, GLdouble translateY) override;
	virtual void rotate(GLdouble angle) override;
	virtual void scale(GLdouble scaleX, GLdouble scaleY) override;
protected:
private:
	//vertexArray is defined as a pointer to make the array size defined during runtime.
	Point *vertexArray;
	vector<Transformation*> transformations;
};