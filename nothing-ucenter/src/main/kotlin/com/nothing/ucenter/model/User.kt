package com.nothing.ucenter.model



import java.io.Serializable
import javax.persistence.*

@Entity
@Table
class User : BaseModel(), Serializable {

	@Column
	var email: String? = null

	@Column
	var nickName: String? = null

	@Column
	var password: String? = null

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = arrayOf(JoinColumn(name = "userId")), inverseJoinColumns = arrayOf(JoinColumn(name = "roleId")))
	var roles:List<Role>? = ArrayList()
}
