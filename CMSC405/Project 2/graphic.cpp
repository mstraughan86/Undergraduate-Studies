// CMSC 405 Computer Graphics
// Project 1
// Duane J. Jarc
// August 1, 2013

// Function bodies of class that defines all graphic objects

#include "stdafx.h"

// Constructor that can only be called by the subclasses to initialize the color

Graphic::Graphic(Color color)
{
	this->color = color;
}

// Sets the color of the graphic to be draw. Must be called first by the draw function of the subclasses

void Graphic::colorDrawing() const
{
	glColor3d(color.red, color.green, color.blue);
}