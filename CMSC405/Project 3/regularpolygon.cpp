/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


#include "stdafx.h"

void RegularPolygon::translate(GLdouble translateX, GLdouble translateY)
{
	glTranslated(translateX, translateY, 0);
}

void RegularPolygon::rotate(GLdouble angle)
{
	glRotated(angle, 0, 0, 1);
}

void RegularPolygon::scale(GLdouble scaleX, GLdouble scaleY)
{
	glScaled(scaleX, scaleY, 1);
}

RegularPolygon::RegularPolygon(vector<Transformation*> transformations, Color color, GLint vertices)
	: ConvexPolygon(vertices, color)
{
	this->transformations = transformations;
	
	Point center = { 0, 0 };
	GLdouble radius = 1;

	this->vertexArray = new Point[vertices];

	for (int i = 0; i < vertices; i++)
	{
		GLdouble x = radius * cos(2 * M_PI * (i + 1) / vertices) + center.x;
		GLdouble y = radius * sin(2 * M_PI * (i + 1) / vertices) + center.y;
		this->vertexArray[i] = { x, y };
	}

	ConvexPolygon::SetVertexArray(this->vertexArray);
	ConvexPolygon::SetTransformations(this->transformations);
}