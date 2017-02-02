/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


class Polyhedron
{
public:
	void draw();
	virtual void drawSpecific() = 0;
protected:
	Polyhedron(Color color, vector<Transformation*> transformations);
private:
	Color color;
	vector<Transformation*> transformations;
};