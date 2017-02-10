// CMSC 405 Computer Graphics
// Project 1
// Duane J. Jarc
// August 1, 2013

// Project 1 main source file

#include "stdafx.h"

// Global variable that defines the scene object

static Scene scene;

// Function that is called each time the window is drawn

void draw(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	scene.draw();
	glFlush( );
}

// Function that is called each time the window is resized

void resizeWindow(GLint newWidth, GLint newHeight)
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D((GLdouble) -newWidth / 2, (GLdouble) newWidth/2, (GLdouble) -newHeight/2, (GLdouble) newHeight/2);
	glClear(GL_COLOR_BUFFER_BIT);  
}

// The main function of the whole program, which requires the name of the scene definition file as a command line argument
// It calls the parser to parse the scene definition file and add he graphic objects to the scene,
// and then it creates the drawing window and registers the callback function for draing the window and resizing it

void main (GLint argc, char** argv)
{
	cout << "Start Program";
	
	Parser parser(argv[1]);
	try
	{
		scene = parser.parseScene();
	}
	catch (SyntaxError error)
	{
		cout << error.what() << endl;
	}
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	scene.createWindow();
	glutDisplayFunc(draw);
	glutReshapeFunc(resizeWindow);
	glutMainLoop();
	
}
