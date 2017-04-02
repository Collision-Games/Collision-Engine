package com.collisiongames.engine.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import com.collisiongames.engine.graphics.buffers.IndexBuffer;
import com.collisiongames.engine.graphics.buffers.VertexArray;
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
	
	public void setIndexBuffer() {
		
	}
	
	@Override
	public void destroy() {
		IBO.destroy();
		VAO.destroy();
	}
}