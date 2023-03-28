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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import Harjoitustyo.Yrityspeli.domain.CompanyRepository;
import Harjoitustyo.Yrityspeli.domain.Data;
import Harjoitustyo.Yrityspeli.domain.DataRepository;

@Controller

public class DataController {
private static final Logger log = LoggerFactory.getLogger(DataController.class);

	@Autowired
	DataRepository dataRepository;
	@Autowired
	CompanyRepository companyRepository;
	
	@GetMapping("/index")
	public String showIndex(Model model, ModelMap map) {
		model.addAttribute("data", dataRepository.findAll());
		map.addAttribute("se", dataRepository.findBySubmittedYear(2015).getTurnover());
		map.addAttribute("si", dataRepository.findBySubmittedYear(2016).getTurnover());
		map.addAttribute("ou", dataRepository.findBySubmittedYear(2017).getTurnover());
		return "index";
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/data")
	public String showData(Model model) {
		log.info("get data");
		model.addAttribute("data", dataRepository.findAll());
		return "data";
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("/addData")
	public String addData(Model model) {
		model.addAttribute("data", new Data());
		model.addAttribute("companies", companyRepository.findAll());
		return "addData";
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@PostMapping("/saveData")
	public String saveData(@Valid @ModelAttribute("data") Data data, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("error with validation");
			model.addAttribute("companies", companyRepository.findAll());
			return "addData";
		}
		dataRepository.save(data);
		return "redirect:/index?datasaved";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deleteData/{id}")
	public String deleteData(@PathVariable("id") Long id, Model model) {
		dataRepository.deleteById(id);
		return "redirect:/index?datadeleted";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/editData/{id}")
	public String editData(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editData", dataRepository.findById(id));
		model.addAttribute("companies", companyRepository.findAll());
		return "editData";
	}
	
	//Rest
	
	@GetMapping("/alldata")
	public @ResponseBody List<Data> showData() {
		log.info("show data");
		return (List<Data>) dataRepository.findAll();
		
	}
	
	 @GetMapping("/alldata/{id}")
	 public @ResponseBody Optional<Data> findData(@PathVariable("id") Long id) {
		 log.info("find data by id = " + id);
	     return dataRepository.findById(id);
	    }   
	 
	 @PostMapping("/alldata")
	 public @ResponseBody Data newData(@RequestBody Data newData) {
		log.info("save new data: " + newData);
		return dataRepository.save(newData);
	 }
	 
	@PutMapping("/alldata/{id}")
	public @ResponseBody Data editData(@RequestBody Data editedData, @PathVariable("id") Long id) {
		log.info("edit data: " + editedData);
		return dataRepository.save(editedData);
		}

		
	@DeleteMapping("/alldata/{id}")
	public @ResponseBody Iterable<Data> deleteData(@PathVariable("id") Long id) {
		log.info("delete data, id = " + id);
		dataRepository.deleteById(id);
		return dataRepository.findAll();
	}
}