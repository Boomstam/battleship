package be.thomasmore.thirty.repositories;

import be.thomasmore.thirty.model.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
}