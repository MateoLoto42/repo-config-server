package com.circuitbraker.hotels_service.service;

import com.circuitbraker.hotels_service.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements IHotelService{

    List<Hotel> hotelList = new ArrayList<>();


    @Override
    public List<Hotel> getHotelsByCityId(Long city_id) {

        List<Hotel> hotelCityList = new ArrayList<>();

        //carga de la BD Logica para agilizar
        this.loadHotels();

        //busca si la id de city coincide con la del hotel
        for(Hotel h:hotelCityList) {
            if (h.getCity_id() == city_id) {
                hotelCityList.add(h);
            }
        }
        //Devuelve la lista con los hoteles de esa ciudad
        return hotelCityList;
    }

    public void loadHotels(){
        hotelList.add(new Hotel(1L,"Hotel1",5,1L));
        hotelList.add(new Hotel(2L,"Hotel2",5,2L));
        hotelList.add(new Hotel(3L,"Hotel3",5,3L));
        hotelList.add(new Hotel(4L,"Hotel4",5,4L));
        hotelList.add(new Hotel(5L,"Hotel5",5,4L));
        hotelList.add(new Hotel(6L,"Hotel6",5,1L));
        hotelList.add(new Hotel(7L,"Hotel7",5,2L));
        hotelList.add(new Hotel(8L,"Hotel8",5,3L));
    }

}
