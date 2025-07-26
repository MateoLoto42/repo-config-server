package com.circuitbraker.cities_service.repository;

import com.circuitbraker.cities_service.dto.HotelDTO;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//mismo nombre de la app en aureka
//asi ya no es necesario escribir la URL
@FeignClient(name = "hotels-service")
public interface IHotelsAPI {

    @GetMapping("/hotels/{city_id}")
    public List<HotelDTO> getHotelsByCityId(@PathVariable Long city_id);
}
