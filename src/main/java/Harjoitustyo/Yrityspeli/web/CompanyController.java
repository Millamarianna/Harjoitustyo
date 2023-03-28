package Harjoitustyo.Yrityspeli.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import Harjoitustyo.Yrityspeli.domain.Company;
import Harjoitustyo.Yrityspeli.domain.CompanyRepository;

@Controller
public class CompanyController {
private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyRepository companyRepository;
	

	@GetMapping("/companies")
	public String showCompanies(Model model) {
		log.info("get companies");
		model.addAttribute("companies", companyRepository.findAll());
		return "companies";
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/addCompany")
	public String addCompany(Model model) {
		model.addAttribute("company", new Company());
		return "addCompany";
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@PostMapping("/saveCompany")
	public String saveCompany(@Valid @ModelAttribute("newCompany") Company company, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			log.info("error with validation");
			model.addAttribute("newCompany", company);
			return "addCompany";
		}
		companyRepository.save(company);
		return "redirect:/index?companysaved";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deleteCompany/{id}")
	public String deleteCompany(@PathVariable("id") Long id, Model model) {
		companyRepository.deleteById(id);
		return "redirect:/companies";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/editCompany/{id}")
	public String editData(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editCompany", companyRepository.findById(id));
		return "editCompany";
	}
	
	
	//Rest
	
	@GetMapping("/allcompanies")
	public @ResponseBody List<Company> showCompanies() {
		log.info("show Companies");
		return (List<Company>) companyRepository.findAll();
		
	}
	
	 @GetMapping("/allcompanies/{id}")
	 public @ResponseBody Optional<Company> findCompany(@PathVariable("id") Long id) {
		 log.info("find company, id = " + id);
	     return companyRepository.findById(id);
	    }   
	 
	 @PostMapping("/allcompanies")
	 public @ResponseBody Company newCompany(@RequestBody Company newCompany) {
		log.info("save new company " + newCompany);
		return companyRepository.save(newCompany);
	 }
	 
	@PutMapping("/allcompanies/{id}")
	public @ResponseBody Company editCompany(@RequestBody Company editedCompany, @PathVariable("id") Long id) {
		log.info("edit company " + editedCompany);
		return companyRepository.save(editedCompany);
		}

		
	@DeleteMapping("/allcompanies/{id}")
	public @ResponseBody Iterable<Company> deleteCompany(@PathVariable("id") Long id) {
		log.info("delete company, id = " + id);
		companyRepository.deleteById(id);
		return companyRepository.findAll();
	}
	 
}