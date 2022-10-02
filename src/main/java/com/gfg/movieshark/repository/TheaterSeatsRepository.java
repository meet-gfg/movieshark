package com.gfg.movieshark.repository;


import com.gfg.movieshark.domain.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatsRepository extends JpaRepository<TheaterSeats, Long> {

}