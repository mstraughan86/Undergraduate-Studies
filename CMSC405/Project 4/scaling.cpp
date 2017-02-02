/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

void Scaling::transform(Polyhedron* polyhedron, GLint modifier)
{
	//polyhedron->scale(pow(this->scaleX, modifier), pow(this->scaleY, modifier));
	glScaled(pow(this->scaleX, modifier),
		pow(this->scaleY, modifier),
		pow(this->scaleZ, modifier));
}

Scaling::Scaling(GLdouble scaleX, GLdouble scaleY, GLdouble scaleZ, GLint beginStep, GLint endStep)
	:Transformation(beginStep, endStep)
{
	this->scaleX = scaleX;
	this->scaleY = scaleY;
	this->scaleZ = scaleZ;
}