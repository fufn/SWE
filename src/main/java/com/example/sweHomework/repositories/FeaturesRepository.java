package com.example.sweHomework.repositories;

import com.example.sweHomework.entities.Feature;
import com.example.sweHomework.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FeaturesRepository extends JpaRepository<Feature, Long> {
}
