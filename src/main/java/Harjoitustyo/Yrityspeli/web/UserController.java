package Harjoitustyo.Yrityspeli.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Harjoitustyo.Yrityspeli.domain.AppUser;
import Harjoitustyo.Yrityspeli.domain.AppUserRepository;
import Harjoitustyo.Yrityspeli.domain.SignupForm;

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private AppUserRepository repository; 
	
	
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    @RequestMapping(value = "/signup")
    public String addUser(Model model){
    	log.info("new user template " + new SignupForm());
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	log.info("saveuser: newUser is " + signupForm.toString());
    	
    	if (!bindingResult.hasErrors()) { 
    	log.info("no errors in singupform");
    	
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { 
    			log.info("passwords equal");
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	AppUser newUser = new AppUser();
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setEmail(signupForm.getEmail());
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setRole("USER");
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { 
		    		log.info("username is unique");
		    		repository.save(newUser);
		    	}
		    	else {
		    		log.info("username exists");
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			log.info("passwords dont match");
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");    	
    			return "signup";
    		}
    	}
    	else {
			return "signup";}
    	
    	return "redirect:login";    	
    }    
}
    
