package com.prabhas.skipmenu.module;

public final class Module {
	public final String id;
	public final String name;
	public boolean enabled;

	public Module(String id, String name, boolean enabled) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
	}
}