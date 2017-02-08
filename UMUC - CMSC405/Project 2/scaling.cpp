/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

#include "stdafx.h"

void Scaling::transform(ConvexPolygon* polygon) const
{
	polygon->scale(this->scaleX, this->scaleY);
}

Scaling::Scaling(GLdouble scaleX, GLdouble scaleY)
{
	this->scaleX = scaleX;
	this->scaleY = scaleY;
}