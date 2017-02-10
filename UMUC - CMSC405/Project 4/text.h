/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

class Text : public Graphic
{
public:
	virtual void draw() const override; // Implementing the virtual function from Graphic class.
	Text(Color color, Point point, string str);
protected:
private:
	Point point;
	string str;
};