/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/

#include "stdafx.h"

void Transformation::transform(Polyhedron* polyhedron)
{
	GLint currentStep = Animator::getCurrentStep();

	if (endStep >= currentStep)
	{
		if (currentStep >= beginStep)
		{
			transform(polyhedron, (currentStep - beginStep + 1));
		}
	}
	else {
		transform(polyhedron, (endStep - beginStep + 1));
	}
}

Transformation::Transformation(GLint beginStep, GLint endStep)
{
	this->beginStep = beginStep;
	this->endStep = endStep;
}