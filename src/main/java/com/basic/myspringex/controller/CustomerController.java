package com.basic.myspringex.controller;

import com.basic.myspringex.dto.CustomerReqDTO;
import com.basic.myspringex.dto.CustomerReqFormDTO;
import com.basic.myspringex.dto.CustomerResDTO;
import com.basic.myspringex.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/index")
    public ModelAndView index() {
        List<CustomerResDTO> customerResDTOList = customerService.getCustomers();
        return new ModelAndView("index", "customers", customerResDTOList);
    }

    @GetMapping("/addCustomer")
    public String addCustomerForm(CustomerReqDTO customerReqDTO) {
        return "add-customer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@Valid CustomerReqDTO customerReqDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-customer";
        }
        customerService.saveCustomer(customerReqDTO);
        model.addAttribute("customers", customerService.getCustomers());
        return "redirect:/customers/index";
    }

    @GetMapping("/updateCustomer/{id}")
    public String updateCustomerForm(@PathVariable Long id, Model model) {
        CustomerResDTO customerResDTO = customerService.getCustomerById(id);
        model.addAttribute("customer", customerResDTO);
        return "update-customer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @Valid CustomerReqFormDTO customerReqFormDTO,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(">>>>hasErrors customer " + customerReqFormDTO);
            model.addAttribute("customer", customerReqFormDTO);
            return "update-customer";
        }
        customerService.updateCustomerForm(customerReqFormDTO);
        return "redirect:/customers/index";
    }

    @GetMapping("deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers/index";
    }

}
