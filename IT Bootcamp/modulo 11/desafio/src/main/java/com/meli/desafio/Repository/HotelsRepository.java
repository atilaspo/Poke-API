package com.meli.desafio.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafio.Models.HotelDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingRepository implements IBookingRepository {
    List<HotelDTO> hotels;

    //Constructor from the Hoteles.json
    public BookingRepository() {
        this.hotels = loadDataBase();
    }


    @Override
    public List<HotelDTO> getHotels() {
        return hotels;
    }

    @Override
    public List<HotelDTO> getHotelByCode(String hotelCode) {
        List<HotelDTO> hotelByCode = hotels.stream()
                .filter(hotelDTO -> hotelDTO.getHotelCode().equalsIgnoreCase(hotelCode))
                .collect(Collectors.toList());
        return hotelByCode;
    }

    @Override
    public List<HotelDTO> getHotelByDestination(String destination) {
        List<HotelDTO> hotelByDestination = hotels.stream()
                .filter(hotelDTO -> hotelDTO.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
        return hotelByDestination;
    }

    //Method that Load the database from products.json
    private List<HotelDTO> loadDataBase() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:Hoteles.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HotelDTO>> typeReference = new TypeReference<>() {};
        List<HotelDTO> hotelDTOS = null;
        try{
            hotelDTOS = objectMapper.readValue(file, typeReference);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return hotelDTOS;
    }
}
