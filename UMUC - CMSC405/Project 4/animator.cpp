/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

GLint Animator::currentStep = 0;

void Animator::step()
{
	currentStep++;
}

void Animator::reset()
{
	currentStep = 0;
}

GLint Animator::getCurrentStep() 
{
	return currentStep;
}


