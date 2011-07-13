package com.onb.ozmness

import org.apache.commons.beanutils.converters.DateConverter;

import grails.test.*

class RatingTests extends GrailsUnitTestCase {
    
    void testConstraints() {
		mockDomain(Rating)
		mockDomain(Technology)
		mockDomain(Employee)
		
		def technology = new Technology(name: "Longhorn")
		def employee = createSampleEmployee()
		def rating = makeRating(employee, technology)
		
		assertNotNull rating
		assert rating.save()
		assertNotNull Rating.findByComment("Expert")
		
		println Rating.list()
    }
	
	private Employee createSampleEmployee() {
		def employee = new Employee()

		employee.setUsername("sample1")
		employee.setLastName("Sam")
		employee.setFirstName("Pol")
		employee.setPassword("password")
		employee.setEnabled(true)
		employee.setAccountExpired(false)
		employee.setAccountLocked(false)
		employee.setPasswordExpired(false)
		//employee.setMentor(employee)
		mockForConstraintsTests(User)
		mockForConstraintsTests(Employee)

		println "employee.validate: ${employee.validate()}"
		println "hasErrors: ${employee.hasErrors()}"
		employee.errors.allErrors.each { println it }

		assert employee.validate()
		return employee
	}
	
	private Rating makeRating(Employee employee, Technology technology) {
		mockForConstraintsTests(Rating)
		
		def rating = new Rating(
			dateCreated: new Date(),
			creator: employee,
			rated: employee,
			technology: technology)
		
		rating.comment = "Expert"
		rating.value = 3
		
		println "rating.validate: ${rating.validate()}"
		println "hasErrors: ${rating.hasErrors()}"
		rating.errors.allErrors.each { println it }
		
		return rating
	}
}
