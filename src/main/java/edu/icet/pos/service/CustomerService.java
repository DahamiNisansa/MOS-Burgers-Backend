package edu.icet.pos.service;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.dto.LoyaltyPointsDto;

import java.util.List;

public interface CustomerService {


    void addCustomer(CustomerDto customer);

    List<CustomerDto> getAllCustomers();

    void deleteCustomer(Integer id);

    void updateCustomer(CustomerDto customer);

    CustomerDto searchCustomerById(Integer id);

    List<CustomerDto> searchCustomerByName(String name);

    CustomerDto searchCustomerByPhoneNumber(String phoneNumber);

    List<CustomerDto> searchCustomerByAddress(String address);
    
    

    void updateCustomerPreferences(Integer id, String preferenceNotes);

    String getCustomerPreferences(Integer id);






    //Manage loyalty programs
    void updateLoyaltyPoints(Integer id, LoyaltyPointsDto loyaltyPoints);
}
