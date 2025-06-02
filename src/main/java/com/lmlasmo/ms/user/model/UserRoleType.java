package com.lmlasmo.ms.user.model;

import java.util.Comparator;
import java.util.List;

import lombok.Getter;

@Getter
public enum UserRoleType {
	COMUM(2),
	MANAGER(1),
	ADMIN(0);

	private int priority;

	UserRoleType(int priority) {
		this.priority = priority;
	}

	public List<UserRoleType> getHierarchy() {
		return List.of(UserRoleType.values()).stream()
				.filter(r -> r.getPriority() <= this.getPriority())
				.sorted(Comparator.comparing(UserRoleType::getPriority).reversed())
				.toList();
	}

}
