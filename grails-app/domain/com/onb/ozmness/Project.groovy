package com.onb.ozmness

class Project {

	String name
	static hasMany = [technologies: Technology, employees: Employee]
	Employee lead
	static belongsTo = Employee
	
	
    static constraints = {
		name(unique: true)
		lead()
		technologies()
    }
	
	String toString() {
		name
	}
}
