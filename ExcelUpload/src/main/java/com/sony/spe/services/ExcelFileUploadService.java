package com.sony.spe.services;

import com.sony.spe.entity.ExcelUpload;
import com.sony.spe.util.MagicUtil;
import com.sony.spe.vo.ExcelColumnVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Service
@EnableAsync
@Transactional
public class ExcelFileUploadService {

    private static final Logger LOGGER = Logger.getLogger(ExcelFileUploadService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseEntity<String> upload(MultipartFile file) {

        String response = "{\"isSuccess\":\"true\",\"message\":\"Success\"}";
        String currentTimeStamp = MagicUtil.getCurrentTimeStamp();
        String tempPathToConvertMultipartToFile = MagicUtil.getTempPathToSaveMultipartFileToFile();
        String folderWithCurrentTimpStamp = tempPathToConvertMultipartToFile+currentTimeStamp+File.separator;
        MagicUtil.createFolder(folderWithCurrentTimpStamp);
        //String newExcelFileName = folderWithCurrentTimpStamp+currentTimeStamp+".xlsx";
        MagicUtil.converyMultipartToFile(file, folderWithCurrentTimpStamp);

        List<ExcelColumnVO> excelRowValueList = readExcel(folderWithCurrentTimpStamp+file.getOriginalFilename());
        LOGGER.info("Excel Row Count : "+excelRowValueList.size());

        //Delete the file which was created recently
        FileSystemUtils.deleteRecursively(new File(tempPathToConvertMultipartToFile+currentTimeStamp));

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Transactional
    public List<ExcelColumnVO> readExcel(String tempExcelPath){

        List<ExcelColumnVO> excelRowValueList = new ArrayList<ExcelColumnVO>();
        List<ExcelUpload> uploadDataList = new ArrayList<ExcelUpload>();
        try {
            FileInputStream fis = new FileInputStream(new File(tempExcelPath));

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            boolean isFirstRowIgnored = false;
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                if(isFirstRowIgnored) {
                    ExcelColumnVO excelData = new ExcelColumnVO();
                    ExcelUpload uploadData = new ExcelUpload();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    while (cellIterator.hasNext()) {

                        Cell currentCell = cellIterator.next();
                        if(currentCell.getColumnIndex() == 0) {
                            excelData.setEpisodeId((int)currentCell.getNumericCellValue());
                            uploadData.setEpisodeId((int)currentCell.getNumericCellValue());
                        } else if(currentCell.getColumnIndex() == 1) {
                            excelData.setSongId((int)currentCell.getNumericCellValue());
                            uploadData.setSongId((int)currentCell.getNumericCellValue());
                        } else if(currentCell.getColumnIndex() == 2) {
                            excelData.setSongName(currentCell.getStringCellValue());
                            uploadData.setSongName(currentCell.getStringCellValue());
                        }
                        /* else if(currentCell.getColumnIndex() == 3) {
                            excelData.setCreatedBy(currentCell.getStringCellValue());
                            uploadData.setCreatedBy(Integer.parseInt(currentCell.getStringCellValue()));
                        } else if(currentCell.getColumnIndex() == 4) {
                            excelData.setCreateddate(currentCell.getStringCellValue());
                            uploadData.setEpisodeId(Integer.parseInt(currentCell.getStringCellValue()));
                        }*/
                    }
                    uploadData.setCreatedBy("System");
                    uploadData.setCreatedDt(Calendar.getInstance());
                    entityManager.merge(uploadData);
                    entityManager.flush();
                    excelRowValueList.add(excelData);
                }
                if(!isFirstRowIgnored) {
                    isFirstRowIgnored = true;
                }
            }
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return excelRowValueList;
    }
}
