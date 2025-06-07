package com.lmlasmo.ms.user.mapper;

import java.math.BigInteger;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lmlasmo.ms.user.dto.FullUserDTO;
import com.lmlasmo.ms.user.dto.UserDTO;
import com.lmlasmo.ms.user.dto.register.SignupDTO;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.model.UserRoleType;
import com.lmlasmo.ms.user.security.UserAuth;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public User toUser(SignupDTO signup);

	@Mapping(target = "roles", source = "role")
	public UserAuth toUserAuth(User user);

	public UserDTO toUserDTO(User user);

	public FullUserDTO toFullUserDTO(User user);

	public default String idToString(BigInteger id) {
		return id.toString();
	}

	public default String[] roleToStringArray(UserRoleType role) {
		return role.getHierarchy().stream().map(UserRoleType::name).toArray(String[]::new);
	}

}
