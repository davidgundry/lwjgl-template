package com.example;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

class NGon {
    FloatBuffer vertices;
    int pointCount;
    float red, green, blue;
}

public class MyGame extends Game {

    NGon myPolygon;

    public void init(Window window) {
        myPolygon = createNGon(20);
    }

    public void update(long currentTime, long deltaTime) {}

    /**
     * This example makes use of the old OpenGL fixed function workflow. This is quick for getting something
     * to render on the screen, but is less efficient and is not programmable.
     */
    public void draw() {
        glColor3f(1, 0, 0);
        rect(0, 0, 1, 1);
        // drawNGon();
        drawNGon(myPolygon);
    }

    /** Draw a rectangle by defining points  */
    private void rect(float x1, float y1, float x2, float y2)
    {
        glBegin(GL_QUADS); // GL QUADS is a particular drawing mode, see https://registry.khronos.org/OpenGL-Refpages/gl2.1/xhtml/glBegin.xml
    
        glVertex3f(x1, y1, 0);
        glVertex3f(x2, y1, 0);
        glVertex3f(x2, y2, 0);
        glVertex3f(x1, y2, 0);
    
        glEnd();
    }

    /**
     * This example allocates to a buffer that exists only for the duration
     * of the try-with-resources block
     */
    private void drawNGon() {
        try (MemoryStack stack = MemoryStack.stackPush())
        { // We can allocate to this stack. However, we must not access the
          // buffers we allocated after we leave this try-with-resources block
            int segments = 5;
            float radius = 0.2f;
            FloatBuffer buffer = stack.mallocFloat(segments*3); // Here we allocate memory for segments*3 floats (each 4 bytes)

            int pos = 0;
            for (int i = 0;i<segments;i++)
            {
                double theta = 2.0f * Math.PI * i/(float) segments;
                float x = (float) (radius * Math.cos(theta));
                float y = (float) (radius * Math.sin(theta));
                buffer.put(pos, x); // We put values into our buffer in sets of three, giving the x
                buffer.put(pos+1, y); // y
                buffer.put(pos+2, 0); // and z values
                pos +=3;
            }
                
            glBegin(GL_TRIANGLE_FAN); // Triangle fan is a particular drawing mode, see https://registry.khronos.org/OpenGL-Refpages/gl2.1/xhtml/glBegin.xml
            for (int i=0;i<segments;i++)
                glVertex3f(buffer.get(3*i), buffer.get((3*i)+1), 0); // we send a single vertex to Open GL by getting values from our buffer
            glEnd();
        } 
    }

    /**
     * This example calculates the points for the NGon and stores it in a buffer on the
     * heap. This buffer lasts until `MemoryUtil.memFree(buffer)` is called. We return
     * our buffer in an NGon object (defined above).
     */
    private NGon createNGon(int points)
    {
        int segments = points;
        float radius = 0.2f;
        // Here we allocate memory on the heap. We need to free it manually when done
        // Don't lose this buffer until the memory has been freed!
        FloatBuffer buffer = MemoryUtil.memAllocFloat(segments*3);
        
        int pos = 0;
        for (int i = 0;i<segments;i++)
        {
            double theta = 2.0f * Math.PI * i/(float) segments;

            float x = (float) (radius * Math.cos(theta));
            float y = (float) (radius * Math.sin(theta));

            buffer.put(pos, x);
            buffer.put(pos+1, y);
            buffer.put(pos+2, 0);
            pos +=3;
        }
        NGon p = new NGon();
        p.vertices = buffer;
        p.pointCount = segments;
        p.red = 1;
        p.blue = 1;
        return p;
    }

    
    /**
     * This draws our previously defined NGon, reading from the buffer on the heap.
     */
    private void drawNGon(NGon p)
    {
        glColor3f(p.red, p.green, p.blue);
        glBegin(GL_TRIANGLE_FAN);
        for (int i=0;i<p.pointCount;i++)
            glVertex3f(p.vertices.get(3*i), p.vertices.get((3*i)+1), 0);
        glEnd();
    }


    public void handleKeyPress(int key, int action) {}
    public void handleMouseClick(int key, int action) {}
    public void windowResized(int width, int height)  {}
    
    /**
     * Because we have manually allocated memory, we need to ensure that it is disposed of
     * ourselves. The garbage collector will not do it for us.
     */
    public void dispose() {
        MemoryUtil.memFree(myPolygon.vertices);
    }

}