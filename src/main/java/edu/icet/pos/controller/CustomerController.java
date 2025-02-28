package edu.icet.pos.controller;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.dto.LoyaltyPointsDto;
import edu.icet.pos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class CustomerController {

    /* use @RequiredArgsConstructor with final keyword for CustomerService object
    instead of using @Autowired to do constructor dependency injection -> to avoid de-referencing */
    final CustomerService service;


    //Handle customer registration & Manage customer profiles
    @PostMapping("/customer/register")
    public void addCustomer(@RequestBody CustomerDto customer){
        service.addCustomer(customer);
    }

    @GetMapping("/customer/get-all")
    public List<CustomerDto> getAllCustomers(){
        return service.getAllCustomers();
    }

    @DeleteMapping("/customer/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id){
        service.deleteCustomer(id);
    }

    @PutMapping("/customer/update")
    public void updateCustomer(@RequestBody CustomerDto customer){
        service.updateCustomer(customer);
    }

    @GetMapping("/customer/search-id")
    public CustomerDto searchCustomerById(@RequestParam(name = "query") Integer id){
        return service.searchCustomerById(id);
    }

    @GetMapping("/customer/search-phoneNumber")
    public CustomerDto searchCustomerByPhoneNumber(@RequestParam(name = "query") String phoneNumber){
        return service.searchCustomerByPhoneNumber(phoneNumber);
    }

    @GetMapping("/customer/search-name")
    public List<CustomerDto> searchCustomerByName(@RequestParam(name = "query") String name){
        return service.searchCustomerByName(name);
    }

    @GetMapping("/customer/search-address")
    public List<CustomerDto> searchCustomerByAddress(@RequestParam(name = "query") String address){
        return service.searchCustomerByAddress(address);
    }



    //Handle customer preferences
    @PutMapping("/customer/preferences/{id}")
    public void updatePreferences(@PathVariable Integer id, @RequestBody String preferenceNotes) {
        if(preferenceNotes.startsWith("{")) {
            throw new IllegalArgumentException("Invalid format: Only plain text is allowed");
        }
        service.updateCustomerPreferences(id, preferenceNotes);
    }

    @GetMapping("/customer/preferences/{id}")
    public String getPreferences(@PathVariable Integer id) {
        return service.getCustomerPreferences(id);
    }



    //Manage loyalty programs
    @PutMapping("/customer/loyalty/{id}")
    public void updateLoyaltyPoints(@PathVariable Integer id, @RequestBody LoyaltyPointsDto loyaltyPoints) {
        service.updateLoyaltyPoints(id, loyaltyPoints);
    }



}
