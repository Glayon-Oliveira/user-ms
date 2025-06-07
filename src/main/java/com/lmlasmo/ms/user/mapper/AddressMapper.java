package com.lmlasmo.ms.user.mapper;

import org.mapstruct.Mapper;

import com.lmlasmo.ms.user.dto.AddressDTO;
import com.lmlasmo.ms.user.dto.register.RegisterAddressDTO;
import com.lmlasmo.ms.user.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	public AddressDTO toAddressDTO(Address address);

	public Address toAddress(RegisterAddressDTO address);

}
