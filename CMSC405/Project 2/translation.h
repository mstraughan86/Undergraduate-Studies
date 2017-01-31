/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

class Translation : public Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon) const override;
	Translation(GLdouble translateX, GLdouble translateY);
protected:
private:
	GLdouble translateX, translateY;
};