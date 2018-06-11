package school.domain;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	private long id;
	private String userName;
	private String email;
	private String password;
	private long userGroupId;

	public User(String userName, String email, String password, long userGroupId) {
		this.userName = userName;
		this.email = email;
		setPassword(password);
		this.userGroupId = userGroupId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserGroupId(long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public long getUserGroupId() {
		return userGroupId;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", userGroupId=" + userGroupId + "]";
	}

}
