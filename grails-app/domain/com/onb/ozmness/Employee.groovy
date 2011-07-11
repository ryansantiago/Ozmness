package com.onb.ozmness

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
		//projects cascade: 'none'
	}
	
	String toString() {
		lastName + ", " + firstName
	}
}
