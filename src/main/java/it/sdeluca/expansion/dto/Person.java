package it.sdeluca.expansion.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Person {

	private String firstName;
	private String lastName;
	private Person spouse;
	private List<Person> kids;
	
}
