package cn.com.smer.dao.dto;

public class Users {
    private Long userId;

    private String userName;

    private String userPassword;

    private Long managetype;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Long getManagetype() { return managetype; }

    public void setManagetype(Long managetype) { this.managetype = managetype; }

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", managetype=" + managetype + "]";
	}
    
}