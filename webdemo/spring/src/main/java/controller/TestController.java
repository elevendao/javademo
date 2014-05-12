package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.User;

@Controller
public class TestController {
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="/form.do")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, ModelAndView mv) {
		mv.setViewName("/form");
		User user = userManager.getUserById(1);
		System.out.println(user);
		return mv;
	}
	
	@RequestMapping(value="/register.do")
	public ModelAndView register(HttpServletRequest request,
			HttpServletResponse response, User user, ModelAndView mv) {
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getDesc());
		mv.addObject("user", user);
		mv.setViewName("/info");
		return mv;
	}
}
