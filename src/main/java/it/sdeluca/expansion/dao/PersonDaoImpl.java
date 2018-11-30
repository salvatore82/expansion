/**
 * 
 */
package it.sdeluca.expansion.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import it.sdeluca.expansion.LocalExpand;
import it.sdeluca.expansion.dto.Person;

/**
 * @author S.DeLuca
 *
 */
@Repository
public class PersonDaoImpl implements PersonDao {

	private static Logger log = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	private List<Person> personRepo;

	public PersonDaoImpl() {
		personRepo = new ArrayList<>();
		Person giuseppe = new Person();
		giuseppe.setFirstName("Giuseppe");
		giuseppe.setLastName("Garibaldi");
		personRepo.add(giuseppe);
	}	
	
	//Dummy implementation, it needs a database to fetch the correct entities based on expand parameter
	@Override
	public List<Person> getPersons() {
		List<String> expansion = LocalExpand.getExpands();
		log.debug("Expansion = ".concat(expansion.toString()));
		personRepo.forEach(new Consumer<Person>() {
			@Override
			public void accept(Person t) {
				if(expansion.contains("kids")) {
					Person ricciotti = new Person();
					ricciotti.setFirstName("Ricciotti");
					ricciotti.setLastName("Garibaldi");
					ricciotti.setKids(Arrays.asList(new Person("Peppino", "Garibaldi", null, null)));
					personRepo.get(0).setKids(Arrays.asList(ricciotti));
				} else if(!expansion.contains("kids")) {
					personRepo.get(0).setKids(null);
				}
				if(expansion.contains("spouse")) {
					Person ricciotti = new Person();
					ricciotti.setFirstName("Ricciotti");
					ricciotti.setLastName("Garibaldi");
					ricciotti.setKids(Arrays.asList(new Person("Peppino", "Garibaldi", null, null)));
					personRepo.get(0).setSpouse(new Person("Anita", "Garibaldi", null, null));
				} else if(!expansion.contains("spouse")) {
					personRepo.get(0).setSpouse(null);
				}
			}
		});
		return personRepo;
	}

}
