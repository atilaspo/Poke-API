package com.meli.desafio.Service;

import com.meli.desafio.Exceptions.*;
import com.meli.desafio.Models.BookingDTO;
import com.meli.desafio.Models.HotelDTO;
import com.meli.desafio.Models.ReservedDTO;
import com.meli.desafio.Repository.IHotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IHotelsRepository repository;

    @Override
    public List<HotelDTO> getHotels(Date from, Date to, String destination, String hotelCode) throws InvalidInput, DestinationDoesNotExistException, InvalidPeriodException, CodeDoesNotExistException {

        List<HotelDTO> listHotel = repository.getHotels();

        if(to == null && from == null && destination == null) {
            return listHotel;
        } else {
            return getHotelFiltered(from, to, destination, hotelCode);
        }
    }


    private List<HotelDTO> getHotelFiltered(Date from, Date to, String destination, String hotelCode) throws DestinationDoesNotExistException, InvalidInput, InvalidPeriodException, CodeDoesNotExistException {
        if(!to.before(from))
        {
            List<HotelDTO> listHotel = new ArrayList<>();

            if(destination != null)
                if(!destinationExists(destination)){
                    throw new DestinationDoesNotExistException("The destination does not exist");
                }
            listHotel = repository.getHotelByDestination(destination);

            if(hotelCode != null) {
                if(!codeExists(hotelCode)){
                    throw new CodeDoesNotExistException("The Hotel Code does not exist");
                }
                listHotel = repository.getHotelByCode(hotelCode);
            }

                List<HotelDTO> filterHotel;
                filterHotel = listHotel.stream()
                        .filter(f -> destination == null || f.getDestination().equalsIgnoreCase(destination))
                        .filter(f -> from == null || (from.after(f.getDateFrom()) && configDay(from,-1).before(f.getDateTo())))
                        .filter(f -> to == null || (to.before(configDay(f.getDateTo(),1)) && to.after(f.getDateFrom())))
                        .collect(Collectors.toList());
                if(!filterHotel.isEmpty())
                    return filterHotel;
                else
                    throw new InvalidPeriodException("There is no availability in this period");
        }
                throw new InvalidInput("The From date should be lower than the To date");
    }
    @Override
    public ReservedDTO createInvoice(ReservedDTO booking) throws InvalidPeriodException, InvalidInput, CodeDoesNotExistException, DestinationDoesNotExistException, RoomSizeException, InvalidCreditDuesException, InvalidDebitDuesException {
        Date from = booking.getBooking().getDateFrom();
        Date dateFrom = configDay(from, 1);

        HotelDTO hotelDTO = getHotelFiltered(dateFrom, booking.getBooking().getDateTo(), null, booking.getBooking().getHotelCode()).get(0);

        if(!roomMatch(booking.getBooking())) {
            throw new RoomSizeException("Error Room size");
        }

        booking.setUserName(booking.getUserName());
        booking.setAmount(subTotal(booking.getBooking()));
        booking.setInterest(getInterests(booking));

        Double interests = getInterests(booking);
        Double toPercentage = transformToPercentage(interests);
        Double total = (toPercentage + 1) * booking.getAmount();
        booking.setTotal(total);
        booking.setBooking(booking.getBooking());
        booking.getBooking();
        booking.getStatusCode();
        return booking;
    }

    private Date configDay(Date fecha, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    private Boolean destinationExists(String destination) {
        for (HotelDTO hotel: repository.getHotels()) {
            if(hotel.getDestination().equalsIgnoreCase(destination)){
                return true;
            }
        }
        return false;
    }

    private Boolean codeExists(String hotelCode) {
        for (HotelDTO hotel: repository.getHotels()) {
            if(hotel.getHotelCode().equalsIgnoreCase(hotelCode)){
                return true;
            }
        }
        return false;
    }

    private Boolean roomMatch(BookingDTO booking) {
        if(booking.getRoomType().equalsIgnoreCase("SINGLE") && booking.getPeopleAmount() == 1) {
            return true;
        }
        if(booking.getRoomType().equalsIgnoreCase("DOUBLE") && booking.getPeopleAmount() == 2 ){
            return true;
        }
        if(booking.getRoomType().equalsIgnoreCase("TRIPLE") && booking.getPeopleAmount() == 3) {
            return true;
        }
        if (booking.getRoomType().equalsIgnoreCase("MULTIPLE") && booking.getPeopleAmount() >= 4 && booking.getPeopleAmount() <= 10) {
            return true;
        } else
            return false;
    }

    private double transformToPercentage(double interests) throws InvalidDebitDuesException, InvalidCreditDuesException {
        if(interests == 0) {
            return 1;
        }

        return interests / 100;
    }


    private double getInterests(ReservedDTO booking) throws InvalidCreditDuesException, InvalidDebitDuesException {
        String creditStr = "CREDIT";
        String debitStr = "DEBIT";
        String paymentMethod = booking.getBooking().getPaymentMethod().getType();
        if(creditStr.equalsIgnoreCase(paymentMethod)) {
            if(booking.getBooking().getPaymentMethod().getDues() > 1 && booking.getBooking().getPaymentMethod().getDues() <= 3){
                return  5;
            }
            if(booking.getBooking().getPaymentMethod().getDues() > 3 && booking.getBooking().getPaymentMethod().getDues() <=5){
                return 10;
            }
            if(booking.getBooking().getPaymentMethod().getDues() > 5 && booking.getBooking().getPaymentMethod().getDues() <=9){
                return 15;
            }
            if(booking.getBooking().getPaymentMethod().getDues() > 9){
                throw new InvalidCreditDuesException("The maximum amount of dues is 9");
            }

        }
        if(debitStr.equalsIgnoreCase(paymentMethod)){
            if(booking.getBooking().getPaymentMethod().getDues() != 1) {
                throw new InvalidDebitDuesException("If the payment method is DEBIT is only one payment");
            }
        }
        return 0;

    }

    private double subTotal(BookingDTO booking) {
        List<HotelDTO> bookingByCode = repository.getHotelByCode(booking.getHotelCode());
        Double pricePerNight = bookingByCode.get(0).getPricePerNight();
        return getStayDays(booking) * pricePerNight;
    }

    private Integer getStayDays(BookingDTO booking){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFrom = booking.getDateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateTo = booking.getDateTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dateFrom, dateTo).getDays();
    }
}




















