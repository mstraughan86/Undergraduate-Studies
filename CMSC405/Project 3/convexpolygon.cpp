/*
CMSC 405 - Computer Graphics
Project 2
Michael Straughan
November 27, 2015
*/


#include "stdafx.h"

//Draw logic for this class. Sets color and polygon shapes to be drawn.
void ConvexPolygon::draw()
{
	//Set color for polygon about to be drawn
	Graphic::colorDrawing();

	//Save current matrix (should be matrix identity)
	glPushMatrix();

	//Apply transformations from the shape's transformation array
	for each (Transformation* transformation in this->transformations)
		transformation->transform(this);
	
	//Actual draw logic
	glBegin(GL_POLYGON);
	for (GLint i = 0; i < vertexNumber; i++)
		glVertex2d(vertexArray[i].x, vertexArray[i].y);
	glEnd();

	//Load previously saved matrix value (should have been matrix identity)
	glPopMatrix();

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

//Setter functionfor transformations array
void ConvexPolygon::SetTransformations(vector<Transformation*> transformations)
{
	this->transformations = transformations;
}