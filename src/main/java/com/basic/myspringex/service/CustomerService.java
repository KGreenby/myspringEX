package com.basic.myspringex.service;

import com.basic.myspringex.dto.CustomerReqDTO;
import com.basic.myspringex.dto.CustomerReqFormDTO;
import com.basic.myspringex.dto.CustomerResDTO;
import com.basic.myspringex.entity.Customer;
import com.basic.myspringex.exception.BusinessException;
import com.basic.myspringex.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResDTO saveCustomer(CustomerReqDTO customerReqDTO) {
        Customer customer = modelMapper.map(customerReqDTO, Customer.class);
        Customer saveCustomer = customerRepository.save(customer);
        return modelMapper.map(saveCustomer, CustomerResDTO.class);
    }

    @Transactional(readOnly = true)
    public CustomerResDTO getCustomerById(Long id) {
        Customer CustomerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " customer not found", HttpStatus.NOT_FOUND));
        CustomerResDTO customerResDTO = modelMapper.map(CustomerEntity, CustomerResDTO.class);
        return customerResDTO;
    }

    @Transactional(readOnly = true)
    public List<CustomerResDTO> getCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResDTO> customerResDTOList = customerList.stream()
                .map(customer -> modelMapper.map(customer, CustomerResDTO.class))
                .collect(Collectors.toList());
        return customerResDTOList;
    }

    public CustomerResDTO updateCustomer(String email, CustomerReqDTO customerReqDTO) {
        Customer existCustomer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " customer Not Found", HttpStatus.NOT_FOUND));
        if (customerReqDTO.getAge() != null) {
            existCustomer.setAge(customerReqDTO.getAge());
        }
        if (customerReqDTO.getName() != null) {
            existCustomer.setName(customerReqDTO.getName());
        }
        return modelMapper.map(existCustomer, CustomerResDTO.class);
    }

    public void updateCustomerForm(CustomerReqFormDTO customerReqFormDTO) {
        Customer updateCustomer = customerRepository.findById(customerReqFormDTO.getId())
                .orElseThrow(() ->
                        new BusinessException(customerReqFormDTO.getId() + " customer Not Found", HttpStatus.NOT_FOUND));
//        updateCustomer.setName(customerReqFormDTO.getName());
        if (customerReqFormDTO.getName() != null) {
            updateCustomer.setName(customerReqFormDTO.getName());
        }
        if (customerReqFormDTO.getAge() != null) {
            updateCustomer.setAge(customerReqFormDTO.getAge());
        }
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);

    }

}
