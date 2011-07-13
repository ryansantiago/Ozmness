package com.onb.ozmness

import grails.test.*

class ProjectTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSaveProject() {
		mockDomain(Project)
		mockDomain(Employee)

		def lead = createSampleEmployee()
		def project = new Project(name: "Project One", lead: lead)

		project.save()

		project.validate()

		assertNotNull(project)
	}

	void testDeleteProject() {

		mockDomain(Project)
		mockDomain(Employee)

		def lead = createSampleEmployee()
		def project = new Project(name: "Project One", lead: lead)

		project.save()

		assertNotNull(project)

		project.delete()

		project = Project.findByName("Project One")

		assertNull(project)
	}

	void testConstraints() {

		mockDomain(Project)
		mockDomain(Employee)

		def lead = createSampleEmployee()
		def project = new Project(name: "Project One", lead: lead)

		project.save()

		project = new Project(name: "Project One", lead: lead)


		println "project.validate: ${project.save()}"
		println "hasErrors: ${project.hasErrors()}"
		project.errors.allErrors.each { println it }
		assertFalse project.validate()
	}

	private Employee createSampleEmployee() {
		def employee = new Employee()

		employee.setUsername("ryan01")
		employee.setLastName("Santiago")
		employee.setFirstName("Ryan")
		employee.setPassword("1234")
		employee.setEnabled(true)
		employee.setAccountExpired(false)
		employee.setAccountLocked(false)
		employee.setPasswordExpired(false)
		//employee.setPosition(employee)
		//employee.setMentor(employee)
		mockForConstraintsTests(User)
		mockForConstraintsTests(Employee)

		assert employee.validate()
		return employee
	}
}
