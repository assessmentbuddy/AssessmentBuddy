package org.assessmentbuddy

import org.assessmentbuddy.model.User

class UserController {
	def bcryptService

    def index() {
		// For admin, show paginated list of users, otherwise, show current user
		def users = session.user.isAdmin() ? User.list(params) : [ session.user ]
		def userCount = session.user.isAdmin() ? User.count() : 1
		[ users: users, userCount: userCount ]
	}
	
	def create() {
		// Edit a new user
		redirect( action: 'edit', params: [ user: new User() ] )
	}
	
	def edit() {
		User userToEdit
		
		if (!params.id) {
			// Create a new user
			userToEdit = new User()
		} else {
			// Edit existing user
			userToEdit = User.get(params.id)
			if (!userToEdit) {
				response.sendError(404)
				return
			}
		}
		
		[ userToEdit: userToEdit ]
	}
	
	def save() {
		// attempt to save
		User userToSave = new User(params) // create user object based on form params
		
		// FIXME: ensure that password and passwordConfirm match
		
		if (params.password && params.password != "") {
			params.passwordHash = bcryptService.hashPassword(params.password)
		}
		
		if (!userToSave.save()) {
			// Failed to save, redirect to edit page
			flash.message = "Could not save user"
			redirect( action: 'edit', params: params, id: params.id )
			return
		}
		
		// save successful
		flash.message = "User ${userToSave.userName} saved successfully"
		redirect( action: "index" )
	}
}
