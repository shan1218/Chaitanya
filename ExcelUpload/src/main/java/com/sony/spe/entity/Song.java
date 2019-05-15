package com.sony.spe.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SONG")
@NamedQuery(name = "Song.findAll", query = "SELECT m FROM Song m")
public class Song {

    @Id
    @SequenceGenerator(name = "songSeq", sequenceName = "SONG_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "songSeq")
    @Column(name = "SONG_ID")
    private Integer songId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SONG_CLASS_ID")
    private Integer songClassId;

    @Column(name = "SONG_TYPE_ID")
    private Integer songTypeId;

    @Column(name = "COPYRIGHT_YEAR")
    private String copyRightYear;

    @Column(name = "COMPOSER_DEATH_DATE")
    private Date composeDeathDate;

    @Column(name = "SNIPPET_FLAG")
    private String snippetFlag;

    @Column(name = "LENGTH")
    private Integer lenght;

    @Column(name = "PUBLIC_DOMAIN_FLAG")
    private String publicDomainFlag;

    @Column(name = "SONG_STATUS_ID")
    private Integer songStatusId;

    @Column(name = "PARENT_SONG_ID")
    private Integer parentSongId;

    @Column(name = "MADE_FOR_MEDIA_ID")
    private Integer madeForMediaId;

    @Column(name = "DAT_LOCATION")
    private String datLocation;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "ORIGINAL_PRODUCT_ID")
    private Integer originalProductId;

    @Column(name = "EXTERNAL_ALBUM_ID")
    private Integer externalAlbumId;

    @Column(name = "HIDDEN_FLAG")
    private String hiddenFlag;

    @Column(name = "PUBLIC_DOMAIN_US_FLAG")
    private String publicDomainUSFlag;

    @Column(name = "PUBLIC_DOMAIN_WW_FLAG")
    private String publicDomainWWFlag;

    @Column(name = "PARENT_CUE_ID")
    private Integer parentCueId;

    @Column(name = "PARENT_MASTER_SONG_ID")
    private Integer parentMasterSongId;

    @Column(name = "ALBUM_LABEL_ID")
    private Integer albumLabelId;

    @Column(name = "ALBUM_RELEASE_YEAR")
    private String albumReleaseYear;

    @Column(name = "ALBUM_TITLE")
    private String albumTitle;

    @Column(name = "ISRC")
    private String isrc;

    @Column(name = "RECORDING_LOCATION")
    private String recordingLocation;

    @Column(name = "NUMBER_OF_MUSICIANS")
    private String numberOfMusicians;

    @Column(name = "NUMBER_OF_VOCALISTS")
    private String numberOfVocalist;

    @Column(name = "MASTER_DETAIL_COMMENT")
    private String masterDetailComment;

    @Column(name = "UNION_")
    private String union;

    @Column(name = "RECORDING_DATE")
    private Date recordingDate;

    @Column(name = "PUBLISH_ON_SITE")
    private String publishOnSite;

    @Column(name = "SONG_FREEZE")
    private String songFreeze;

    @Column(name = "INCLUDE_SAMPLE")
    private String includeSample;

    @Column(name = "COLUMBIA_SYNC_RIGHTS")
    private String columbiaSyncRights;

    @Column(name = "GENERIC_SONG")
    private String genericSong;

    @Column(name = "SODRAC_FLAG")
    private String sodracFlag;

    @Column(name = "DMR_ASSET_ID")
    private Integer dmrAssetId;

    @Column(name = "PLAYBACK_FILE_NAME")
    private String playbackFileName;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "SPE_RETAINED_RIGHTS")
    private Integer speRetainedRights;

    @Column(name = "ORIGINAL_PUBLISHER")
    private Integer originalPublisher;

    @Column(name = "REVIEWED_PARTY_ID")
    private Integer reviewedPartyId;

    @Column(name = "REVIEW_STATUS_ID")
    private Integer reviewStatusId;

    @Column(name = "SPE_INTEREST")
    private String speInterest;

    @Column(name = "ASSET_URL")
    private String assetUrl;

    @Column(name = "VIDEO_KEY")
    private String videoKey;

    @Column(name = "ALBUM_LABEL_AKA_ID")
    private Integer albumLabelAkaId;

    @Column(name = "COMPOSER_AGREEMENT_SERVICED")
    private Date composerAgreementServiced;

    @Column(name = "CATALOG")
    private Integer catalog;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSongClassId() {
        return songClassId;
    }

    public void setSongClassId(Integer songClassId) {
        this.songClassId = songClassId;
    }

    public Integer getSongTypeId() {
        return songTypeId;
    }

    public void setSongTypeId(Integer songTypeId) {
        this.songTypeId = songTypeId;
    }

    public String getCopyRightYear() {
        return copyRightYear;
    }

    public void setCopyRightYear(String copyRightYear) {
        this.copyRightYear = copyRightYear;
    }

    public Date getComposeDeathDate() {
        return composeDeathDate;
    }

    public void setComposeDeathDate(Date composeDeathDate) {
        this.composeDeathDate = composeDeathDate;
    }

    public String getSnippetFlag() {
        return snippetFlag;
    }

    public void setSnippetFlag(String snippetFlag) {
        this.snippetFlag = snippetFlag;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public String getPublicDomainFlag() {
        return publicDomainFlag;
    }

    public void setPublicDomainFlag(String publicDomainFlag) {
        this.publicDomainFlag = publicDomainFlag;
    }

    public Integer getSongStatusId() {
        return songStatusId;
    }

    public void setSongStatusId(Integer songStatusId) {
        this.songStatusId = songStatusId;
    }

    public Integer getParentSongId() {
        return parentSongId;
    }

    public void setParentSongId(Integer parentSongId) {
        this.parentSongId = parentSongId;
    }

    public Integer getMadeForMediaId() {
        return madeForMediaId;
    }

    public void setMadeForMediaId(Integer madeForMediaId) {
        this.madeForMediaId = madeForMediaId;
    }

    public String getDatLocation() {
        return datLocation;
    }

    public void setDatLocation(String datLocation) {
        this.datLocation = datLocation;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(Integer originalProductId) {
        this.originalProductId = originalProductId;
    }

    public Integer getExternalAlbumId() {
        return externalAlbumId;
    }

    public void setExternalAlbumId(Integer externalAlbumId) {
        this.externalAlbumId = externalAlbumId;
    }

    public String getHiddenFlag() {
        return hiddenFlag;
    }

    public void setHiddenFlag(String hiddenFlag) {
        this.hiddenFlag = hiddenFlag;
    }

    public String getPublicDomainUSFlag() {
        return publicDomainUSFlag;
    }

    public void setPublicDomainUSFlag(String publicDomainUSFlag) {
        this.publicDomainUSFlag = publicDomainUSFlag;
    }

    public String getPublicDomainWWFlag() {
        return publicDomainWWFlag;
    }

    public void setPublicDomainWWFlag(String publicDomainWWFlag) {
        this.publicDomainWWFlag = publicDomainWWFlag;
    }

    public Integer getParentCueId() {
        return parentCueId;
    }

    public void setParentCueId(Integer parentCueId) {
        this.parentCueId = parentCueId;
    }

    public Integer getParentMasterSongId() {
        return parentMasterSongId;
    }

    public void setParentMasterSongId(Integer parentMasterSongId) {
        this.parentMasterSongId = parentMasterSongId;
    }

    public Integer getAlbumLabelId() {
        return albumLabelId;
    }

    public void setAlbumLabelId(Integer albumLabelId) {
        this.albumLabelId = albumLabelId;
    }

    public String getAlbumReleaseYear() {
        return albumReleaseYear;
    }

    public void setAlbumReleaseYear(String albumReleaseYear) {
        this.albumReleaseYear = albumReleaseYear;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getRecordingLocation() {
        return recordingLocation;
    }

    public void setRecordingLocation(String recordingLocation) {
        this.recordingLocation = recordingLocation;
    }

    public String getNumberOfMusicians() {
        return numberOfMusicians;
    }

    public void setNumberOfMusicians(String numberOfMusicians) {
        this.numberOfMusicians = numberOfMusicians;
    }

    public String getNumberOfVocalist() {
        return numberOfVocalist;
    }

    public void setNumberOfVocalist(String numberOfVocalist) {
        this.numberOfVocalist = numberOfVocalist;
    }

    public String getMasterDetailComment() {
        return masterDetailComment;
    }

    public void setMasterDetailComment(String masterDetailComment) {
        this.masterDetailComment = masterDetailComment;
    }

    public String getUnion() {
        return union;
    }

    public void setUnion(String union) {
        this.union = union;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public String getPublishOnSite() {
        return publishOnSite;
    }

    public void setPublishOnSite(String publishOnSite) {
        this.publishOnSite = publishOnSite;
    }

    public String getSongFreeze() {
        return songFreeze;
    }

    public void setSongFreeze(String songFreeze) {
        this.songFreeze = songFreeze;
    }

    public String getIncludeSample() {
        return includeSample;
    }

    public void setIncludeSample(String includeSample) {
        this.includeSample = includeSample;
    }

    public String getColumbiaSyncRights() {
        return columbiaSyncRights;
    }

    public void setColumbiaSyncRights(String columbiaSyncRights) {
        this.columbiaSyncRights = columbiaSyncRights;
    }

    public String getGenericSong() {
        return genericSong;
    }

    public void setGenericSong(String genericSong) {
        this.genericSong = genericSong;
    }

    public String getSodracFlag() {
        return sodracFlag;
    }

    public void setSodracFlag(String sodracFlag) {
        this.sodracFlag = sodracFlag;
    }

    public Integer getDmrAssetId() {
        return dmrAssetId;
    }

    public void setDmrAssetId(Integer dmrAssetId) {
        this.dmrAssetId = dmrAssetId;
    }

    public String getPlaybackFileName() {
        return playbackFileName;
    }

    public void setPlaybackFileName(String playbackFileName) {
        this.playbackFileName = playbackFileName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSpeRetainedRights() {
        return speRetainedRights;
    }

    public void setSpeRetainedRights(Integer speRetainedRights) {
        this.speRetainedRights = speRetainedRights;
    }

    public Integer getOriginalPublisher() {
        return originalPublisher;
    }

    public void setOriginalPublisher(Integer originalPublisher) {
        this.originalPublisher = originalPublisher;
    }

    public Integer getReviewedPartyId() {
        return reviewedPartyId;
    }

    public void setReviewedPartyId(Integer reviewedPartyId) {
        this.reviewedPartyId = reviewedPartyId;
    }

    public Integer getReviewStatusId() {
        return reviewStatusId;
    }

    public void setReviewStatusId(Integer reviewStatusId) {
        this.reviewStatusId = reviewStatusId;
    }

    public String getSpeInterest() {
        return speInterest;
    }

    public void setSpeInterest(String speInterest) {
        this.speInterest = speInterest;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }

    public String getVideoKey() {
        return videoKey;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public Integer getAlbumLabelAkaId() {
        return albumLabelAkaId;
    }

    public void setAlbumLabelAkaId(Integer albumLabelAkaId) {
        this.albumLabelAkaId = albumLabelAkaId;
    }

    public Date getComposerAgreementServiced() {
        return composerAgreementServiced;
    }

    public void setComposerAgreementServiced(Date composerAgreementServiced) {
        this.composerAgreementServiced = composerAgreementServiced;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }
}
