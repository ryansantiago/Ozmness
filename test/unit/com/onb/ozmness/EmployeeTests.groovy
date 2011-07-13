package com.onb.ozmness

import org.codehaus.groovy.grails.compiler.injection.GrailsInjectionOperation;

import grails.test.*

class EmployeeTests extends GrailsUnitTestCase {

	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testEmployeeSave() {
		mockDomain(User)
		mockDomain(Employee)

		def employee = createSampleEmployee()
		def employeeList = Employee.list()
		assertEquals 0, employeeList.size()
		employee.save()
		employeeList = Employee.list()
		assertEquals 1, employeeList.size()
		assertEquals "sample1", employee.getUsername()
		assertEquals "password", employee.getPassword()
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
}
