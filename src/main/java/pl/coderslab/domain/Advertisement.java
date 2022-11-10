package pl.coderslab.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advertisements")
public class Advertisement {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String description;
	private String location;
	@Column(updatable = false)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	public void created() {
		LocalDateTime time = LocalDateTime.now();
		this.setCreatedAt(time);
	}

	@PreUpdate
	public void update() {
		LocalDateTime time = LocalDateTime.now();
		this.setUpdatedAt(time);
	}

	@ManyToOne
	private User user;
	@ManyToOne
	private Category category;
}
