package com.collisiongames.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class SimpleRenderer {

	public void renderMesh(Mesh mesh) {
		glBindVertexArray(mesh.VAO.ID);
		
		glEnableVertexAttribArray(0);
		
		glDrawArrays(GL_TRIANGLES, 0, 3);
		
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
}