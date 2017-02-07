/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

#include "stdafx.h"

void Parallelogram::draw() const
{
	ConvexPolygon::draw();
}

Parallelogram::Parallelogram(Color color, Point point, Point otherPoint, GLdouble offset)
	: ConvexPolygon(4, color)
{
	vertexArray = new Point[4];
	this->vertexArray[3] = point;
	this->vertexArray[2] = { otherPoint.x + offset, point.y };
	this->vertexArray[0] = { point.x - offset, otherPoint.y };
	this->vertexArray[1] = otherPoint;
	ConvexPolygon::SetVertexArray(this->vertexArray);
}