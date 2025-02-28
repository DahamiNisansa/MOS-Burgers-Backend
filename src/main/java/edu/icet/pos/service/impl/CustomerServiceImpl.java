package edu.icet.pos.service.impl;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.dto.LoyaltyPointsDto;
import edu.icet.pos.entity.CustomerEntity;
import edu.icet.pos.repository.CustomerRepo;
import edu.icet.pos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    final CustomerRepo repo;
    final ModelMapper mapper;


    //Handle customer registration & Manage customer profiles
    @Override
    public void addCustomer(CustomerDto customer) {
        repo.save(mapper.map(customer, CustomerEntity.class));
    }


    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerList = new ArrayList<>();
        List<CustomerEntity> all = repo.findAll();

        all.forEach(customerEntity -> {
            customerList.add(mapper.map(customerEntity, CustomerDto.class));
        });

        return customerList;
    }


    @Override
    public void deleteCustomer(Integer id) {
        repo.deleteById(id);
    }


    @Override
    public void updateCustomer(CustomerDto customer) {

        repo.save(mapper.map(customer, CustomerEntity.class));
    }


    @Override
    public CustomerDto searchCustomerById(Integer id) {
        return mapper.map(repo.findById(id), CustomerDto.class);
    }


    @Override
    public List<CustomerDto> searchCustomerByName(String name) {
        List<CustomerEntity> byName = repo.findByName(name);
        List<CustomerDto> customerList = new ArrayList<>();

        byName.forEach(customerEntity -> {
            customerList.add(mapper.map(customerEntity, CustomerDto.class));
        });

        return customerList;
    }


    @Override
    public CustomerDto searchCustomerByPhoneNumber(String phoneNumber) {
        return mapper.map(repo.findByPhoneNumber(phoneNumber), CustomerDto.class);
    }


    @Override
    public List<CustomerDto> searchCustomerByAddress(String address) {
        List<CustomerEntity> byAddress = repo.findByAddress(address);
        List<CustomerDto> customerList = new ArrayList<>();

        byAddress.forEach(customerEntity -> {
            customerList.add(mapper.map(customerEntity, CustomerDto.class));
        });

        return customerList;
    }



    //Handle customer preferences
    @Override
    public void updateCustomerPreferences(Integer id, String preferenceNotes) {
        CustomerEntity customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setPreferenceNotes(preferenceNotes);
        repo.save(customer);
    }

    @Override
    public String getCustomerPreferences(Integer id) {
        CustomerEntity customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return customer.getPreferenceNotes();
    }



    //Manage loyalty programs
    @Override
    public void updateLoyaltyPoints(Integer id, LoyaltyPointsDto loyaltyPoints) {
        CustomerEntity customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setLoyaltyPoints(loyaltyPoints.getLoyaltyPoints());
        repo.save(customer);
    }


}
