package com.example.sweHomework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "room_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Double size;

    @Column(name = "capacity")
    private int capacity;

}
