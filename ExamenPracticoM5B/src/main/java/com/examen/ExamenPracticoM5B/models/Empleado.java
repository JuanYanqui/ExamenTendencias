package com.examen.ExamenPracticoM5B.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "listareproduccion")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer id_empleado;

    @Column(name= "apellido", nullable = false, length = 45)
    @NotEmpty
    private String apellido;

    @Column(name = "nombre", nullable = false, length = 45)
    @NotEmpty
    private String nombre;

    @Column(name = "telefono", nullable = false, length = 15)
    @NotEmpty
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 45)
    @NotEmpty
    private String direccion;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @Column(name = "observacion", length = 45)
    private String observacion;

    @Column(name = "dias_trabajo")
    private Integer dias_trabajo;

    @Column(name = "sueldo", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.00")
    private double sueldo ;




}
