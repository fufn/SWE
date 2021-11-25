package com.example.sweHomework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotels {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<PhoneNumber> phone_numbers;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Feature> features;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoomType> roomTypes;

}
