/*
CMSC 405 - Computer Graphics
Project 4
Michael Straughan
December 10, 2015
*/

// Header file to be included by all source files

#define _USE_MATH_DEFINES

// Contains all header files in the correct order

#include <cmath>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <map>

using namespace std;

#include <GL/glut.h>

#include "types.h"

class Transformation;

#include "polyhedron.h"
#include "tetrahedron.h"
#include "cube.h"
#include "dodecahedron.h"

#include "vector.h"

#include "transformation.h"
#include "rotation.h"
#include "scaling.h"
#include "translation.h"

#include "animator.h"

#include "scene.h"
#include "syntax.h"
#include "token.h"
#include "lexer.h"
#include "parser.h"
