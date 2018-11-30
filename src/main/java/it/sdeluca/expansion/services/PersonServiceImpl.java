/**
 * 
 */
package it.sdeluca.expansion.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sdeluca.expansion.LocalExpand;
import it.sdeluca.expansion.dao.PersonDao;
import it.sdeluca.expansion.dto.Person;

/**
 * @author S.DeLuca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	private static Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonDao personDao;
	
	@Override
	public List<Person> getPersons() {
		log.debug("Expansion = ".concat(LocalExpand.getExpands().toString()));
		return personDao.getPersons();
	}

}
