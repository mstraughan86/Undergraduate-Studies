/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

class Scaling : public Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon) const override;
	Scaling(GLdouble scaleX, GLdouble scaleY);
protected:
private:
	GLdouble scaleX, scaleY;
};