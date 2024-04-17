package com.openclassrooms.mddapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	@OneToMany(
			mappedBy = "post",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "topic_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Topic topic;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private User author;

	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

}
