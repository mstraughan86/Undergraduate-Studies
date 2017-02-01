/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

#include "stdafx.h"

void Rotation::transform(ConvexPolygon* polygon, GLint modifier)
{
	polygon->rotate(this->angle * modifier);
}

Rotation::Rotation(GLdouble angle, GLint beginStep, GLint endStep)
	:Transformation(beginStep, endStep)
{
	this->angle = angle;
}