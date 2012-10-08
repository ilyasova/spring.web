package com.study.actions.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.study.entity.*;
import com.study.service.ServiceInterf;
import com.study.util.*;

@Controller
public class Authentication implements MessageSourceAware {
	
	@Autowired
	private ServiceInterf serviceInterf;
	
	private MessageSource messageSource;
	private final String REDIRECT_PAGE = "redirect:/choice.html";
	private final String REDIRECT_HOME_PAGE = "redirect:/index.html";

	@RequestMapping(value = { "index", "/" }, method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("regUser", new User());
		return "index";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user,
			BindingResult result, Model model, HttpServletRequest request) {
		user.setPassword(PasswordEncryptor.getEncryptedPassword(user
				.getPassword()));
		User _user = serviceInterf.getAuthorize(user);
		if (_user != null) {
			request.getSession().setAttribute("user", _user);
		} else {
			model.addAttribute("loginError", messageSource.getMessage("wrongPassword",
					null, LocaleContextHolder.getLocale()));
			return setupForm(model);
		}
		return REDIRECT_PAGE;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("regUser") User user,
			BindingResult result, Model model, HttpServletRequest request) {
		if (serviceInterf.checkLoginFree(user).equals("free")) {
			user.setPassword(PasswordEncryptor.getEncryptedPassword(user
					.getPassword()));
			user = (User) serviceInterf.save(user);
			request.getSession().setAttribute("user", user);
		} else {
			model.addAttribute("regError", messageSource.getMessage(("loginBusy"),
					null, LocaleContextHolder.getLocale()));
			return setupForm(model);
		}
		return REDIRECT_PAGE;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(
			@RequestParam(value = "id", required = false) String id,
			Model model, HttpServletRequest request) {
		int idPhrase = 0;
		try {
			idPhrase = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return REDIRECT_HOME_PAGE;
		}
		serviceInterf.updateLanguageForUser((User) request.getSession()
				.getAttribute("user"), new Phrases(idPhrase));
		request.getSession().invalidate();
		return REDIRECT_HOME_PAGE;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
