#version 330 core
layout (location = 0) in vec3 position;

out vec4 color;

void main() {
	gl_Position = vec4(position, 1.0);
	color = vec4(1.0 - position.x, position.y / 2, position.z / 3, 1.0);
}