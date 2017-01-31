/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


class Text : public Graphic
{
public:
	virtual void draw() override; // Implementing the virtual function from Graphic class.
	Text(Color color, Point point, string str);
protected:
private:
	Point point;
	string str;
};