/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/


#include "stdafx.h"

void Dodecahedron::drawSpecific()
{
	glutWireDodecahedron();
}

Dodecahedron::Dodecahedron(Color color, vector<Transformation*> transformations)
	: Polyhedron(color, transformations)
{

}