/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

class Rotation : public Transformation
{
public:
	virtual void transform(ConvexPolygon* polygon) const override;
	Rotation(GLdouble angle);
protected:
private:
	GLdouble angle;
};