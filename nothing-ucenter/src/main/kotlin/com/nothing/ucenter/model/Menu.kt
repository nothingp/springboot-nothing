package com.nothing.ucenter.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table
class Menu : BaseModel(), Serializable {

	@Column
	var name: String? = null

	@Column
	var url: String? = null
}