// CMSC 405 Computer Graphics
// Project 1
// Duane J. Jarc
// August 1, 2013

// An abstract class that defines all graphic objects

// All subclasses must define the abstract (pure virtual) function draw

class Graphic
{
public:
	virtual void draw() const = 0;
protected:
	Graphic(Color color);
	void colorDrawing() const;
private:
	Color color;
};