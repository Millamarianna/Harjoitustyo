package Harjoitustyo.Yrityspeli.domain;

import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Long> {

	Data findBySubmittedYear(int submittedYear);
	
}
