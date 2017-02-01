/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

#include "stdafx.h"

void Translation::transform(ConvexPolygon* polygon, GLint modifier)
{
	polygon->translate(this->translateX * modifier, this->translateY * modifier);
}

Translation::Translation(GLdouble translateX, GLdouble translateY, GLint beginStep, GLint endStep)
	:Transformation(beginStep, endStep)
{
	this->translateX = translateX;
	this->translateY = translateY;
}