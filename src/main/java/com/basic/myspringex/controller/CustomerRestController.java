package com.basic.myspringex.controller;

import com.basic.myspringex.dto.CustomerReqDTO;
import com.basic.myspringex.dto.CustomerResDTO;
import com.basic.myspringex.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerResDTO saveCustomer(@RequestBody CustomerReqDTO customerReqDTO) {
        return customerService.saveCustomer(customerReqDTO);
    }

    @GetMapping("/{id}")
    public CustomerResDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerResDTO> getCustomer() {
        return customerService.getCustomers();
    }

    @PatchMapping("/{email}")
    public CustomerResDTO updateCustomer(@PathVariable String email, @RequestBody CustomerReqDTO customerReqDTO){
        return customerService.updateCustomer(email, customerReqDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(id + " Customer가 삭제 처리 되었습니다.");
    }

}
