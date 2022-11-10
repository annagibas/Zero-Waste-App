package pl.coderslab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.coderslab.domain.Advertisement;
import pl.coderslab.domain.Category;
import pl.coderslab.domain.User;
import pl.coderslab.repository.AdvertisementRepository;
import pl.coderslab.repository.CategoryRepository;

@Service
public class AdvertisementService {

	private AdvertisementRepository advertisementRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;

	public AdvertisementService(AdvertisementRepository advertisementRepository, UserService userService,
			CategoryRepository categoryRepository) {
		super();
		this.advertisementRepository = advertisementRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	public List<Advertisement> getAll() {
		return advertisementRepository.findAll();
	}

	public List<Advertisement> getByCategory(String name) {
		Optional<Category> category = categoryRepository.findById(name);
		return advertisementRepository.findByCategory(category.get());
	}

	public List<Advertisement> getByUser(String username) {
		User user = userService.findByUsername(username);

		return advertisementRepository.findByUserId(user.getId());
	}

	public void add(Advertisement advertisement, String username) {
		User user = userService.findByUsername(username);
		advertisement.setUser(user);
		Optional<Category> category = categoryRepository.findById(advertisement.getCategory().getName());
		if (category.isEmpty()) {
			Category newCategory = new Category();
			newCategory.setName(advertisement.getCategory().getName());
			categoryRepository.save(newCategory);
			advertisement.setCategory(newCategory);
		} else {
			advertisement.setCategory(category.get());

		}

		advertisementRepository.save(advertisement);

	}
}
