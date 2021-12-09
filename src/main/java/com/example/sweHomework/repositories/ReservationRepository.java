package com.example.sweHomework.repositories;

import com.example.sweHomework.entities.Reservation;
import com.example.sweHomework.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUsers(Users users);

}
