package com.nothing.ucenter.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table
class Role : BaseModel(), Serializable {

	@Column
	var name: String? = null

	@Column
	var parentId: String? = null

//	@ManyToMany
//	@JoinTable(name = "role_menu", joinColumns = arrayOf(JoinColumn(name = "roleId")), inverseJoinColumns = arrayOf(JoinColumn(name = "menuId")))
//	var menus:List<Menu>? = ArrayList()

}