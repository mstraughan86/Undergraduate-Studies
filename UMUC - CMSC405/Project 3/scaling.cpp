/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

#include "stdafx.h"

void Scaling::transform(ConvexPolygon* polygon, GLint modifier)
{
	polygon->scale(pow(this->scaleX, modifier),pow(this->scaleY, modifier));
}

Scaling::Scaling(GLdouble scaleX, GLdouble scaleY, GLint beginStep, GLint endStep)
	:Transformation(beginStep, endStep)
{
	this->scaleX = scaleX;
	this->scaleY = scaleY;
}