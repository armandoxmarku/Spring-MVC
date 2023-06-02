package com.akademia.ikub.spring.mvc.controller;

import com.akademia.ikub.spring.mvc.model.User;
import com.akademia.ikub.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Locale;

@Controller
@RequestMapping("/customer")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    public String getCustomerListView(Model model){
        model.addAttribute("customers",userService.getUsers());
        return "customer-list";
    }


    @GetMapping("/register-view")
    public String getRegistrationView(Model model,@RequestParam(value = "userId",required = false) Long id,Locale locale){
        model.addAttribute("countryList", Arrays.asList("Albania","Kosovo","North Macedonia"));
        if(id!=null){
            model.addAttribute("customerForm",userService.getUserById(id));
            model.addAttribute("viewTitle",messageSource.getMessage("view.customer.registration.update.title",null, locale));
        }else {
            model.addAttribute("customerForm", new User());
            model.addAttribute("viewTitle",messageSource.getMessage("view.customer.registration.title",null, locale));
        }

        return "registration-form";
    }

    @PostMapping("/register")
    public String saveCustomerFormAction(Model model, @Valid @ModelAttribute("customerForm") User user,
                                   BindingResult result,Locale locale){
        String registerTitle = messageSource
                .getMessage("view.customer.registration.title",null, locale);
        String updateTitle = messageSource
                .getMessage("view.customer.registration.update.title",null, locale);

        String viewTitle = user.getId()==null?registerTitle:updateTitle;
        if (result.hasErrors()) {
            model.addAttribute("viewTitle",viewTitle);
            return "registration-form";
        }
        if(user.getId()!=null){
            userService.updateById(user.getId(),user);
        }else {
            userService.createUser(user);
        }
        return "redirect:/customer";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam(name = "userId") Long id){
        userService.deleteById(id);
        return "redirect:/customer";
    }


}
