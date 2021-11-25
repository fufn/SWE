package com.example.sweHomework.rest;

import com.example.sweHomework.beans.DataBaseBean;
import com.example.sweHomework.entities.Hotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    @Qualifier("db")
    private DataBaseBean dataBaseBean;

    @GetMapping(value = "/test")
    public ResponseEntity<List<Hotels>> test(){

        List<Hotels> hotelsList = dataBaseBean.getAllHotels();

        return new ResponseEntity<>(hotelsList, HttpStatus.OK);

    }

}
