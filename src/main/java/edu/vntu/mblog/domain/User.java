package edu.vntu.mblog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = -961884833081701094L;

	public enum Permission {
		USER, MODERATE_POSTS, MANAGE_USERS;
	}
	
	private long id;
	private Date creationDate;
	private String login;
	private String email;
	private String passHash;
	private Date blockDate;
    private String avatar;
	private EnumSet<Permission> permissions;
	
	public User(String login, String email, String passHash) {
		this(0L, null, login, email, passHash, null, null);
	}
	
	public User(long id, Date creationDate, String login, String email,
			String passHash, Date blockDate, String avatar) {
		this.id = id;
		this.creationDate = creationDate;
		this.login = login;
		this.email = email;
		this.passHash = passHash;
		this.blockDate = blockDate;
        this.avatar = avatar;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassHash() {
		return passHash;
	}
	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}
	
	public Date getBlockDate() {
		return blockDate;
	}
	
	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}

	public EnumSet<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(EnumSet<Permission> permissions) {
		this.permissions = permissions;
	}

    public List<String> getPermissionsNames() {
        List<String> list = new ArrayList<>(permissions.size());

        for(Permission p: permissions) {
            list.add(p.name());
        }

        return list;
    }

    public boolean isHasAvatar() {
        return avatar != null;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
