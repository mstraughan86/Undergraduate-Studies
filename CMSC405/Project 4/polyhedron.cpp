/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

void Polyhedron::draw()
{
	glColor3d(color.red, color.green, color.blue);
	glPushMatrix();
	for each (Transformation* transformation in this->transformations)
		transformation->transform(this);
	this->drawSpecific();
	glPopMatrix();
}

Polyhedron::Polyhedron(Color color, vector<Transformation*> transformations)
{
	this->color = color;
	this->transformations = transformations;
}