/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

#include "stdafx.h"

void Rotation::transform(ConvexPolygon* polygon) const
{
	polygon->rotate(this->angle);
}

Rotation::Rotation(GLdouble angle)
{
	this->angle = angle;
}