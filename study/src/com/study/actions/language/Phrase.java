package com.study.actions.language;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.study.entity.*;
import com.study.service.ServiceInterf;

@Controller
public class Phrase implements MessageSourceAware {

	@Autowired
	private ServiceInterf serviceInterf;
	
	private MessageSource messageSource;
	private final String PAGE_NAME = "study";
	private final Integer ORDER = 10;

	@RequestMapping(value = "study", method = RequestMethod.GET)
	public String setupForm(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null){
		Phrases phrases = serviceInterf.getLastPhraseByLanguage(user,
				((List<Phrases>) user.getPhrases()).get(0));
		model.addAttribute("phrases", phrases);
		return PAGE_NAME;
		}
		return "index";
	}

	@RequestMapping(value = "phraseAction", params = "showNextPhrase")
	public String showNextPhrase(@ModelAttribute("phrases") Phrases phrases,
			BindingResult result, Model model, HttpServletRequest request) {
		if (phrases != null) {
			if (phrases.getOrder() != null && phrases.getOrder().equals(ORDER)) {
				request.getSession().invalidate();
				model.addAttribute("endDemo",  messageSource.getMessage(("end"),
						null, LocaleContextHolder.getLocale()));
				return "end";
			} else {
				phrases = serviceInterf.getNextPhraseByLanguage(phrases);
				model.addAttribute("phrases", phrases);
			}
		}
		return PAGE_NAME;
	}

	@RequestMapping(value = "phraseAction", params = "showRightAnswer")
	public String showRightAnswer(@ModelAttribute("phrases") Phrases phrases,
			BindingResult result, Model model, HttpServletRequest request) {
		if (phrases != null) {
			model.addAttribute("answer", phrases.getTextTo());
			model.addAttribute("phrases", phrases);
		}
		return PAGE_NAME;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
