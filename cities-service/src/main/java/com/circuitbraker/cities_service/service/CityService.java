package com.circuitbraker.cities_service.service;

import com.circuitbraker.cities_service.dto.CityDTO;
import com.circuitbraker.cities_service.model.City;
import com.circuitbraker.cities_service.repository.IHotelsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CityService implements ICityService{

    @Autowired
    private IHotelsAPI hotelsAPI;

    List<City> cities = new ArrayList<>();

    @Override
    @CircuitBreaker(name="hotels-service", fallbackMethod = "fallbackGetCitiesHotels")
    @Retry(name="hotels-service")
    public CityDTO getCitiesHotels(String name, String country) {

        //buscamos la ciudad original
        City city = this.findCity(name,country);

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity_id(city.getCity_id());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setContinent(city.getContinent());
        cityDTO.setState(city.getState());

        //buscamos la lista de hoteles en la API y asignamos
        cityDTO.setHotelList(hotelsAPI.getHotelsByCityId(city.getCity_id()));

        createException();

        return cityDTO;
    }
    /*
        Metodo fallback por si no se puede realizar la comunicacion con la API hotels-service
        Este metodo descia el flujo del programa para evitar que caiga el servicio hasta que
        se retome la comunicacion entre APIs
     */
    public CityDTO fallbackGetCitiesHotels( Throwable throwable){
        return new CityDTO(9999999L,"Fallido","Fallido","Fallido","Fallido",null);
    }

    public void createException(){
        throw new IllegalArgumentException("Prueba Resilience4J y Circuit Braker");
    }

    private City findCity(String name, String country){
        this.loadCities();
        for(City c:cities){
            if (c.getName().equals(name)){
                if (c.getCountry().equals(country)){
                    return c;
                }
            }
        }
        return null;
    }

    public void loadCities(){
        cities.add(new City(1L,"Buenos Aires","South America","Argentina","Buenos Aires"));
        cities.add(new City(2L,"San Salvador de Jujuy","South America","Argentina","Jujuy"));
        cities.add(new City(3L,"Mexico City","North America","Mexico","Mexico City"));
        cities.add(new City(4L,"Guadalajara","North America","Mexico","Jalisco"));
        cities.add(new City(5L,"Bogota","South America","Colombia","Cundinamarca"));
        cities.add(new City(6L,"Medellin","South America","Colombia","Antioquia"));
        cities.add(new City(7L,"Santiago","South America","Chile","Santiago Metropolitan"));
        cities.add(new City(8L,"Valparaiso","South America","Chile","Valparaiso"));
        cities.add(new City(9L,"Asuncion","South America","Paraguay","Asuncion"));
        cities.add(new City(10L,"Montevideo","South America","Uruguay","Montevideo"));
        cities.add(new City(11L,"Madrid","Europe","Spain","Community of Madrid"));
        cities.add(new City(12L,"Barcelona","Europe","Spain","Catalonia"));
        cities.add(new City(13L,"Seville","Europe","Spain","Andalucia"));
        cities.add(new City(14L,"Monterrey","South America","Mexico","Nuevo Leon"));
        cities.add(new City(15L,"Valencia","Europe","Spain","Valencian Community"));
    }

}
