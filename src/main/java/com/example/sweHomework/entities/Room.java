package com.example.sweHomework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @Column(name = "number")
    private Long number;

    @Column(name = "floor")
    private int floor;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotels hotels;

}
