package com.collisiongames.engine.graphics.buffers;

import static org.lwjgl.opengl.GL15.*;

import java.nio.ByteBuffer;

import com.collisiongames.engine.util.IDestroyAble;

public class VertexBuffer implements IDestroyAble {

	public final int ID, DATA_TYPE, DIMENSIONS;
	
	public VertexBuffer(ByteBuffer data, int dimensions, int type) {
		ID = glGenBuffers();
		DATA_TYPE = type;
		DIMENSIONS = dimensions;
		glBindBuffer(GL_ARRAY_BUFFER, ID);
		
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void destroy() {
		glDeleteBuffers(ID);
	}
}