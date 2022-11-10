package pl.coderslab.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "categories")
public class Category {

	@Id
	@Column(length = 100)
	private String name;
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

	@OneToMany(mappedBy = "category")
	private List<Advertisement> advertisements;
}
