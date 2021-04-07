package com.meli.desafio.Repository;
import com.meli.desafio.Models.HotelDTO;
import java.util.List;


public interface IBookingRepository {

    List<HotelDTO> getHotels();

    List<HotelDTO> getHotelByCode(String hotelCode);

    List<HotelDTO> getHotelByDestination(String destination);
}
