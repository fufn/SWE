package com.example.sweHomework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotels hotels;

    @Column(name = "check_in")
    private Date check_in;

    @Column(name = "check_out")
    private Date check_out;

}
