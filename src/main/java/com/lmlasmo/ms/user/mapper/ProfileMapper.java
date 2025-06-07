package com.lmlasmo.ms.user.mapper;

import org.mapstruct.Mapper;

import com.lmlasmo.ms.user.dto.ProfileDTO;
import com.lmlasmo.ms.user.dto.register.RegisterProfileDTO;
import com.lmlasmo.ms.user.model.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

	public ProfileDTO toProfileDTO(Profile profile);

	public Profile toProfile(RegisterProfileDTO profile);

}
