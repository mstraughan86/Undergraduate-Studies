/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

void Rotation::transform(Polyhedron* polyhedron, GLint modifier)
{
	//polygon->rotate(this->angle * modifier);
	glRotated(this->angle * modifier, this->axis.getX(), this->axis.getY(), this->axis.getZ());
}

Rotation::Rotation(GLdouble angle, Vector axis, GLint beginStep, GLint endStep)
	:Transformation(beginStep, endStep)
{
	this->angle = angle;
	this->axis = axis;
}