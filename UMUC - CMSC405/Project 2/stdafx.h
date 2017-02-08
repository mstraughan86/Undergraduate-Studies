// CMSC 405 Computer Graphics
// Project 2
// Duane J. Jarc
// August 1, 2013

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

#include "transformable.h"
#include "graphic.h"
#include "text.h"
#include "convexpolygon.h"
#include "isoscelestriangle.h"
#include "parallelogram.h"
#include "regularpolygon.h"

#include "transformation.h"
#include "rotation.h"
#include "scaling.h"
#include "translation.h"

#include "scene.h"
#include "syntax.h"
#include "token.h"
#include "lexer.h"
#include "parser.h"
