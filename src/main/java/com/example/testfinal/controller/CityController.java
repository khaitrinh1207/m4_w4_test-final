package com.example.testfinal.controller;

import com.example.testfinal.model.City;
import com.example.testfinal.model.Nation;
import com.example.testfinal.service.city.CityService;
import com.example.testfinal.service.nation.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private NationService nationService;

    @ModelAttribute("nations")
    public List<Nation> findAll(){
        return nationService.findAll();
    }

    @GetMapping("/city")
    public ModelAndView listCity(){
        List<City> citylist= cityService.findAll();
        ModelAndView modelAndView= new ModelAndView("city/list");
        modelAndView.addObject("list", citylist);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView= new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @PostMapping("/create-city")
    public ModelAndView saveCity(@Validated @ModelAttribute City city, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("city/create");
        }
        cityService.save(city);
        ModelAndView modelAndView=new ModelAndView("city/create");
        modelAndView.addObject("message", "Tạo mới thành công");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @GetMapping("/view-city/{id}")
    public ModelAndView viewCity(@PathVariable Long id){
        City city= cityService.findById(id);
        ModelAndView modelAndView= new ModelAndView("city/view");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @GetMapping("/edit-city/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id){
        City city= cityService.findById(id);
        ModelAndView modelAndView= new ModelAndView("city/edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @PostMapping("/edit-city")
    public ModelAndView updateCity(@Validated @ModelAttribute City city, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("city/edit");
        }
        if (city== null){
            ModelAndView modelAndView =new ModelAndView("city/edit");
            modelAndView.addObject("message", "Khong the sua !!!");
            return modelAndView;
        }

        cityService.save(city);
        ModelAndView modelAndView =new ModelAndView("city/edit");
        modelAndView.addObject("message", "Sua thanh cong!!!");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
            cityService.delete(id);
            ModelAndView modelAndView = new ModelAndView("redirect:/city");
            return modelAndView;
       
    }


}
