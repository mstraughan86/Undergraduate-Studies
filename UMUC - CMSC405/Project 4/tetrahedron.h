/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


class Tetrahedron : public Polyhedron
{
public:
	virtual void drawSpecific();
	Tetrahedron(Color color, vector<Transformation*> transformations);
protected:
private:
};