package com.example.sweHomework.repositories;

import com.example.sweHomework.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
