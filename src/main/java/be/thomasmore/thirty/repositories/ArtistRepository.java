package be.thomasmore.thirty.repositories;

import be.thomasmore.thirty.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
}
