package com.nothing.ucenter.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table
class Menu : BaseModel(), Serializable {

	@Column
	var name: String? = null

	@Column
	var url: String? = null
}