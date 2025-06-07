package com.lmlasmo.ms.user.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ms.user.dto.ProfileDTO;
import com.lmlasmo.ms.user.dto.update.UpdateProfileDTO;
import com.lmlasmo.ms.user.mapper.ProfileMapper;
import com.lmlasmo.ms.user.model.Profile;
import com.lmlasmo.ms.user.repository.ProfileRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Service
@Transactional
public class ProfileService {

	private ProfileRepository profileRepository;
	private ProfileMapper profileMapper;

	public void update(UpdateProfileDTO update, BigInteger userId) {
		Profile profile = profileRepository.findByUserId(userId).orElseGet(() -> null);

		if(profile == null) throw new EntityNotFoundException();

		if(update.getName() != null && update.getName().isBlank()) profile.setName(update.getName());

		if(update.getTel() != null) profile.setTel(update.getTel());

		profileRepository.save(profile);
	}

	public ProfileDTO findByUser(BigInteger userId) {
		ProfileDTO profile =  profileRepository.findByUserId(userId)
				.map(p -> profileMapper.toProfileDTO(p))
				.orElseGet(() -> null);

		if(profile == null) throw new EntityNotFoundException("Profile not found");

		return profile;
	}

}
