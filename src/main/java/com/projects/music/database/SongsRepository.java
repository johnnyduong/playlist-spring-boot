package com.projects.music.database;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.projects.music.model.Song;

public interface SongsRepository extends JpaRepository<Song, BigInteger> {

	Optional<Song> findByName(String name);

	Collection<Song> findByPlaylistId(BigInteger playlistId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("delete from Song s where s.playlistId = ?1")
	void deleteByPlaylistId(BigInteger playlistId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("delete from Song s where s.playlistId = ?1 and s.id = ?2")
	void delete(BigInteger playlistId, BigInteger songId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Song s set s.playlistId = ?2 where s.id = ?1")
	int updatePlaylist(BigInteger songId, BigInteger playlistId);
}