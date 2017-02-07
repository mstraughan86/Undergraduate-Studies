/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

#include "stdafx.h"

void IsoscelesTriangle::draw() const
{
	ConvexPolygon::draw();
}

IsoscelesTriangle::IsoscelesTriangle(Color color, Point point, GLdouble height, GLdouble width) 
	: ConvexPolygon(3 , color)
{
	vertexArray = new Point[3];
	this->vertexArray[0] = point;
	this->vertexArray[1] = { point.x + (width/2), point.y - height};
	this->vertexArray[2] = { point.x - (width / 2), point.y - height };
	ConvexPolygon::SetVertexArray(this->vertexArray);
}