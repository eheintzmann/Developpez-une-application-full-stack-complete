package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;
	
	// TODO : to finish...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
}
