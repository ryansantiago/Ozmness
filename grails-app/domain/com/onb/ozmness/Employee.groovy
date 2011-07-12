package com.onb.ozmness

import java.util.Set;

class Employee extends User {
	
	String lastName
	String firstName
	EmployeePosition position
	Employee mentor
	
	static hasMany = [projects: Project]

    static constraints = {
		username()
		password()
		lastName()
		firstName()
		position()
		mentor(nullable: true)
		projects()
		enabled()
		accountExpired()
		passwordExpired()
		accountLocked()
    }
	
	static mapping = {
	}
	
	String toString() {
		lastName + ", " + firstName
	}
	
	List<User> getMentees(User user) {
		def menteeList = Employee.findAllByMentor(user)
		return menteeList.asList()
	}
}
