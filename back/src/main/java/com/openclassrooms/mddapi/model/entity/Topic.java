package com.openclassrooms.mddapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "topics")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@OneToMany(
			mappedBy = "topic",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Post> posts = new ArrayList<>();

	@ManyToMany(
			mappedBy = "subscriptions"
	)
	private List<User> subscribers = new ArrayList<>();

	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

}
