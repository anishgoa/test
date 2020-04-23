package com.goaudits.business.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goaudits.business.entity.User;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String guid;
	
	private String uid;

	private String name;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private boolean active;
    private boolean portalAccess;
    private boolean analyticsAccess;
    private int auditsCount;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(String guid, String uid, String name, String username, String password,boolean active) {
		this.guid = guid;
		this.uid = uid;
		this.name = name; 
		this.username = username;
		this.active=active;
		this.password = password;
	}

	public static UserPrinciple build(User user) {
		// List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
		// new SimpleGrantedAuthority(role.getName().name())
		// ).collect(Collectors.toList());

		return new UserPrinciple(user.getGuid(),user.getUid(), user.getFirst_name(), user.getUser_name(), user.getUsr_pwd(), user.isActive());
	}

	
	public String getGuid() {
		return guid;
	}

	public boolean isActive() {
		return active;
	}

	public String getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrinciple user = (UserPrinciple) o;
		return Objects.equals(uid, user.uid);
	}

	public boolean isPortalAccess() {
		return portalAccess;
	}

	public void setPortalAccess(boolean portalAccess) {
		this.portalAccess = portalAccess;
	}

	public boolean isAnalyticsAccess() {
		return analyticsAccess;
	}

	public void setAnalyticsAccess(boolean analyticsAccess) {
		this.analyticsAccess = analyticsAccess;
	}

	public int getAuditsCount() {
		return auditsCount;
	}

	public void setAuditsCount(int auditsCount) {
		this.auditsCount = auditsCount;
	}
	
	
}