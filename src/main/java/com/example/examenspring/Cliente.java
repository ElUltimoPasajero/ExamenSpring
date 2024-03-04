package com.example.examenspring;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Double total;
    private String estado;




}



