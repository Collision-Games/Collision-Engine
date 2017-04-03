package com.collisiongames.engine.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.Arrays;

import com.collisiongames.engine.graphics.buffers.IndexBuffer;
import com.collisiongames.engine.graphics.buffers.VertexArray;
import com.collisiongames.engine.graphics.buffers.VertexBuffer;
import com.collisiongames.engine.util.IDestroyAble;

public class Mesh implements IDestroyAble {
	
	public VertexArray VAO;
	private IndexBuffer IBO;

	public Mesh(VertexArray VAO, IndexBuffer IBO) {
		this.VAO = VAO;
		this.IBO = IBO;
		
		glBindVertexArray(VAO.ID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO.ID);
		glBindVertexArray(0);
	}
	
	public Mesh(float[] vertices) {
		IBO = null;
		
		VertexBuffer buffer = new VertexBuffer(vertices, 3);
		VAO = new VertexArray(new VertexBuffer[] {buffer});
	}
	
	public void setIndexBuffer() {
		
	}
	
	@Override
	public void destroy() {
		//TODO Destroy ibo
//		IBO.destroy();
		VAO.destroy();
	}
}