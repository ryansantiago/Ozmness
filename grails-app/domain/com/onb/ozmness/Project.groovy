package com.onb.ozmness

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class Project {

	def springSecurityService
	
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
