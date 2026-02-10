package ru.medweather.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medweather.manager.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
