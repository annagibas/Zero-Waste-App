package pl.coderslab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.controller.validator.UserValidator;
import pl.coderslab.domain.User;
import pl.coderslab.service.SecurityService;
import pl.coderslab.service.UserService;

@Controller
public class UserController {

	private UserService userService;
	private SecurityService securityService;
	private UserValidator userValidator;

	public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
		super();
		this.userService = userService;
		this.securityService = securityService;
		this.userValidator = userValidator;
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		if (securityService.isAuthenticated()) {
			return "redirect:/";
		}

		model.addAttribute("userForm", new User());

		return "registrationForm";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registrationForm";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (securityService.isAuthenticated()) {
			return "redirect:/";
		}

		if (error != null)
			model.addAttribute("error", "Login lub hasło nieprawidłowe.");

		if (logout != null)
			model.addAttribute("message", "Wylogowano poprawnie.");

		return "loginForm";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

}