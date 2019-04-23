package com.sony.spe.controller;

import com.sony.spe.services.ExcelFileUploadService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/excel")
public class ExcelFileUploadController {

    private static final Logger LOGGER = Logger.getLogger(ExcelFileUploadController.class);

    @Autowired
    ExcelFileUploadService excelFileUploadService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam(value = "file", required = true) MultipartFile file) {

        LOGGER.info("file : "+file.getContentType()+" : "+file.getName() + " : "+file.getOriginalFilename() + " : "+file.getSize());
        return excelFileUploadService.upload(file);
    }

}
