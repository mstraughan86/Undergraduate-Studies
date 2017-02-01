/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

#include "stdafx.h"

void Transformation::transform(ConvexPolygon* polygon)
{
	if (Animator::getAnimating())
	{
		GLint currentStep = Animator::getCurrentStep();

		if (endStep >= currentStep)
		{
			if (currentStep >= beginStep)
			{
				transform(polygon, (currentStep - beginStep + 1));
			}
		}
		else {
			transform(polygon, (endStep - beginStep + 1));
		}
	}
}

Transformation::Transformation(GLint beginStep, GLint endStep)
{
	this->beginStep = beginStep;
	this->endStep = endStep;
}