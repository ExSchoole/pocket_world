package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.model.Book;
import org.exschool.pocketworld.model.City;

public interface CityService {
	
	//List<City> allCities();
	
	City get(long id);

	//List<City> getByTitle(String title);
	
	void save(City entity);

}
