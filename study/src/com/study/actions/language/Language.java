package com.study.actions.language;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.study.entity.*;
import com.study.service.*;

@Controller
public class Language {

	@Autowired
	private ServiceInterf serviceInterf;

	@RequestMapping(value = "choice", method = RequestMethod.GET)
	public String setupForm(Model model) {
		List<Phrases.LANGUAGE> langslist = new ArrayList<Phrases.LANGUAGE>();
		langslist.add(Phrases.LANGUAGE.ENGLISH);
		langslist.add(Phrases.LANGUAGE.SPANISH);
		model.addAttribute("languagesList", langslist);
		model.addAttribute("phrases", new Phrases());
		return "choice";
	}

	@RequestMapping(value = "languageSelected", method = RequestMethod.POST)
	public String languageSelected(@ModelAttribute("phrases") Phrases phrases,
			BindingResult result, Model model, HttpServletRequest request) {
		Phrases phrase = serviceInterf.getLastPhraseByLanguage((User) request
				.getSession().getAttribute("user"), phrases);
		User user = (User) request.getSession().getAttribute("user");
		if (phrase == null) {
			phrase = serviceInterf.getNextPhraseByLanguage(new Phrases(phrases
					.getLanguage(), 0));
			serviceInterf.addLanguage(user, phrase);
		}
		List<Phrases> phraseList = new ArrayList<Phrases>();
		phraseList.add(phrase);
		user.setPhrases(phraseList);
		request.getSession().setAttribute("user", user);
		return "redirect:/study.html";
	}
}
