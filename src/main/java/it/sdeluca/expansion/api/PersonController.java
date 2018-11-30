package it.sdeluca.expansion.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.sdeluca.expansion.LocalExpand;
import it.sdeluca.expansion.dto.Person;
import it.sdeluca.expansion.services.PersonService;

@RestController
@RequestMapping("/api/v1/persons")
@Api(value = "Controller to get persons")
public class PersonController {

	private static Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@ApiOperation("Get list of persons")
	@GetMapping
	public List<Person> getPersons() {
		log.debug("Expansion = ".concat(LocalExpand.getExpands().toString()));
		return personService.getPersons();
	}
	
	@ApiOperation("Get person identified by id")
	@GetMapping("/{id}")
	public Person getPerson(@ApiParam(value = "ID of the person to retrieve", required = true) @PathVariable Long id) {
		log.debug("Expansion = ".concat(LocalExpand.getExpands().toString()));
		return personService.getPersons().get(0);
	}
}