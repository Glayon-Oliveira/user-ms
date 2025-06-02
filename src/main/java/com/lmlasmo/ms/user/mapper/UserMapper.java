package com.lmlasmo.ms.user.mapper;

import java.math.BigInteger;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lmlasmo.ms.user.dto.auth.UserAuthDTO;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.model.UserRoleType;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "roles", source = "role")
	public UserAuthDTO toUserAuthDTO(User user);

	public default String idToString(BigInteger id) {
		return id.toString();
	}

	public default String[] roleToStringArray(UserRoleType role) {
		return role.getHierarchy().stream().map(UserRoleType::name).toArray(String[]::new);
	}

}
