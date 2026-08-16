package com.prabhas.skipmenu.module;

public final class Setting {
	private final String id;
	private final String name;
	private final Type type;
	private String value;
	private final String[] options;

	public Setting(String id, String name, Type type, String value, String... options) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.value = value;
		this.options = options;
	}

	public String id() {
		return id;
	}

	public String name() {
		return name;
	}

	public Type type() {
		return type;
	}

	public String[] options() {
		return options;
	}

	public String value() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? "" : value;
	}

	public boolean asBool() {
		return Boolean.parseBoolean(value);
	}

	public int asInt(int fallback) {
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception e) {
			return fallback;
		}
	}

	public enum Type {
		BOOLEAN,
		TEXT,
		CHOICE
	}
}