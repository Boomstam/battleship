package be.thomasmore.thirty.repositories;

import be.thomasmore.thirty.model.Ship;
import org.springframework.data.repository.CrudRepository;

public interface ShipRepository extends CrudRepository<Ship, Integer> {
    //Iterable<Ship> findByShipType(String shipType);
    /*Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
    Iterable<Venue> findByCapacityLessThanEqual(int capacity);
    Iterable<Venue> findByCapacityGreaterThan(int capacity);*/
}
