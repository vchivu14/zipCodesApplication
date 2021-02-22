package com.zip.zipos.controllers;

import com.zip.zipos.helper.ExcelHelper;
import com.zip.zipos.service.ZipCityUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ZipCityUploadController {
    final ZipCityUploadService zipCityUploadService;

    public ZipCityUploadController(ZipCityUploadService zipCityUploadService) {
        this.zipCityUploadService = zipCityUploadService;
    }

    @RequestMapping("/")
    public String uploadPage(Model model) {
        return "uploadView";
    }

    @PostMapping("/upload")
    public String upload(Model model, @RequestParam("excelFile")MultipartFile file) {
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                zipCityUploadService.save(file);
                model.addAttribute("msg", "Successfully uploaded file: " + file.getOriginalFilename().toString());
            } catch (Exception e) {
                model.addAttribute("msg", "Couldn't upload the file: " + file.getOriginalFilename().toString());
            }
        } else {
            model.addAttribute("msg", "Must be an excel format");
        }
        return "uploadStatusView";
    }
}
