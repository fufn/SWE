package com.example.sweHomework.beans;

import com.example.sweHomework.entities.Hotels;
import com.example.sweHomework.repositories.HotelsRepository;
import com.example.sweHomework.repositories.RoomRepository;
import com.example.sweHomework.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DataBaseBean{

    @Autowired
    private HotelsRepository hotelsRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public List<Hotels> getAllHotels(){
        return hotelsRepository.findAll();
    }


}

