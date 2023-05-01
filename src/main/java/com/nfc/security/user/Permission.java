package com.nfc.security.user;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission {
   
	public Permission(String permissionName) {
		this.name=permissionName;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long permissionId;
    
    private String name;
    
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();
    
    
}
