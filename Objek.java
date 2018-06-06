package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Objek {

    static void Matahari(GL gl) {
        {
           float BODY_RADIUS=3f;
int SLICES=70;
int STACKS=70;
GLU glu=new GLU();
GLUquadric q=glu.gluNewQuadric();
glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);

        }
    }
    static void Bumi(GL gl) {
        {
           float BODY_RADIUS=0.5f;
int SLICES=50;
int STACKS=50;
GLU glu=new GLU();
GLUquadric q=glu.gluNewQuadric();
glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);

        }
    }
}
