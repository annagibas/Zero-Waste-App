package pl.coderslab.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pl.coderslab.domain.Category;
import pl.coderslab.repository.CategoryRepository;

@Component
public class InitService {

	private CategoryRepository categoryRepository;

	public InitService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void addCategories() {

		Category category1 = new Category();
		category1.setName("Narzędzia");
		categoryRepository.save(category1);

		Category category2 = new Category();
		category2.setName("Farby i tapety");
		categoryRepository.save(category2);

		Category category3 = new Category();
		category3.setName("Drewno");
		categoryRepository.save(category3);

		Category category4 = new Category();
		category4.setName("Cegły i pustaki");
		categoryRepository.save(category4);

		Category category5 = new Category();
		category5.setName("Płytki i panele");
		categoryRepository.save(category5);

		Category category6 = new Category();
		category6.setName("Cementy i zaprawy");
		categoryRepository.save(category6);

	}

}
