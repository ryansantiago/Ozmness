package com.onb.ozmness

class EmployeePosition {
	
	String name
	
    static constraints = {
		name (unique: true, nullable: true)
    }
	
	String toString() {
		name
	}
}
