package com.collisiongames.engine.graphics.buffers;

import static org.lwjgl.opengl.GL15.*;

import com.collisiongames.engine.util.BufferUtil;
import com.collisiongames.engine.util.IDestroyAble;

public class IndexBuffer implements IDestroyAble {
	
	public final int ID;
	
	public IndexBuffer(int[] data) {
		ID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ID);
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createIntBuffer(data), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ID);
	}

	@Override
	public void destroy() {
		glDeleteBuffers(ID);
	}
}