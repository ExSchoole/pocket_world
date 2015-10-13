package game.java.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user")
public class User extends Model implements UserDetails{


	private static final long serialVersionUID = -837858953612360312L;
	
	@Size(min= 5,max=25)
	@NotNull
	@Column(name="userName", length=25)
	private String userName;
	
	@NotNull
	@Column(name="password", length=32)
	private String password;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="user_id",nullable=false,updatable=false),
	 inverseJoinColumns=@JoinColumn(name="role_id",nullable=false,updatable=false))
	
	private Set<Role> roles= new HashSet<>();
	
	public User(){super();};
	public User(Long id){super(id);}
	public User(String userName,String password ){
		super(); 
		this.userName=userName;
		this.password=password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> result= new ArrayList<>();
		for(Role role:getRoles())
			result.add(new SimpleGrantedAuthority(role.getTitle().name()));
		return result;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	};
	
}
