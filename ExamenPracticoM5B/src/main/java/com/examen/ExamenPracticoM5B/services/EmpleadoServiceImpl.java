package com.examen.ExamenPracticoM5B.services;

import com.examen.ExamenPracticoM5B.models.Empleado;
import com.examen.ExamenPracticoM5B.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, Integer> implements EmpleadoService{


    @Autowired
    EmpleadoRepository empleadoRepository;
    @Override
    public CrudRepository<Empleado, Integer> getDao() {
        return empleadoRepository;
    }

}
