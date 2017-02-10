// CMSC 405 Computer Graphics
// Project 4
// Duane J. Jarc
// August 1, 2013

// Function bodies of class that defines a 3 element vector

#include "stdafx.h"

// Constructor that defines a vector given the coordinates

Vector::Vector(GLdouble x, GLdouble y, GLdouble z)
{
	this->x = x;
	this->y = y;
	this->z = z;
}

// Constructor that defines a vector given a point

Vector::Vector(Point point)
{
	x = point.x;
	y = point.y;
	z = point.z;
}

// Constructor that defines a vector given an array of 3 values

Vector::Vector(GLdouble values[])
{
	x = values[0];
	y = values[1];
	z = values[2];
}

// Transforms vector into a unit vector

Vector Vector::normalize()
{
	GLdouble length = sqrt(x * x + y * y + z * z);
	return Vector(x / length, y / length, z / length);
}

// Negates a vector

Vector operator-(const Vector& vector)
{
	return Vector(-vector.x, -vector.y, -vector.z);
}

// Computes the sum of two vectors

Vector operator+(const Vector& left, const Vector& right)
{
	return Vector(left.x + right.x, left.y + right.y,  left.z + right.z);
}

// Computes the dot product of two vectors

GLdouble operator*(const Vector& left, const Vector& right)
{
	return left.x * right.x + left.y * right.y + left.z * right.z;
}

// Computes the cross product of two vectors

Vector operator^(const Vector& left, const Vector& right)
{
	return Vector(left.y * right.z - left.z * right.y, left.z * right.x - left.x * right.z, left.x * right.y - left.y * right.x);
}
// Computes product of a scalar and a vector

Vector operator*(GLdouble scalar, const Vector& vector)
{
	return Vector(scalar * vector.x, scalar * vector.y, scalar * vector.z);
}

// Performs a deep comparison of of two vectors

bool operator==(const Vector& left, const Vector& right)
{
	return left.x == right.x && left.y == right.y && left.z == right.z;
}

// Outputs a 3 element vector

ostream& operator<<(ostream& out, const Vector& vector)
{
	return out << vector.x << ' ' << vector.y << ' ' << vector.z << endl;
}