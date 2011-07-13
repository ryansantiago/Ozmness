package com.onb.ozmness

import grails.test.*

class EmployeePositionTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSaveEmployeePosition() {
		
		mockDomain(EmployeePosition)
		def pos = new EmployeePosition(name: "CEO")

		assert pos.validate();
		assert pos.save()

		pos = EmployeePosition.findByName("CEO")
		assertNotNull pos

		assert pos.validate()
		
		
	}

	void testDeleteEmployeePosition() {
		
		mockDomain(EmployeePosition)
		def pos = new EmployeePosition(name: "Associate Software Engineer")

		assert pos.validate();
		assert pos.save()

		pos = EmployeePosition.findByName("Associate Software Engineer")
		assertNotNull pos

		pos.delete()

		assertNull(EmployeePosition.findByName("Associate Software Engineer"))

		assert pos.validate()
	}
}
