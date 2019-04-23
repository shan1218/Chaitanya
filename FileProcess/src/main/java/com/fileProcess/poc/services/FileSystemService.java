package com.fileProcess.poc.services;

import com.fileProcess.poc.vo.FileInfoVO;
import org.apache.commons.io.FilenameUtils;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableAsync
public class FileSystemService {

    private static final Logger LOGGER = Logger.getLogger(FileSystemService.class);

    public ResponseEntity<List<FileInfoVO>> getFileNames(String path) {
        List<FileInfoVO> fileDetail = new ArrayList<FileInfoVO>();
        File file = new File(path);
        if(file.isDirectory() && file.exists()){
            search(file, fileDetail);
        }
        LOGGER.info("File Path : "+path);
        return new ResponseEntity<List<FileInfoVO>>(fileDetail, HttpStatus.OK);
    }

    public void search(final File folder, List<FileInfoVO> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(f, result);
            }

            if (f.isFile()) {
                FileInfoVO fileInfoVO = new FileInfoVO();
                fileInfoVO.setFileName(f.getName());
                fileInfoVO.setFileSize(f.length()+"");
                fileInfoVO.setFileExtension(FilenameUtils.getExtension(f.getName()));
                MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
                String mimeType = fileTypeMap.getContentType(f.getName());
                fileInfoVO.setMimeType(mimeType);
                result.add(fileInfoVO);
            }
        }
    }
}
