package pl.coderslab.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.domain.Advertisement;
import pl.coderslab.domain.Category;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.service.AdvertisementService;

@Controller
public class AdvertisementController {

	private AdvertisementService advertisementService;
	private CategoryRepository categoryRepository;

	public AdvertisementController(AdvertisementService advertisementService, CategoryRepository categoryRepository) {
		super();
		this.advertisementService = advertisementService;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/add")
	public String add(Model model) {

		model.addAttribute("advertisementForm", new Advertisement());
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "addingAdvertisementForm";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("advertisementForm") Advertisement advertisementForm, Principal principal) {

		advertisementService.add(advertisementForm, principal.getName());

		return "redirect:/advertisements";
	}

	@GetMapping("/advertisements")
	public String getAll(Model model, @RequestParam(required = false) String category) {

		List<Advertisement> advertisements = null;
		if (Objects.nonNull(category)) {
			advertisements = advertisementService.getByCategory(category);
		} else {
			advertisements = advertisementService.getAll();
		}

		model.addAttribute("advertisements", advertisements);

		return "advertisements";

	}

	@GetMapping("/advertisements/my")
	public String getAll(Model model, Principal principal) {

		List<Advertisement> advertisements = advertisementService.getByUser(principal.getName());

		model.addAttribute("advertisements", advertisements);

		return "advertisements";

	}

}
