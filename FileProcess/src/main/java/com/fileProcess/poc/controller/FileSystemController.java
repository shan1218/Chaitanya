package com.fileProcess.poc.controller;

import com.fileProcess.poc.services.FileSystemService;
import com.fileProcess.poc.vo.FileInfoVO;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class FileSystemController {

    private static final Logger LOGGER = Logger.getLogger(FileSystemController.class);

    @Autowired
    FileSystemService fileSystemService;

    @PostMapping(value = "/getFileDetail")
    public ResponseEntity<List<FileInfoVO>> getFileDetail(@RequestParam(value = "path") String path) {

        return fileSystemService.getFileDetail(path);
    }

    @PostMapping(value = "/getFileDetailForSpecifiedMimeType")
    public ResponseEntity<List<FileInfoVO>> getFileDetailForSpecifiedMimeType(@RequestParam(value = "path") String path,
                                                                              @RequestParam(value = "acceptedMimeType") String acceptedMimeType) {

        return fileSystemService.getFileDetailForSpecifiedMimeType(path, acceptedMimeType);
    }

}
