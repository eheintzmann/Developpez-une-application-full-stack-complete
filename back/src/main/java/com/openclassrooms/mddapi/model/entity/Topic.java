package com.openclassrooms.mddapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.*;

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

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "topic",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Post> posts = new ArrayList<>();

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(
			fetch = FetchType.LAZY,
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
