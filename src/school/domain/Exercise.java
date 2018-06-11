package school.domain;

public class Exercise {
	private long id;
	private String title;
	private String description;

	public Exercise(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Exercise [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

}
