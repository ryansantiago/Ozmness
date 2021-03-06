package com.onb.ozmness

class Rating {

	long value
	String comment
	Date dateCreated
	Employee creator
	Employee rated
	Technology technology
	
    static constraints = {
		value(validator: {
			if (it<1 || it>3)
				return ["error.outOfRange", it]
		})
		comment()
		dateCreated()
		creator()
		rated()
		technology()
    }
	
	static mapping = {
//		technology cascade: 'none'
	}
	
	String toString() {
		comment + " (" + value + ")"
	}
}
