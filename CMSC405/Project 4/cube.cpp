/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

void Cube::drawSpecific()
{
	glBegin(GL_LINES);

	glVertex3d(vertexArray[0].x, vertexArray[0].y, vertexArray[0].z);
	glVertex3d(vertexArray[1].x, vertexArray[1].y, vertexArray[1].z);
	
	glVertex3d(vertexArray[0].x, vertexArray[0].y, vertexArray[0].z);
	glVertex3d(vertexArray[2].x, vertexArray[2].y, vertexArray[2].z);

	glVertex3d(vertexArray[1].x, vertexArray[1].y, vertexArray[1].z);
	glVertex3d(vertexArray[3].x, vertexArray[3].y, vertexArray[3].z);

	glVertex3d(vertexArray[2].x, vertexArray[2].y, vertexArray[2].z);
	glVertex3d(vertexArray[3].x, vertexArray[3].y, vertexArray[3].z);


	glVertex3d(vertexArray[4].x, vertexArray[4].y, vertexArray[4].z);
	glVertex3d(vertexArray[5].x, vertexArray[5].y, vertexArray[5].z);

	glVertex3d(vertexArray[4].x, vertexArray[4].y, vertexArray[4].z);
	glVertex3d(vertexArray[6].x, vertexArray[6].y, vertexArray[6].z);

	glVertex3d(vertexArray[5].x, vertexArray[5].y, vertexArray[5].z);
	glVertex3d(vertexArray[7].x, vertexArray[7].y, vertexArray[7].z);

	glVertex3d(vertexArray[6].x, vertexArray[6].y, vertexArray[6].z);
	glVertex3d(vertexArray[7].x, vertexArray[7].y, vertexArray[7].z);


	glVertex3d(vertexArray[5].x, vertexArray[5].y, vertexArray[5].z);
	glVertex3d(vertexArray[1].x, vertexArray[1].y, vertexArray[1].z);

	glVertex3d(vertexArray[0].x, vertexArray[0].y, vertexArray[0].z);
	glVertex3d(vertexArray[4].x, vertexArray[4].y, vertexArray[4].z);

	glVertex3d(vertexArray[6].x, vertexArray[6].y, vertexArray[6].z);
	glVertex3d(vertexArray[2].x, vertexArray[2].y, vertexArray[2].z);

	glVertex3d(vertexArray[7].x, vertexArray[7].y, vertexArray[7].z);
	glVertex3d(vertexArray[3].x, vertexArray[3].y, vertexArray[3].z);
	
	glEnd();
}

Cube::Cube(Color color, vector<Transformation*> transformations)
	: Polyhedron(color, transformations)
{
	vertexArray = new Point[8];

	GLdouble height = 1;
	Point center = { 0, 0, 0 };

	vertexArray[0] = { center.x + height / 2, center.y + height / 2, center.x + height / 2 };
	vertexArray[1] = { center.x + height / 2, center.y + height / 2, center.x - height / 2 };
	vertexArray[2] = { center.x + (height / 2), center.y - (height / 2), center.z + (height / 2) };
	vertexArray[3] = { center.x + (height / 2), center.y - (height / 2), center.z - (height / 2) };

	vertexArray[4] = { center.x - (height / 2), center.y + (height / 2), center.z + (height / 2) };
	vertexArray[5] = { center.x - (height / 2), center.y + (height / 2), center.z - (height / 2) };
	vertexArray[6] = { center.x - (height / 2), center.y - (height / 2), center.z + (height / 2) };
	vertexArray[7] = { center.x - (height / 2), center.y - (height / 2), center.z - (height / 2) };
}