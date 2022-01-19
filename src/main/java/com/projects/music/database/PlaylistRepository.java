package com.projects.music.database;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.projects.music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projects.music.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, BigInteger> {

	Optional<Playlist> findByName(String name);

	@Query("select s from Song s where s.playlistId = ?1")
	Collection<Song> getSongs(BigInteger playlistId);

	@Query(value = "select name from song where playlist_id = ?", nativeQuery = true)
	List<String> getSongsUsingNativeQuery(BigInteger playlistId);

}