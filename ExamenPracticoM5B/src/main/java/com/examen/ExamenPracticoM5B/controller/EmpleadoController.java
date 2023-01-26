package com.examen.ExamenPracticoM5B.controller;

import com.examen.ExamenPracticoM5B.models.Empleado;
import com.examen.ExamenPracticoM5B.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/li")
    public ResponseEntity<List<Empleado>> obtenerListacan() {
        return new ResponseEntity<>(empleadoService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/cre")
    public ResponseEntity<Empleado> crearcli(@RequestBody Empleado empleado){
        Integer dias = 15;
        if(empleado.getApellido().equals("")){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo nulo apellido");
        }else if(empleado.getNombre().equals("")){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo nulo en nombre");
        } else if (empleado.getNombre().length()<=45) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Poner Longitud menor a 45 en nombre");
        }else if (empleado.getApellido().length()<=45) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Poner Longitud menor a 45 en apellido");
        } else if (empleado.getDireccion().length()<=45) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Poner Longitud menor a 45 en direccion");
        }else if (empleado.getTelefono().length()<=15) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Poner Longitud menor a 15 en telefono");
        } else if (empleado.getObservacion().length()<=45) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Poner Longitud menor a 45 en observaciones");
        }else if(empleado.getObservacion().equals("")) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo nulo en observacion");
        }

        if(empleado.getDias_trabajo()>= 20 && empleado.getDias_trabajo()<=29){
                Double saldo1 = Double.valueOf(empleado.getDias_trabajo()*dias);
                saldo1 = saldo1+ (saldo1*0.02);
                empleado.setSueldo(saldo1);
        }else if(empleado.getDias_trabajo()>=30){
            Double saldo2 = Double.valueOf(empleado.getDias_trabajo()*dias);
            saldo2 = saldo2+ (saldo2*0.05);
            empleado.setSueldo(saldo2);
        }else {
            empleado.setSueldo(empleado.getDias_trabajo()*dias);
        }
        return new ResponseEntity<>(empleadoService.save(empleado), HttpStatus.CREATED);
    }

    @DeleteMapping("/eli/{id}")
    public ResponseEntity <?> eliminarcan(@PathVariable Integer id) {
        empleadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/actu/{id}")
    public ResponseEntity <Empleado> actulizarcan(@RequestBody Empleado empleado, @PathVariable Integer id) {
        Empleado em = empleadoService.findById(id);
        if (em ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            em.setApellido(empleado.getApellido());
            em.setNombre(empleado.getNombre());
            em.setDireccion(empleado.getDireccion());
            em.setObservacion(empleado.getObservacion());
            em.setTelefono(empleado.getTelefono());
            em.setFecha_nacimiento(empleado.getFecha_nacimiento());
            em.setDias_trabajo(empleado.getDias_trabajo());

            return new ResponseEntity<>(empleadoService.save(em), HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }}
