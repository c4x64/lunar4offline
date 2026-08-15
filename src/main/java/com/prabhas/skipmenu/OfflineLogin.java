package com.prabhas.skipmenu;

import net.minecraft.client.User;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

public final class OfflineLogin {
	private static final String DEFAULT_NAME = "123";

	private static volatile String username = DEFAULT_NAME;

	private OfflineLogin() {
	}

	public static User currentUser() {
		String name = getUsername();
		UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
		return new User(name, uuid, "0", Optional.empty(), Optional.empty());
	}

	public static String getUsername() {
		String name = username;
		return (name == null || name.isBlank()) ? DEFAULT_NAME : name.trim();
	}

	public static void apply(String name) {
		username = (name == null || name.isBlank()) ? DEFAULT_NAME : name.trim();
	}
}