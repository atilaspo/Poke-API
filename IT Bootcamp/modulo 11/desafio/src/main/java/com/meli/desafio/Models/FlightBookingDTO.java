package com.meli.desafio.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FlightReservationDTO {
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateTo;
    private String origin;
    private String destination;
    private String flightNumber;
    private Integer seats;
    private String seatType;
    List<PersonaDTO> people;
    PaymentMethodDTO paymentMethod;
}
