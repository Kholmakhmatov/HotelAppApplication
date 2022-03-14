package com.example.hotelappapplication.repository;

import com.example.hotelappapplication.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    boolean existsByName(String name);


}