package hsu.bee.petra.attraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.attraction.entity.Attraction;

import java.util.Optional;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

	Optional<Attraction> findByName(String attractionName);

}
