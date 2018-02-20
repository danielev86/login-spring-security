package it.dverrienti.demo.springbootsecurity.front.bean;

public class RoleBean extends GenericBean {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String roleName;

	public Long getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
