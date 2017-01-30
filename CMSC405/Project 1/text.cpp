/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

#include "stdafx.h"
#include <GL/freeglut.h>

void Text::draw() const
{
	//Draw logic for this class. Sets color, raster position double and font style to be drawn.
	//Raster position Double is used due to the GLDouble type used in parser.cpp
	Graphic::colorDrawing();
	glRasterPos2d(point.x, point.y);
	glutBitmapString(GLUT_BITMAP_9_BY_15, (const unsigned char *)str.c_str());
}

Text::Text(Color color, Point point, string str) : Graphic(color)
{
	this->point = point;
	this->str = str;
}