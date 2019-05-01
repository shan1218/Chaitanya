package com.sony.spe.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "EXCEL_UPLOAD")
@NamedQuery(name = "ExcelUpload.findAll", query = "SELECT a FROM ExcelUpload a")
public class ExcelUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "excelUploadSeq", sequenceName = "EXCEL_UPLOAD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "excelUploadSeq")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EPISODE_ID")
    private Integer episodeId;

    @Column(name = "SONG_ID")
    private Integer songId;

    @Column(name = "SONG_NAME")
    private String songName;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DT")
    private Calendar createdDt;

    @Column(name = "COLUMN6")
    private String column6;

    @Column(name = "COLUMN7")
    private String column7;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Calendar getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Calendar createdDt) {
        this.createdDt = createdDt;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }
}

