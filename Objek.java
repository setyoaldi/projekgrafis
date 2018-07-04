package org.yourorghere;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Objek {

    public static void Matahari(GL gl) {
        {
            float amb[] = {3F, 0f, 0f, 1};
            float diff[] = {3f, 0f, 0f, 1};
            float spec[] = {3f, 2f, 0f, 1};
            float shine = 0;
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, amb, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, diff, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, spec, 0);
            gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
            float BODY_RADIUS = 3f;
            int SLICES = 70;
            int STACKS = 70;
            GLU glu = new GLU();
            GLUquadric q = glu.gluNewQuadric();
            glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);
        }
    }

    public static void Merkurius(GL gl) {
        {
            float amb[] = {0F, 0f, 0f, 0};
            float diff[] = {0f, 0f, 0f, 1};
            float spec[] = {0f, 2f, 0f, 0};
            float shine = 0;
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, amb, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, diff, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, spec, 0);
            gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
            float BODY_RADIUS = 0.5f;
            int SLICES = 40;
            int STACKS = 40;
            GLU glu = new GLU();
            GLUquadric q = glu.gluNewQuadric();
            glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);
        }
    }

    public static void Venus(GL gl) {
        {
            float amb[] = {0F, 0f, 0f, 0};
            float diff[] = {1f, 0f, 0f, 0};
            float spec[] = {0f, 0f, 0f, 0};
            float shine = 0;
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, amb, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, diff, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, spec, 0);
            gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
            float BODY_RADIUS = 1f;
            int SLICES = 50;
            int STACKS = 50;
            GLU glu = new GLU();
            GLUquadric q = glu.gluNewQuadric();
            glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);
        }
    }

    public static void Bumi(GL gl) {
        {
            float amb[] = {0F, 0f, 1f, 0};
            float diff[] = {2f, 0f, 0f, 0};
            float spec[] = {3f, 0f, 1f, 1};
            float shine = 0;
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, amb, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, diff, 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, spec, 0);
            gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
            float BODY_RADIUS = 1.5f;
            int SLICES = 60;
            int STACKS = 60;
            GLU glu = new GLU();
            GLUquadric q = glu.gluNewQuadric();
            glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);

        }
    }
}
