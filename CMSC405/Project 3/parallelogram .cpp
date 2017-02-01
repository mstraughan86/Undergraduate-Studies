/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


#include "stdafx.h"

void Parallelogram::translate(GLdouble translateX, GLdouble translateY)
{
	GLdouble translate[16] = {
		1, 0, 0, 0,
		0, 1, 0, 0,
		0, 0, 1, 0,
		translateX, translateY, 0, 1
	};
	glMultMatrixd(translate);
}

void Parallelogram::rotate(GLdouble angle)
{
	GLdouble rotate[16] = {
		cos(degreesToRadians(angle)), sin(degreesToRadians(angle)), 0, 0,
		-1 * sin(degreesToRadians(angle)), cos(degreesToRadians(angle)), 0, 0,
		0, 0, 1, 0,
		0, 0, 0, 1
	};
	glMultMatrixd(rotate);
}

void Parallelogram::scale(GLdouble scaleX, GLdouble scaleY)
{
	GLdouble scale[16] = {
		scaleX, 0, 0, 0,
		0, scaleY, 0, 0,
		0, 0, 1, 0,
		0, 0, 0, 1
	};
	glMultMatrixd(scale);
}


Parallelogram::Parallelogram(vector<Transformation*> transformations, Color color, GLdouble angle)
	: ConvexPolygon(4, color)
{
	this->transformations = transformations;

	int sideA = 1;
	int sideB = 1;
	Point center = { 0, 0 };
	double height = sideA * sin(degreesToRadians(angle));
	double offset = sideA * sin(degreesToRadians(90 - angle));

	vertexArray = new Point[4];
	this->vertexArray[0] = center;
	this->vertexArray[1] = { center.x + sideB, center.y};
	this->vertexArray[2] = { center.x + sideB + offset, center.y + height };
	this->vertexArray[3] = { center.x + offset, center.y + height};

	ConvexPolygon::SetVertexArray(this->vertexArray);
	ConvexPolygon::SetTransformations(this->transformations);
}