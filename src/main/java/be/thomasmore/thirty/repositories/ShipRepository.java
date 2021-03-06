package be.thomasmore.thirty.repositories;

import be.thomasmore.thirty.model.ShipClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends CrudRepository<ShipClass, Integer> {
    Iterable<ShipClass> findByShipType(String shipType);
    //Iterable<ShipClass> findByCapacityGreaterThan(int capacity);
    //Iterable<ShipClass> findByOutdoor(boolean isOutdoor);
    //Iterable<ShipClass> findByIndoor(boolean isIndoor);
    //Iterable<ShipClass> findByCapacityLessThanEqual(int capacity);
    //Iterable<ShipClass> findByCapacityGreaterThan(int capacity);

    @Query("SELECT sc FROM ShipClass sc WHERE lower(sc.shipType) LIKE concat('%', lower(:keyword), '%')")
    List<ShipClass> findByKeyword(@Param("keyword") String keyword);
}
