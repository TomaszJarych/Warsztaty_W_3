package school.domain;

import java.time.LocalDateTime;

public class Solution {
	private long id;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String description;
	private long exerciseId;
	private long usersId;

	public Solution(String description, long exerciseId, long usersId) {
		LocalDateTime localDateTime = LocalDateTime.now();
		this.description = description;
		this.exerciseId = exerciseId;
		this.usersId = usersId;
		this.created = localDateTime;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public String getDescription() {
		return description;
	}

	public long getExercise_id() {
		return exerciseId;
	}

	public long getUsers_id() {
		return usersId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", created=" + created + ", updated=" + updated + ", description=" + description
				+ ", exercise_id=" + exerciseId + ", users_id=" + usersId + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	
	
}
