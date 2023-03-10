package com.employee.attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.employee.attendance.entity.Attendance;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.Person;

public class Sample {

	public static void main(String[] args)  {

		
		
		
		 List<Person> people = new ArrayList<>();
	        people.add(new Person("John", "London", 21));
	        people.add(new Person("Swann", "London", 21));
	        people.add(new Person("Kevin", "London", 23));
	        people.add(new Person("Monobo", "Tokyo", 23));
	        people.add(new Person("Sam", "Paris", 23));
	        people.add(new Person("Nadal", "Paris", 31));
	        
	        // Now let's group all person by city in pre Java 8 world        
	        Map<String,List<Person>> personByCity 
	                       = new HashMap<>();
	        
	        for(Person p : people){
	            if(!personByCity.containsKey(p.getCity())){
	                personByCity.put(p.getCity(), new ArrayList<>());                
	            }
	            personByCity.get(p.getCity()).add(p);
	        }
	        
	        System.out.println("Person grouped by cities : " + personByCity);
	        
	        // Let's see how we can group objects in Java 8
	        personByCity =  people.stream()
	                         .collect(Collectors.groupingBy(Person::getCity));
	        System.out.println("Person grouped by cities in Java 8: " 
	                         + personByCity);
	        
	        // Now let's group person by age
	        
	        Map<Integer,List<Person>> personByAge = people.stream()
	                          .collect(Collectors.groupingBy(Person::getAge));
	        System.out.println("Person grouped by age in Java 8: " 
	                                    + personByAge);
	    }

}

    
