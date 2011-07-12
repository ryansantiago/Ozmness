package com.onb.ozmness

import java.awt.List;

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.SpringSecurityCoreVersion;

class RatingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def ratingInstanceList
		
		if (!springSecurityService.currentUser.username.equals("admin")) {
			ratingInstanceList = Rating.findAllByCreatorAndRatedNotEqual(springSecurityService.currentUser, springSecurityService.currentUser)
			ratingInstanceList += Rating.findAllByRated(Employee.findById(springSecurityService.currentUser.id))
		} else {
			ratingInstanceList = Rating.list(params)
		}
        return [ratingInstanceList: ratingInstanceList, ratingInstanceTotal: Rating.count()]
    }

    def create = {
        def ratingInstance = new Rating()
        ratingInstance.properties = params
		def ratedEmployeeList
		if (params.id) {
			ratedEmployeeList = Employee.findById(params.id)
		} else if (!springSecurityService.currentUser.username.equals("admin")) {
			ratedEmployeeList = Employee.findAllByMentor(springSecurityService.currentUser)
			ratedEmployeeList.add(springSecurityService.currentUser)
		}
        return [ratingInstance: ratingInstance, ratedEmployeeList: ratedEmployeeList]
    }

    def save = {
        def ratingInstance = new Rating(params)
		ratingInstance.creator = Employee.findById(springSecurityService.currentUser.id)
        if (!springSecurityService.currentUser.username.equals('admin') && ratingInstance.creator.id != springSecurityService.currentUser.id) {				
				flash.message = "${message(code: 'domain.invalidCreator', args: [message(code: 'rating.label', default: 'Rating'), 'creator'])}"
            render(view: "create", model: [ratingInstance: ratingInstance])
		} else if (ratingInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'rating.label', default: 'Rating'), ratingInstance.id])}"
            redirect(action: "show", id: ratingInstance.id)
        }
        else {
            render(view: "create", model: [ratingInstance: ratingInstance])
        }
    }

    def show = {
        def ratingInstance = Rating.get(params.id)
        if (!ratingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        } else if (springSecurityService.currentUser.username.equals('admin') || ratingInstance.creator == springSecurityService.currentUser) {
			return [ratingInstance: ratingInstance]
        } else {
			flash.message = "${message(code: 'rating.invalidRater')}"
            redirect(action: "byEmployee", id: ratingInstance.rated.id)
        }
    }

    def edit = {
        def ratingInstance = Rating.get(params.id)
        if (!ratingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
        else {
	        if (!springSecurityService.currentUser.username.equals('admin') && ratingInstance.creator.id != springSecurityService.currentUser.id) {				
				flash.message = "${message(code: 'domain.invalidCreator', args: [message(code: 'rating.label', default: 'Rating'), 'creator'])}"
				redirect(action: "list")
			} else {
				return [ratingInstance: ratingInstance]
			}
        }
    }

    def update = {
        def ratingInstance = Rating.get(params.id)
        if (ratingInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (ratingInstance.version > version) {
                    
                    ratingInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'rating.label', default: 'Rating')] as Object[], "Another user has updated this Rating while you were editing")
                    render(view: "edit", model: [ratingInstance: ratingInstance])
                    return
                }
            }
            ratingInstance.properties = params
			ratingInstance.creator = Employee.findById(springSecurityService.currentUser.id)
	        if (!springSecurityService.currentUser.username.equals('admin') && ratingInstance.creator.id != springSecurityService.currentUser.id) {				
				flash.message = "${message(code: 'domain.invalidCreator', args: [message(code: 'rating.label', default: 'Rating'), 'creator'])}"
                render(view: "edit", model: [ratingInstance: ratingInstance])
			} else if (!ratingInstance.hasErrors() && ratingInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'rating.label', default: 'Rating'), ratingInstance.id])}"
                redirect(action: "show", id: ratingInstance.id)
            }
            else {
                render(view: "edit", model: [ratingInstance: ratingInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def ratingInstance = Rating.get(params.id)
        if (ratingInstance) {
			
/*			if (!springSecurityService.currentUser.username.equals('admin') && ratingInstance.creator.id != springSecurityService.currentUser.id) {
				flash.message = "${message(code: 'domain.invalidCreator', args: [message(code: 'rating.label', default: 'Rating'), 'creator'])}"
                redirect(action: "list")
			}*/
            try {
				if (springSecurityService.currentUser.username.equals('admin') || ratingInstance.creator.id == springSecurityService.currentUser.id) {
	                ratingInstance.delete(flush: true)
	                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
	                redirect(action: "list")
				} else {
					flash.message = "${message(code: 'domain.invalidCreator', args: [message(code: 'rating.label', default: 'Rating'), 'creator'])}"
	                redirect(action: "byEmployee", id: ratingInstance.id)
				}
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
    }

    def byEmployee = {
		def employee = Employee.findById(params.id)
		def rated = [:]
		rated.id = employee.id
		rated.name = employee.username == springSecurityService.currentUser.username ? "My" : employee.firstName
		rated.thirdPerson = employee.firstName
        [ratingInstanceList: Rating.findAllByRated(employee), rated: rated]
    }

    def forEmployee = {
        def ratingInstance = new Rating()
        ratingInstance.properties = params
		ratingInstance.rated = Employee.findById(params.id)
        return [ratingInstance: ratingInstance]
    }
	
	def selection = {
		def mentees = Employee.findAllByMentor(springSecurityService.currentUser)
		def self = Employee.findById(springSecurityService.currentUser.id)
		return [mentees: mentees, self: self]
	}
}
