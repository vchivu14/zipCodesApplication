package com.zip.zipos.controllers;

import com.zip.zipos.repositories.ZipCityRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZipCityDisplay {
    final ZipCityRepo zipCityRepo;

    public ZipCityDisplay(ZipCityRepo zipCityRepo) {
        this.zipCityRepo = zipCityRepo;
    }

    @RequestMapping("/zipcity")
    public String getData(Model model) {
        model.addAttribute("zipcities", zipCityRepo.findAll());
        return "zipos";
    }
}
