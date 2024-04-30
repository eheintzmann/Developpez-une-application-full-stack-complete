package com.openclassrooms.mddapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne
	@JoinColumn(
			name = "post_id",
			nullable = false
	)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Post post;

	@ManyToOne
	@JoinColumn(
			name = "user_id",
			nullable = false
	)
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
