// CMSC 405 Computer Graphics
// Project 4
// Duane J. Jarc
// August 1, 2013

// Class definition of a 3 element vector

class Vector
{
public:
	Vector() {}
	Vector::Vector(Point point);
	Vector(GLdouble x, GLdouble y, GLdouble z);
	Vector(GLdouble values[]);
	GLdouble getX()	{return x;}
	GLdouble getY() {return y;}
	GLdouble getZ()	{return z;}
	Vector Vector::normalize();
	friend Vector operator-(const Vector& vector);
	friend Vector operator+(const Vector& left, const Vector& right);
	friend GLdouble operator*(const Vector& left, const Vector& right);
	friend Vector operator^(const Vector& left, const Vector& right);
	friend Vector operator*(GLdouble scalar, const Vector& vector);
	friend bool operator==(const Vector& left, const Vector& right);
	friend ostream& operator<<(ostream& out, const Vector& vector);
private:
	GLdouble x, y, z;
};
