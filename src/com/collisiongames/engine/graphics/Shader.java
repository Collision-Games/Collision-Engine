package com.collisiongames.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.File;
import java.io.FileNotFoundException;

import com.collisiongames.engine.util.FileUtils;
import com.collisiongames.engine.util.IDestroyAble;

public class Shader implements IDestroyAble {

	public final int PROGRAM_ID, VERTEX_SHADER_ID, FRAGMENT_SHADER_ID;
	public final String VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH;
	
	public Shader(String vertex_path, String fragment_path) throws FileNotFoundException {
		VERTEX_SHADER_PATH = vertex_path;
		FRAGMENT_SHADER_PATH = fragment_path;
		
		PROGRAM_ID = glCreateProgram();
		
		File vertex_file = new File(Shader.class.getResource(vertex_path).getFile().replaceAll("%20", " "));
		if(!vertex_file.exists())
			throw new FileNotFoundException("Couldn't find vertex shader file: " + vertex_path + " in the resource folder!");
		
		File fragment_file = new File(Shader.class.getResource(fragment_path).getFile().replaceAll("%20", " "));
		if(!fragment_file.exists())
			throw new FileNotFoundException("Couldn't find fragment shader file: " + fragment_path + " in the resource folder!");
		
	    VERTEX_SHADER_ID = compileShader(FileUtils.readLines(vertex_file), GL_VERTEX_SHADER);
		FRAGMENT_SHADER_ID = compileShader(FileUtils.readLines(fragment_file), GL_FRAGMENT_SHADER);
		
		glAttachShader(PROGRAM_ID, VERTEX_SHADER_ID);
		glAttachShader(PROGRAM_ID, FRAGMENT_SHADER_ID);
		glLinkProgram(PROGRAM_ID);
		glValidateProgram(PROGRAM_ID);
		
		int status = glGetProgrami(PROGRAM_ID, GL_VALIDATE_STATUS);
		
		if(status == GL_FALSE) {
			String info_log = glGetProgramInfoLog(PROGRAM_ID);
			
			System.err.println("An error occurred whilst linking the shader program");
			System.err.println(info_log);
		}
		
		glDeleteShader(VERTEX_SHADER_ID);
		glDeleteShader(FRAGMENT_SHADER_ID);
	}
	
	public int compileShader(String source, int type) {
		int shaderID = glCreateShader(type);
		
		glShaderSource(shaderID, source);
		glCompileShader(shaderID);
		
		int status = glGetShaderi(shaderID, GL_COMPILE_STATUS);
		if(status == GL_FALSE) {
			String info_log = glGetShaderInfoLog(shaderID);
			
			System.err.println("An error ocurred whilst compiling: " + ((type == GL_VERTEX_SHADER) ? VERTEX_SHADER_PATH : FRAGMENT_SHADER_PATH) + " !");
			System.err.println(info_log);
		}
		
		return shaderID;
	}
	
	public void bind() {
		glUseProgram(PROGRAM_ID);
	}
	
	public static void unBind() {
		glUseProgram(0);
	}

	@Override
	public void destroy() {
		glDetachShader(PROGRAM_ID, VERTEX_SHADER_ID);
		glDetachShader(PROGRAM_ID, FRAGMENT_SHADER_ID);
		
		glDeleteProgram(PROGRAM_ID);
	}
}