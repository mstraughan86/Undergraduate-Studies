/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

#include "stdafx.h"

void RegularPolygon::draw() const
{
	ConvexPolygon::draw();
}

RegularPolygon::RegularPolygon(Color color, GLint vertices, Point center, GLdouble radius)
	: ConvexPolygon(vertices, color)
{
	this->vertexArray = new Point[vertices];

	for (int i = 0; i < vertices; i++)
	{
		GLdouble x = radius * cos(2 * PI * (i + 1) / vertices) + center.x;
		GLdouble y = radius * sin(2 * PI * (i + 1) / vertices) + center.y;
		this->vertexArray[i] = { x, y };
	}

	ConvexPolygon::SetVertexArray(this->vertexArray);
	ConvexPolygon::SetCenterPoint(center);
}