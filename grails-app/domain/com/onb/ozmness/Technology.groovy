package com.onb.ozmness

class Technology {

	String name
	
    static constraints = {
		name(unique: true)
    }
	
	String toString() {
		name
	}
}
