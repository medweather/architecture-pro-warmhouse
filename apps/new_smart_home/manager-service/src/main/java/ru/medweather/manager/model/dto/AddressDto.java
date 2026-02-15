package ru.medweather.manager.model.dto;

import ru.medweather.manager.model.Address;

public record AddressDto(Short id, String name) {
    public static AddressDto from(Address address) {
        return new AddressDto(address.getId(), address.getName());
    }
}
