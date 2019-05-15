package com.sony.spe.controller;

import com.sony.spe.services.SongService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/song")
public class SongController {

    private static final Logger LOGGER = Logger.getLogger(SongController.class);

    @Autowired
    SongService songService;

    @PostMapping(value = "/uploadSong")
    public ResponseEntity<String> uploadSong(@RequestParam(value = "file", required = true) MultipartFile file) {

        LOGGER.info("file : "+file.getContentType()+" : "+file.getName() + " : "+file.getOriginalFilename() + " : "+file.getSize());
        return songService.uploadSong(file);
    }

}
