package com.sony.spe.repository;

import com.sony.spe.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    public Optional<Song> findByTitleAndSongClassIdAndSongTypeId(String title, Integer songClassId, Integer songTypeId);
}
