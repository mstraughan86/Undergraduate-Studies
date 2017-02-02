/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

void Translation::transform(Polyhedron* polyhedron, GLint modifier)
{
	//polygon->translate(this->translateX * modifier, this->translateY * modifier);
	glTranslated(this->translateX * modifier, this->translateY * modifier, this->translateZ * modifier);
}

Translation::Translation(GLdouble translateX, GLdouble translateY, GLdouble translateZ, GLint beginStep, GLint endStep)
	:Transformation(beginStep, endStep)
{
	this->translateX = translateX;
	this->translateY = translateY;
	this->translateZ = translateZ;

}