// CMSC 405 Computer Graphics
// Project 1
// Duane J. Jarc
// August 1, 2013

// Class definition for the scene which contains a collection of graphic objects

// The addGraphic is called as the scene definition file is parsed to add graphic objects to the vector of graphics 

class Scene
{
public:
	Scene() {}
	Scene(string name, GLint height, GLint width);
	void createWindow();
	void addGraphic(Graphic* graphic) {graphicObjects.push_back(graphic);}
	void draw();
private:
	string name;
	GLint height, width;
	vector<Graphic*> graphicObjects;
};
