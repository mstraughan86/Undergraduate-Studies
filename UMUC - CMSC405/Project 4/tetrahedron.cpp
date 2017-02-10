/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/



#include "stdafx.h"

void Tetrahedron::drawSpecific()
{
	glutWireTetrahedron();
}

Tetrahedron::Tetrahedron(Color color, vector<Transformation*> transformations)
	: Polyhedron(color, transformations)
{

}