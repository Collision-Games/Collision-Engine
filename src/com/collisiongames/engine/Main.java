package com.collisiongames.engine;

import java.io.FileNotFoundException;

import com.collisiongames.engine.graphics.Mesh;
import com.collisiongames.engine.graphics.Shader;
import com.collisiongames.engine.graphics.SimpleRenderer;
import com.collisiongames.engine.graphics.Window;

public class Main {
	
	private Window window;
	private SimpleRenderer renderer;
	
	public Main() {
		window = new Window("test", 800, 600, 3, 3);
		renderer = new SimpleRenderer();
		
		Shader shader = null;
		try {
			shader = new Shader("/shaders/SimpleVertexShader.vert", "/shaders/SimpleFragmentShader.frag");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		float[] vertices = {// First triangle
			     0.5f,  0.5f, 0.0f,  // Top Right
			     0.5f, -0.5f, 0.0f,  // Bottom Right
			    -0.5f,  0.5f, 0.0f  // Top Left 
	    };
		
		Mesh mesh = new Mesh(vertices);
		
		while(!window.shouldClose()) {
			window.prepareRender();
			
			shader.bind();
			renderer.renderMesh(mesh);
			Shader.unBind();
			
			window.render();
		}
		
		mesh.destroy();
		window.destroy();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}