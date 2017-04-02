package com.collisiongames.engine.graphics.buffers;

import static org.lwjgl.opengl.GL15.*;

import java.nio.ByteBuffer;

import com.collisiongames.engine.util.BufferUtil;
import com.collisiongames.engine.util.IDestroyAble;

public class VertexBuffer implements IDestroyAble {

	public final int ID, DIMENSIONS;
	
	public VertexBuffer(float[] data, int dimensions) {
		ID = glGenBuffers();
		DIMENSIONS = dimensions;
		glBindBuffer(GL_ARRAY_BUFFER, ID);
		
		glBufferData(GL_ARRAY_BUFFER, BufferUtil.createFloatBuffer(data), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void destroy() {
		glDeleteBuffers(ID);
	}
}