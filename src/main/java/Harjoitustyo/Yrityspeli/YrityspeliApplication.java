package Harjoitustyo.Yrityspeli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Harjoitustyo.Yrityspeli.domain.AppUser;
import Harjoitustyo.Yrityspeli.domain.AppUserRepository;
import Harjoitustyo.Yrityspeli.domain.Company;
import Harjoitustyo.Yrityspeli.domain.CompanyRepository;
import Harjoitustyo.Yrityspeli.domain.Data;
import Harjoitustyo.Yrityspeli.domain.DataRepository;
	
@SpringBootApplication
public class YrityspeliApplication {
	
	private static final Logger log = LoggerFactory.getLogger(YrityspeliApplication.class);
	
	@Autowired
	DataRepository dataRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(YrityspeliApplication.class, args);
	}

	@Bean 
	public CommandLineRunner companyGame(DataRepository dataRepository, CompanyRepository companyRepository, AppUserRepository appUserRepository) { 
		return (args) -> { 
			log.info("create companies"); 
			 companyRepository.save(new Company("Stora Enso"));
			 companyRepository.save(new Company("Siili"));
			 companyRepository.save(new Company("Outotec"));
			 companyRepository.save(new Company("Verkkokauppa"));
			 companyRepository.save(new Company("Kesko"));
			 companyRepository.save(new Company("Elisa"));
			 companyRepository.save(new Company("Huhtamäki"));
			
			 log.info("create data");
			 dataRepository.save(new Data(2015, 10.00, 15.00, 20.00, 25.00, 30.00, 35.00, 40.00, 45.00, 50.00, 55.00, companyRepository.findByName("Stora Enso").get(0))); 
			 dataRepository.save(new Data(2016, 10.00, 15.00, 20.00, 25.00, 30.00, 35.00, 40.00, 45.00, 50.00, 55.00, companyRepository.findByName("Siili").get(0))); 
			 dataRepository.save(new Data(2017, 10.00, 15.00, 20.00, 25.00, 30.00, 35.00, 40.00, 45.00, 50.00, 55.00, companyRepository.findByName("Outotec").get(0)));
				
			log.info("create users");
			 AppUser user1 = new AppUser("visitor", "$2a$10$Whf2EwgHzVoiVBXi.GeNIOk2Iq/QYVT/17F1xvBB3TPlzRDFRA9vu", "visitoremail@email.com", "VISITOR");
			 AppUser user2 = new AppUser("user", "$2a$10$nO4u6Hk.m/lx43QDbcwhB.PkpTzJsDOhxx.5Ogo//PqPCOPy4RSb6", "useremail@email.com", "USER");
			 AppUser user3 = new AppUser("admin", "$2a$10$Ns.z6cfu7xLYv9rN3ptTOeZdXPq/kzVtRr8/A9QQWX53PkOINoHuW", "adminemail@email.com", "ADMIN");
			 appUserRepository.save(user1);
			 appUserRepository.save(user2);
			 appUserRepository.save(user3);
				
			 log.info("print all data"); for (Data data : dataRepository.findAll())
			 { System.out.println(data.toString()); }
			 
			 log.info("print all companies"); for (Company company : companyRepository.findAll())
			 { System.out.println(company.toString()); }
			 
			 }; }

	
}
