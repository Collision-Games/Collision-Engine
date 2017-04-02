package com.collisiongames.engine.graphics;

public class Mesh {
	
	public float[] vertices;
	public int[] indices;
	
	private int VAO, VBO, EBO;
	
	public Mesh(float[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
}