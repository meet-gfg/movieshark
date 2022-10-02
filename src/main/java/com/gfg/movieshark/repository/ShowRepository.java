package com.gfg.movieshark.repository;


import com.gfg.movieshark.domain.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query(value = "select * from shows s, movies m where m.id=s.id and m.title=?",nativeQuery = true)
    List<Show> findByMovieName(String movieName);
}