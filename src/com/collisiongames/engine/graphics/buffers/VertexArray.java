package com.collisiongames.engine.graphics.buffers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.collisiongames.engine.util.IDestroyAble;

public class VertexArray implements IDestroyAble {

	public final int ID;
	
	public VertexBuffer[] vbos = new VertexBuffer[16];
	
	public VertexArray() {
		this.ID = glGenVertexArrays();
	}
	
	public VertexArray(VertexBuffer[] vbos) {
		if(vbos.length > 16)
			throw new IllegalStateException("A VAO can't have more than 16 VBO's!");
		
		ID = glGenVertexArrays();
		this.vbos = vbos;
		
		glBindVertexArray(ID);
		
		for(int i = 0; i < vbos.length; i++) {
			
			VertexBuffer buffer = vbos[i];
			glBindBuffer(GL_ARRAY_BUFFER, buffer.ID);
			
			glEnableVertexAttribArray(i);
			glVertexAttribPointer(i, buffer.DIMENSIONS, GL_FLOAT, false, 0, 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, 0);
		}
		
		glBindVertexArray(0);
	}
	
	@Override
	public void destroy() {
		for(int i = 0; i < vbos.length; i++)
			vbos[i].destroy();
		
		glDeleteVertexArrays(ID);
	}
}