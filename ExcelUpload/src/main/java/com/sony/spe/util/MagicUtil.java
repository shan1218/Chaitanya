package com.sony.spe.util;

import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

public class MagicUtil {

    private static final Logger LOGGER = Logger.getLogger(MagicUtil.class);

    public static String getTempPathToSaveMultipartFileToFile(){

        String currentPath = System.getProperty("user.dir");
        LOGGER.info("Current working directory path : "+currentPath);

        if(!currentPath.endsWith(File.separator)){
            currentPath = currentPath + File.separator;
        }
        LOGGER.info("Current working directory path : "+currentPath);

        return currentPath+"uploadFile"+File.separator;
    }

    public static void converyMultipartToFile(MultipartFile file, String destinationDirectory){
        try {
            FileUtils.writeByteArrayToFile(new File(destinationDirectory+file.getOriginalFilename()), file.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getCurrentTimeStamp(){
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis()+"";
    }
    public static void createFolder(String folderPath){
        File dir = new File(folderPath);
        dir.mkdirs();
    }
}
