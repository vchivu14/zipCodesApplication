package com.zip.zipos.service;

import com.zip.zipos.helper.ExcelHelper;
import com.zip.zipos.models.ZipCity;
import com.zip.zipos.repositories.ZipCityRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ZipCityUploadService {
    final ZipCityRepo zipCityRepo;

    public ZipCityUploadService(ZipCityRepo zipCityRepo) {
        this.zipCityRepo = zipCityRepo;
    }

    public void save(MultipartFile file) {
        try {
            List<ZipCity> zipCities = ExcelHelper.excelToZipCityDB(file.getInputStream());
            zipCityRepo.saveAll(zipCities);
        } catch (IOException e) {
            throw new RuntimeException("failed to store excel data: " + e.getMessage());
        }
    }
}
