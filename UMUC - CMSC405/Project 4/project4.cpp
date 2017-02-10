/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/

// Project 3 main source file

#include "stdafx.h"

// Global variable that defines the scene object

static Scene scene;

// Function that is called each time the window is drawn

void draw(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	scene.draw();
	glutSwapBuffers();
}

// Function that is called each time the window is resized

void resizeWindow(GLint newWidth, GLint newHeight)
{
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glClear(GL_COLOR_BUFFER_BIT);  
	double dist = sqrt(1 / 10000.0); 
	gluLookAt(dist / 2, dist / 2, dist, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
}

// Timer Callback Function

void timer()
{
	Sleep(50);
	Animator::step();
	glutPostRedisplay();
}

// Keyboard Listener Callback Function

void keyboardListener(unsigned char key, int x, int y)
{
	switch (key)
	{
		case 's':
			glutIdleFunc(timer);
			break;
		case 'p':
			glutIdleFunc(NULL);
			break;
		case 'r':
			Animator::reset();
			glutIdleFunc(draw);
			break;
		default:
			break;
	}
}

// The main function of the whole program, which requires the name of the scene definition file as a command line argument
// It calls the parser to parse the scene definition file and add he graphic objects to the scene,
// and then it creates the drawing window and registers the callback function for draing the window and resizing it

void main (GLint argc, char** argv)
{
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
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	scene.createWindow();
	glutDisplayFunc(draw);
	glutReshapeFunc(resizeWindow);
	glutKeyboardFunc(keyboardListener);
	glutMainLoop();
}
