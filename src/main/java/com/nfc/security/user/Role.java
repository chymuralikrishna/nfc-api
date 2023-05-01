package com.nfc.security.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	private String roleName;
	private String roleDesc;
	
	  @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_permission",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();
    
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();


    
     public List<SimpleGrantedAuthority> getAuthorities() {
      var authorities = getPermissions()
              .stream()
              .map(permission -> new SimpleGrantedAuthority(permission.getName()))
              .collect(Collectors.toList());
      authorities.add(new SimpleGrantedAuthority("ROLE_" + this.roleName));
      return authorities;
    }



}
