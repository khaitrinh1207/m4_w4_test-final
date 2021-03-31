package com.example.testfinal.controller;

import com.example.testfinal.model.Nation;
import com.example.testfinal.service.nation.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NationController {
    @Autowired
    private NationService nationService;

    @RequestMapping("/nation")
    public ModelAndView listNation(){
        List<Nation> nationList= nationService.findAll();
        ModelAndView modelAndView= new ModelAndView("nation/list");
        modelAndView.addObject("list", nationList);
        return modelAndView;
    }

    @GetMapping("/create-nation")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView= new ModelAndView("nation/create");
        modelAndView.addObject("nation", new Nation());
        return modelAndView;
    }
    @PostMapping("/create-nation")
    public ModelAndView saveNation(@ModelAttribute Nation nation){
        nationService.save(nation);
        ModelAndView modelAndView=new ModelAndView("nation/create");
        modelAndView.addObject("message", "Tạo mới thành công");
        modelAndView.addObject("nation", new Nation());
        return modelAndView;
    }
    @GetMapping("/view-nation/{id}")
    public ModelAndView viewCity(@PathVariable Long id){
        Nation nation= nationService.findById(id);
        ModelAndView modelAndView= new ModelAndView("nation/view");
        modelAndView.addObject("nation", nation);
        return modelAndView;
    }
    @GetMapping("/edit-nation/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        Nation nation= nationService.findById(id);
        ModelAndView modelAndView= new ModelAndView("nation/edit");
        modelAndView.addObject("nation", nation);
        return modelAndView;
    }
    @PostMapping("/edit-nation")
    public ModelAndView updateNation(@ModelAttribute Nation nation){
        if (nation== null){
            ModelAndView modelAndView =new ModelAndView("nation/edit");
            modelAndView.addObject("message", "Không thể sửa !!!");
            return modelAndView;
        }
        nationService.save(nation);
        ModelAndView modelAndView =new ModelAndView("nation/edit");
        modelAndView.addObject("message", "Sửa thành công!!!");
        modelAndView.addObject("nation", new Nation());
        return modelAndView;
    }

    @GetMapping("/delete-nation/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Nation nation= nationService.findById(id);
        ModelAndView modelAndView = new ModelAndView("nation/delete");
        modelAndView.addObject("nation", nation);
        return modelAndView;

    }

    @PostMapping("/delete-nation")
    public ModelAndView deleteNation(@ModelAttribute("nation") Nation nation){
        nationService.delete(nation.getId());
        ModelAndView modelAndView = new ModelAndView("nation/delete");
        modelAndView.addObject("message", "Xóa thành công!!!");
        return modelAndView;
    }
}
