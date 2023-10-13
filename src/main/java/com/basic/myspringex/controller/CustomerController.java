package com.basic.myspringex.controller;

import com.basic.myspringex.entity.Customer;
import com.basic.myspringex.exception.BusinessException;
import com.basic.myspringex.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer create(@RequestBody Customer customer) {return customerRepository.save(customer);}

    @GetMapping
    public List<Customer> getCustomer() {return customerRepository.findAll();}

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.orElseThrow(() -> new BusinessException("user not found", HttpStatus.NOT_FOUND));
        return customer;
    }

    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("요청하신 이메일에 해당하는 유저가 없습니다.", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
        return ResponseEntity.ok(id + " Customer가 삭제 되었습니다.");
    }

    @GetMapping("/age/{age}")
    public List<Customer> getCustomerByAge(@PathVariable Long age) {
        List<Customer> customerList = customerRepository.findByAge(age);
        return customerList;
    }

}
