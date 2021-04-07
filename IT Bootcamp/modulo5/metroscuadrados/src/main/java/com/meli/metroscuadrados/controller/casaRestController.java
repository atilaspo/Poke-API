package com.meli.metroscuadrados.controller;

import com.meli.metroscuadrados.DTOS.CasaDTO;
import com.meli.metroscuadrados.DTOS.HabitacionesDTO;
import com.meli.metroscuadrados.DTOS.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class casaRestController {
    @PostMapping ("/casas")
    @ResponseBody
    public ResponseDTO casaInfo(@RequestBody CasaDTO casaDTO){
        ResponseDTO respuesta = new ResponseDTO();
        respuesta.setTamaño(calcularTamaño(casaDTO));
        respuesta.setValorTotal((int) (800 * calcularTamaño(casaDTO)));

        for (HabitacionesDTO x: casaDTO.getHabitacion()){
            Double tamañoHabitacion = x.getAncho() * x.getLargo();
            x.setTamaño(tamañoHabitacion);
        }

        respuesta.setHabitacionMasGrande(Collections.max(casaDTO.getHabitacion(), (a, b) -> (int) (a.getTamaño() - b.getTamaño())));
        respuesta.setHabitaciones(casaDTO.getHabitacion());
        return respuesta;
    }

    private Double calcularTamaño(CasaDTO casaDTO) {

        List<HabitacionesDTO> habitacionesList =casaDTO.getHabitacion();
        Double tamañoFinal = 0d;
        for (HabitacionesDTO x: habitacionesList){
            tamañoFinal += x.getLargo() * x.getAncho();
        }
        return tamañoFinal;
    }
}
