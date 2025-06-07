package com.lmlasmo.ms.user.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ms.user.dto.AddressDTO;
import com.lmlasmo.ms.user.dto.register.RegisterAddressDTO;
import com.lmlasmo.ms.user.dto.update.UpdateAddressDTO;
import com.lmlasmo.ms.user.mapper.AddressMapper;
import com.lmlasmo.ms.user.model.Address;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.repository.AddressRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Service
@Transactional
public class AddressService {

	private AddressRepository addressRepository;
	private AddressMapper addressMapper;

	public AddressDTO save(RegisterAddressDTO register, BigInteger userId) {
		if(addressRepository.findByUserId(userId).isPresent()) throw new EntityExistsException("Address was created");

		User user = new User(userId);
		Address address = addressMapper.toAddress(register);
		address.setUser(user);

		address = addressRepository.save(address);
		return addressMapper.toAddressDTO(address);
	}

	public void update(UpdateAddressDTO update, BigInteger userId) {
		Address address = addressRepository.findByUserId(userId).orElseGet(() -> null);

		if(address == null) throw new EntityNotFoundException("Address not found");

		if(update.getNation() != null) address.setNation(update.getNation());

		if(update.getState() != null) address.setState(update.getState());

		if(update.getCity() != null) address.setCity(update.getCity());

		if(update.getDistrict() != null) address.setDistrict(update.getDistrict());

		if(update.getStreet() != null) address.setStreet(update.getStreet());

		if(update.getNumber() != null) address.setNumber(update.getNumber());

		if(update.getComplement() != null) address.setComplement(update.getComplement());

		if(update.getPostalCode() != null) address.setPostalCode(update.getPostalCode());

		addressRepository.save(address);
	}

	public AddressDTO findByUser(BigInteger userId) {
		AddressDTO address = addressRepository.findByUserId(userId).map(a -> addressMapper.toAddressDTO(a)).orElseGet(() -> null);

		if(address == null) throw new EntityNotFoundException("Address not found");

		return address;
	}

}
