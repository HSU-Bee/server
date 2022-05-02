package hsu.bee.petra.attraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.attraction.entity.Attraction;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
	}
