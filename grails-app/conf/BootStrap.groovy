import com.onb.ozmness.Role
import com.onb.ozmness.User
import com.onb.ozmness.UserRole
import grails.plugins.springsecurity.SpringSecurityService;

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->
		def roles = [:]
		def users = [:]
		roles.admin = Role.findByAuthority("ROLE_ADMIN") ?: \
			new Role(authority: "ROLE_ADMIN").save()
			
		println "AD " + springSecurityService.currentUser
		users.admin = User.findByUsername('admin') ?: \
			new User(username: 'admin',
				password: springSecurityService.encodePassword('1234'), enabled: true, accountExpired:false, accountLocked: false, passwordExpired: false).save()
		UserRole.create( users.admin, roles.admin )
    }
    def destroy = {
    }
}
