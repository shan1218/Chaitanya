package com.sony.spe.services;

import com.sony.spe.entity.Song;
import com.sony.spe.repository.SongRepository;
import com.sony.spe.util.MagicUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Service
@EnableAsync
@Transactional
public class SongService {

    private static final Logger LOGGER = Logger.getLogger(ExcelFileUploadService.class);

    @Autowired
    SongRepository songRepository;

    public ResponseEntity<String> uploadSong(MultipartFile file) {

        String response = "{\"isSuccess\":\"true\",\"message\":\"Success\"}";
        String currentTimeStamp = MagicUtil.getCurrentTimeStamp();
        String tempPathToConvertMultipartToFile = MagicUtil.getTempPathToSaveMultipartFileToFile();
        String folderWithCurrentTimpStamp = tempPathToConvertMultipartToFile+currentTimeStamp+File.separator;
        MagicUtil.createFolder(folderWithCurrentTimpStamp);
        MagicUtil.converyMultipartToFile(file, folderWithCurrentTimpStamp);

        readExcelForSong(folderWithCurrentTimpStamp+file.getOriginalFilename());

        //Delete the file which was created recently
        FileSystemUtils.deleteRecursively(new File(tempPathToConvertMultipartToFile+currentTimeStamp));

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Transactional
    public void readExcelForSong(String tempExcelPath){

        try {
            FileInputStream fis = new FileInputStream(new File(tempExcelPath));

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            boolean isFirstRowIgnored = false;
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                if(isFirstRowIgnored) {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    String title = "", parentSong = "";
                    Integer originalProductId = null;
                    while (cellIterator.hasNext()) {

                        Cell currentCell = cellIterator.next();
                        if(currentCell.getColumnIndex() == 0) {
                            title = currentCell.getStringCellValue();
                        } else if(currentCell.getColumnIndex() == 1) {
                            parentSong = currentCell.getStringCellValue();
                        } else if(currentCell.getColumnIndex() == 2) {
                            originalProductId = (int)currentCell.getNumericCellValue();
                        }
                    }

                    Integer parentSongId = null;
                    if(null != title && title.trim().length() > 0 && null != parentSong && parentSong.trim().length() > 0
                            && null != originalProductId && originalProductId > 0){

                        Optional<Song> songByTitle = songRepository.findByTitleAndSongClassIdAndSongTypeId(parentSong.trim(), 390, 71);
                        if(songByTitle.isPresent()){
                            parentSongId = songByTitle.get().getSongId();
                            Song song1 = new Song();
                            Calendar calendar = Calendar.getInstance();
                            song1.setTitle(title);
                            song1.setParentSongId(parentSongId);
                            song1.setOriginalProductId(originalProductId);
                            song1.setSongClassId(5102);
                            song1.setSongTypeId(71);
                            song1.setPublicDomainFlag("N");
                            song1.setSongStatusId(31);
                            song1.setHiddenFlag("N");
                            song1.setPublicDomainUSFlag("N");
                            song1.setPublicDomainWWFlag("N");
                            song1.setGenericSong("N");
                            song1.setSpeInterest("N");
                            song1.setCreatedBy("ExcelUpload");
                            song1.setModifiedBy("ExcelUpload");
                            song1.setCreatedDate(calendar.getTime());
                            song1.setModifiedDate(calendar.getTime());
                            song1 = songRepository.save(song1);

                            //Optional<Song> songByTitle2 = songRepository.findByTitleAndSongClassIdAndSongTypeId(title.trim(), 5102, 71);
                            if(song1.getSongId() != null) {
                                Song song2 = new Song();
                                song2.setTitle(title);
                                song2.setParentSongId(song1.getSongId());
                                song2.setOriginalProductId(null);
                                song1.setParentMasterSongId(122755); //How are we getting this value
                                song2.setSongClassId(5102);
                                song2.setSongTypeId(72);
                                song2.setPublicDomainFlag("N");
                                song2.setSongStatusId(31);
                                song2.setHiddenFlag("N");
                                song2.setPublicDomainUSFlag("N");
                                song2.setPublicDomainWWFlag("N");
                                song2.setGenericSong("N");
                                song2.setSpeInterest("N");
                                song2.setCreatedBy("ExcelUpload");
                                song2.setModifiedBy("ExcelUpload");
                                song2.setCreatedDate(calendar.getTime());
                                song2.setModifiedDate(calendar.getTime());
                                song2 = songRepository.save(song2);
                            } else {
                                LOGGER.info("Unable to fetch song id for inserted record.");
                            }
                        } else {
                            LOGGER.info("Unable to fetch parent song id from DB.");
                        }

                    } else {
                        LOGGER.info("Please check excel that if we have valid value for all 3 columns.");
                    }
                }

                if(!isFirstRowIgnored) {
                    isFirstRowIgnored = true;
                }
            }
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
