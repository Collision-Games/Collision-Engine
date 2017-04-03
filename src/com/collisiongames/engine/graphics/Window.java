package com.collisiongames.engine.graphics;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import com.collisiongames.engine.util.IDestroyAble;

public class Window implements IDestroyAble {

	public String name;
	public final int GL_MAJOR, GL_MINOR;
	public int width, height;
	private long window = -1;
	
	public Window(String name, int width, int height, int gl_major, int gl_minor) {
		this.name = name;
		this.width = width;
		this.height = height;
		GL_MAJOR = gl_major;
		GL_MINOR = gl_minor;
		
		init();
		start();
	}
	
	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!glfwInit())
			throw new IllegalStateException("Failed to initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, GL_MAJOR);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, GL_MINOR);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		
		window = glfwCreateWindow(width, height, name, NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Failed to create window!");
		
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			glfwGetWindowSize(window, pWidth, pHeight);
			
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);	
	}
	
	public void render() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	private void start() {
		GL.createCapabilities();
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
	}
	
	@Override
	public synchronized void destroy() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();	
	}

	public void prepareRender() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
//	public static float[] vertices = {// First triangle
//		     0.5f,  0.5f, 0.0f,  // Top Right
//		     0.5f, -0.5f, 0.0f,  // Bottom Right
//		    -0.5f,  0.5f, 0.0f  // Top Left 
//		    };
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
}