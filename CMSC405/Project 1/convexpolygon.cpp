/*
CMSC 405 - Computer Graphics
Project 1
Michael Straughan
November 5, 2015
*/

#include "stdafx.h"

//Draw logic for this class. Sets color and polygon shapes to be drawn.
//Utilizes if/else statements to determine polygon type.
void ConvexPolygon::draw() const
{
	Graphic::colorDrawing();

	//Draw logic for IsocelesTriangle
	if (this->vertexNumber == 3)
	{
		glBegin(GL_TRIANGLES);
		for (int i = 0; i < this->vertexNumber; i++)
		{
			glVertex2d(this->vertexArray[i].x, this->vertexArray[i].y);
		}
		glEnd();
	}

	//Draw logic for Paralellogram
	else if (this->vertexNumber == 4)
	{
		glBegin(GL_QUADS);
		for (int i = 0; i < this->vertexNumber; i++)
		{
			glVertex2d(this->vertexArray[i].x, this->vertexArray[i].y);
		}
		glEnd();
	}

	//Draw logic for RegularPolygon
	else if (this->vertexNumber > 4)
	{
		glBegin(GL_TRIANGLES);

		//For loop to draw all the triangles up until the last one
		int i;
		for (i = 0; i < this->vertexNumber - 1; i++)
		{
			glVertex2d(this->center.x, this->center.y);
			glVertex2d(this->vertexArray[i].x, this->vertexArray[i].y);
			glVertex2d(this->vertexArray[i+1].x, this->vertexArray[i+1].y);
		}
		//Draw function class to draw the last triangle 
		//using the first and last vertices in VertexArray
		glVertex2d(this->center.x, this->center.y);
		glVertex2d(this->vertexArray[i].x, this->vertexArray[i].y);
		glVertex2d(this->vertexArray[0].x, this->vertexArray[0].y);

		glEnd();
	}
}

ConvexPolygon::ConvexPolygon(GLint vertexNumber, Color color) : Graphic(color)
{
	this->vertexNumber = vertexNumber;
}

//Setter function for VertexArray
void ConvexPolygon::SetVertexArray(Point vertexArray[])
{
	this->vertexArray = new Point[this->vertexNumber];
	for (int i = 0; i < this->vertexNumber; i++)
	{
		this->vertexArray[i] = vertexArray[i];
	}
}

//Setter function for center. Exclusively used for RegularPolygon (for now)
void ConvexPolygon::SetCenterPoint(Point center)
{
	this->center = center;
}