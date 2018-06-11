package school.domain;

import java.time.LocalDateTime;

public class Solution {
	private long id;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String description;
	private long exerciseId;
	private long usersId;

	private static final String ID_COLUMN_NAME = "id";
	private static final String INSERT_INTO_SOLUTION = "INSERT INTO warsztaty2.solutions(created, description, exercise_id, users_id)VALUES (?,?,?,?);";
	private static final String LOAD_SOLUTION_BY_ID = "SELECT * FROM warsztaty2.solutions where id =?";
	private static final String LOAD_ALL_SOLUTIONS = "SELECT * FROM warsztaty2.solutions;";
	private static final String LOAD_ALL_SOLUTIONS_FROM_USER = "SELECT * FROM warsztaty2.solutions inner join warsztaty2.users on warsztaty2.solutions.users_id = warsztaty2.users.id WHERE warsztaty2.users.id=? ;";
	private static final String LOADALL_BY_EXERCISE_ID = "SELECT * FROM warsztaty2.solutions where warsztaty2.solutions.exercise_id =? order by warsztaty2.solutions.updated asc;";
	private static final String UPDATE_SOLUTION = "UPDATE warsztaty2.solutions SET updated = ?, description =?  WHERE id =? ;";
	private static final String DELETE_SOLUTION = "DELETE FROM `warsztaty2`.`solutions` WHERE warsztaty2.solutions.id =?;";

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
