/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/

#include "stdafx.h"

void Translation::transform(ConvexPolygon* polygon) const
{
	polygon->translate(this->translateX, this->translateY);
}

Translation::Translation(GLdouble translateX, GLdouble translateY)
{
	this->translateX = translateX;
	this->translateY = translateY;
}