package edu.icet.pos.repository;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<CustomerEntity,Integer> {
    List<CustomerEntity> findByName(String name);

    Object findByPhoneNumber(String phoneNumber);

    List<CustomerEntity> findByAddress(String address);

    //void saveCustomer(CustomerEntity customer);
}
