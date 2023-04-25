package com.srivn.works.smusers.db.repo.personal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

public interface AddressInfoRepo  extends JpaRepository<AddressInfoEn, Integer>{

	//Can use External API for Address
	@Query("SELECT ai FROM AddressInfoEn ai WHERE ai.zipCode = :zipCode AND ai.houseNumber = :houseNumber AND ai.street = :street AND ai.city = :city")
	Optional<AddressInfoEn> findIfAddressExist(@Param("houseNumber") String houseNumber,@Param("street")  String street, @Param("city") String city,@Param("zipCode") String zipCode);
}
