package com.meli.desafio.Service;

import com.meli.desafio.Exceptions.*;
import com.meli.desafio.Models.HotelDTO;
import com.meli.desafio.Models.ReservedDTO;

import java.util.Date;
import java.util.List;

public interface IBookingService {
    List<HotelDTO> getHotels(Date dateFrom, Date dateTo, String destination, String hotelCode) throws InvalidInput, DestinationDoesNotExistException, InvalidPeriodException, CodeDoesNotExistException;

    ReservedDTO createInvoice(ReservedDTO booking) throws InvalidPeriodException, InvalidInput, CodeDoesNotExistException, DestinationDoesNotExistException, RoomSizeException, InvalidCreditDuesException, InvalidDebitDuesException;
}
