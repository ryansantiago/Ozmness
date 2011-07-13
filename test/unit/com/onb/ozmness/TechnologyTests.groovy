package com.onb.ozmness

import grails.test.*

class TechnologyTests extends GrailsUnitTestCase {

	void testConstraints() {
		mockDomain(Technology)
		def tech = new Technology()

		tech.name = "Longhorn"
		mockForConstraintsTests(Technology)

		println "tech.validate: ${tech.validate()}"
		println "hasErrors: ${tech.hasErrors()}"
		tech.errors.allErrors.each { println it }
		println "tech.save: ${tech.save()}"
		println "hasErrors: ${tech.hasErrors()}"
		tech.errors.allErrors.each { println it }

		assert tech.validate()
		tech = new Technology(name: "Longhorn")

		println "tech.validate: ${tech.validate()}"
		println "hasErrors: ${tech.hasErrors()}"
		tech.errors.allErrors.each { println it }
		println "tech.save: ${tech.save()}"
		println "hasErrors: ${tech.hasErrors()}"
		tech.errors.allErrors.each { println it }

		assert tech.validate()
	}
}
