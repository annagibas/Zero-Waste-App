package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.domain.Advertisement;
import pl.coderslab.domain.Category;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

	List<Advertisement> findByCategory(Category name);

	List<Advertisement> findByUserId(Long userId);

}
