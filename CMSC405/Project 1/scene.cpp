// CMSC 405 Computer Graphics
// Project 1
// Duane J. Jarc
// August 1, 2013

// Function bodies of class that defines a scene

#include "stdafx.h"

// Constructor that must be supplied the height and width of the drawing window for the scene

Scene::Scene(string name, GLint height, GLint width)
{
	this->name = name;
	this->height = height;
	this->width = width;
}

// Creates the drawing window for the scene at position 100, 100 with the width and heights supplied to the constructor

void Scene::createWindow()
{
	glutInitWindowPosition(100, 100);
	glutInitWindowSize(width, height);
	glutCreateWindow(name.c_str());
   	glClearColor(1.0, 1.0, 1.0, 1.0);
   	glMatrixMode(GL_PROJECTION);
    gluOrtho2D(-width/2, width/2, -height/2, height/2);
}

// Traverses all the graphic objects in the scene and calls the draw function to draw them

void Scene::draw()
{
	for (vector<Graphic*>::iterator iterator = graphicObjects.begin() ; iterator != graphicObjects.end(); iterator++)
		(*iterator)->draw();
}