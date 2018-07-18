package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class GLRenderer implements GLEventListener {
//class vector untuk memudah vektor-isasi

    float angle = 0;
    float x = 0;

    class vector {

        float x;
        float y;
        float z;

        public vector(float startX, float startY, float startZ) {
            x = startX;
            y = startY;
            z = startZ;
        }

        void vectorRotation(vector reference, float angle) {
            vector temp = reference;
            float magnitude = (float) Math.sqrt(Math.pow(temp.x, 2) + Math.pow(temp.y, 2) + Math.pow(temp.z, 2));
            temp.x = temp.x / magnitude;
            temp.y = temp.y / magnitude;
            temp.z = temp.z / magnitude;
            float dot_product = (x * temp.x) + (y * temp.y) + (z * temp.z);
            float cross_product_x = (y * temp.z) - (temp.z * z);
            float cross_product_y = -((x * temp.z) - (z * temp.x));

            float cross_product_z = (x * temp.y) - (y * temp.x);
            float last_factor_rodrigues = (float) (1 - Math.cos(Math.toRadians(angle % 360)));
            x = (float) ((x * Math.cos(Math.toRadians(angle % 360)))
                    + (cross_product_x * Math.sin(Math.toRadians(angle % 360)))
                    + (dot_product * last_factor_rodrigues * x));
            y = (float) ((this.y * Math.cos(Math.toRadians(angle % 360)))
                    + (cross_product_y * Math.sin(Math.toRadians(angle % 360)))
                    + (dot_product * last_factor_rodrigues * y));
            z = (float) ((z * Math.cos(Math.toRadians(angle % 360)))
                    + (cross_product_z * Math.sin(Math.toRadians(angle % 360)))
                    + (dot_product * last_factor_rodrigues * z));
        }
    }
    vector depanBelakang = new vector(0f, 0f, -1f);//deklarasi awal vektor untuk maju & mundur
    vector samping = new vector(1f, 0f, 0f);//deklarasi awal vektor untuk gerakan ke kanan & kiri
    vector vertikal = new vector(0f, 1f, 0f);//deklarasi awal vetor untuk gerakan naik & turun
vector sumbuz = new vector(1f, 1f, 1f);
    float Cx = 0, Cy = 2.5f, Cz = 0;
    float Lx = 0, Ly = 2.5f, Lz = -20f;
  float angle_depanBelakang = 0f;
    float angle_depanBelakang2 = 0f;
     boolean kamera5 = false;
    Texture sun,earth,merkurius,venus,mars,jupiter,saturnus,uranus,neptunus, bulan;

    /*
ini adalah metod untuk melakukan pergerakan.
magnitude adalah besarnya gerakan sedangkan direction digunakan untuk menentukan arah.
gunakan -1 untuk arah berlawanan dengan vektor awal
     */
    private void vectorMovement(vector toMove, float magnitude, float direction) {
        float speedX = toMove.x * magnitude * direction;
        float speedY = toMove.y * magnitude * direction;
        float speedZ = toMove.z * magnitude * direction;
        Cx += speedX;
        Cy += speedY;
        Cz += speedZ;
        Lx += speedX;
        Ly += speedY;
        Lz += speedZ;
    }

    private void cameraRotation(vector reference, double angle) {
        float M = (float) (Math.sqrt(Math.pow(reference.x, 2) + Math.pow(reference.y, 2) + Math.pow(reference.z, 2)));//magnitud
        float Up_x1 = reference.x / M; //melakukan
        float Up_y1 = reference.y / M; //normalisasi
        float Up_z1 = reference.z / M; //vektor patokan
        float VLx = Lx - Cx;
        float VLy = Ly - Cy;
        float VLz = Lz - Cz;
        float dot_product = (VLx * Up_x1) + (VLy * Up_y1) + (VLz * Up_z1);
        float cross_product_x = (Up_y1 * VLz) - (VLy * Up_z1);
        float cross_product_y = -((Up_x1 * VLz) - (Up_z1 * VLx));
        float cross_product_z = (Up_x1 * VLy) - (Up_y1 * VLx);
        float last_factor_rodriques = (float) (1 - Math.cos(Math.toRadians(angle % 360)));
        float Lx1 = (float) ((VLx * Math.cos(Math.toRadians(angle % 360)))
                + (cross_product_x * Math.sin(Math.toRadians(angle % 360)))
                + (dot_product * last_factor_rodriques * VLx));
        float Ly1 = (float) ((VLy * Math.cos(Math.toRadians(angle % 360)))
                + (cross_product_y * Math.sin(Math.toRadians(angle % 360)))
                + (dot_product * last_factor_rodriques * VLy));
        float Lz1 = (float) ((VLz * Math.cos(Math.toRadians(angle % 360)))
                + (cross_product_z * Math.sin(Math.toRadians(angle % 360)))
                + (dot_product * last_factor_rodriques * VLz));
        Lx = Lx1 + Cx;
        Ly = Ly1 + Cy;
        Lz = Lz1 + Cz;
    }

    public void init(GLAutoDrawable drawable) {
// Use debug pipeline
// drawable.setGL(new DebugGL(drawable.getGL()));
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
// Enable VSync
        gl.setSwapInterval(1);
        try {
            sun = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Sun.bmp"),true);
            merkurius = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Mercury.bmp"),true);
           venus = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Venus.bmp"),true);
           earth = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Earth.bmp"),true);
            mars = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Mars.bmp"),true);
            jupiter = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Jupiter.bmp"),true);
             saturnus = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Saturnus.jpg"),true);
             uranus = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Uranus.bmp"),true);
             neptunus = TextureIO.newTexture(new File("D:\\KULIAH\\images\\Neptune.bmp"),true);
            bulan = TextureIO.newTexture(new File("D:\\KULIAH\\images\\bulan.bmp"),true);
            
        } catch (Exception e) {
            System.out.println("Filenya not found");
        }
      
        gl.glClearColor(0f, 0f, 0f, 0f);
     
        gl.glShadeModel(GL.GL_SMOOTH);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 200.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    public void display(GLAutoDrawable drawable) {
        x += 2;
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
// Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
// Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
// Move the "drawing cursor" around
        glu.gluLookAt(Cx, Cy, Cz,
                Lx, Ly, Lz,
                vertikal.x, vertikal.y, vertikal.z);
        gl.glTranslatef(0f, 0f, -20f);
        //Matahari
        gl.glPushMatrix();
        gl.glRotatef(x, 0, 0, -100);
        sun.enable();
        sun.bind();
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 0.7, 60, 60);
        sun.disable();
        gl.glPopMatrix();
        
        //Merkurius
        gl.glTranslatef(-5f, 0f, 0f);
        gl.glPushMatrix();
        merkurius.enable();
        merkurius.bind();
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 0.7, 60, 60);
        merkurius.disable();
        gl.glPopMatrix();
        
        //Venus
        gl.glTranslatef(0f, -5f, 0f);
        gl.glPushMatrix();
        venus.enable();
        venus.bind();
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 0.7, 60, 60);
        venus.disable();
        gl.glPopMatrix();
        
        //Bumi
        gl.glTranslatef(10f, 10f, 0f);
        gl.glPushMatrix();
        gl.glRotatef(x, 0, 0, -100);
        earth.enable();
        earth.bind();
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 0.7, 60, 60);
        earth.disable();
        gl.glPopMatrix();
        
        
        if (kamera) {
            Key_Pressed(39);
        }
        if (kamera2) {
            Key_Pressed(37);    //x+
        }
        if (kamera3) {
            Key_Pressed(89);    //y+
        }
        if (kamera4) {
            Key_Pressed(38);    //y-
        }
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    void Key_Pressed(int keyCode) {
//huruf W
        if (keyCode == 87) {
            vectorMovement(depanBelakang, 2f, 1f);
        } //huruf S
        else if (keyCode == 83) {
            vectorMovement(depanBelakang, 2f, -1f);
        } //PANAH KANAN
        else if (keyCode == 39) {
            vectorMovement(samping, 2f, 1f);
        } //PANAH KIRI
        else if (keyCode == 37) {
            vectorMovement(samping, 2f, -1f);
        } // PANAH ATAS
        else if (keyCode == 38) {
            vectorMovement(vertikal, 2f, 1f);
        } //PANAH BAWAH
        else if (keyCode == 40) {
            vectorMovement(vertikal, 2f, -1f);
        }
    }
}
