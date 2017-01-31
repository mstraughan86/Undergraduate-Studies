/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


#include "stdafx.h"

void IsoscelesTriangle::translate(GLdouble translateX, GLdouble translateY)
{
	glTranslated(translateX, translateY, 0);
}

void IsoscelesTriangle::rotate(GLdouble angle)
{
	glRotated(angle, 0, 0, 1);
}

void IsoscelesTriangle::scale(GLdouble scaleX, GLdouble scaleY)
{
	glScaled(scaleX, scaleY, 1);
}

IsoscelesTriangle::IsoscelesTriangle(vector<Transformation*> transformations, Color color, GLdouble angle)
	: ConvexPolygon(3 , color)
{
	this->transformations = transformations;
	vertexArray = new Point[3];

	int height = 1;
	double base = 2 * height * (double)tan(degreesToRadians(angle) / 2);
	Point center = { 0, 0 };

	this->vertexArray[0] = { center.x, center.y + height };
	this->vertexArray[1] = { center.x + base/2, center.y };
	this->vertexArray[2] = { center.x - base/2, center.y };

	ConvexPolygon::SetVertexArray(this->vertexArray);
	ConvexPolygon::SetTransformations(this->transformations);
}