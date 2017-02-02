/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


class Cube : public Polyhedron
{
public:
	virtual void drawSpecific();
	Cube(Color color, vector<Transformation*> transformations);
protected:
private:
	Point *vertexArray;
};