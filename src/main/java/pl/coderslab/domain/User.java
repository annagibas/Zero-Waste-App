package pl.coderslab.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	@Column(updatable = false)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean term;

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

	@OneToMany(mappedBy = "user")
	private List<Advertisement> advertisements;

}
