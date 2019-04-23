package com.fileProcess.poc.services;

import com.fileProcess.poc.vo.FileInfoVO;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@EnableAsync
public class FileSystemService {

    private static final Logger LOGGER = Logger.getLogger(FileSystemService.class);

    public ResponseEntity<List<FileInfoVO>> getFileDetail(String path) {
        List<FileInfoVO> fileDetail = new ArrayList<FileInfoVO>();
        File file = new File(path);
        if(file.isDirectory() && file.exists()){
            search(file, fileDetail, null);
        }
        LOGGER.info("File Path : "+path);
        return new ResponseEntity<List<FileInfoVO>>(fileDetail, HttpStatus.OK);
    }

    public ResponseEntity<List<FileInfoVO>> getFileDetailForSpecifiedMimeType(String path, String acceptedMimeType) {

        List<String> acceptedMimeTypeList = new ArrayList<String>(Arrays.asList(acceptedMimeType.split(",")));

        if(StringUtils.containsIgnoreCase(acceptedMimeType, "excel")){
            acceptedMimeTypeList.add("xls");
            acceptedMimeTypeList.add("xlsx");
        }

        List<FileInfoVO> fileDetail = new ArrayList<FileInfoVO>();
        File file = new File(path);
        if(file.isDirectory() && file.exists()){
            search(file, fileDetail, acceptedMimeTypeList);
        }
        LOGGER.info("File Path : "+path);
        return new ResponseEntity<List<FileInfoVO>>(fileDetail, HttpStatus.OK);
    }

    public void search(final File folder, List<FileInfoVO> result, List<String> acceptedMimeTypeList) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(f, result, acceptedMimeTypeList);
            }

            if (f.isFile()) {

                FileInfoVO fileInfoVO = new FileInfoVO();
                fileInfoVO.setFileName(f.getName());
                fileInfoVO.setFileSize(f.length()+"");
                fileInfoVO.setFileExtension(FilenameUtils.getExtension(f.getName()));
                MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
                String mimeType = fileTypeMap.getContentType(f.getName());
                fileInfoVO.setMimeType(mimeType);

                if(null == acceptedMimeTypeList) {
                    result.add(fileInfoVO);
                } else if(acceptedMimeTypeList.contains(fileInfoVO.getFileExtension())){
                    result.add(fileInfoVO);
                }
            }
        }
    }
}
