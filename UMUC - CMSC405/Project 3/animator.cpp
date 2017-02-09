/*
CMSC 405 - Computer Graphics
Project 3
Michael Straughan
December 1, 2015
*/

#include "stdafx.h"

GLint Animator::currentStep = 0;
bool Animator::animationState = false;

void Animator::step()
{
	currentStep++;
}

void Animator::reset()
{
	currentStep = 0;
	animationState = false;
}

GLint Animator::getCurrentStep() 
{
	return currentStep;
}

void Animator::setAnimating(bool b)
{
	animationState = b;
}

bool Animator::getAnimating()
{
	return animationState;
}


