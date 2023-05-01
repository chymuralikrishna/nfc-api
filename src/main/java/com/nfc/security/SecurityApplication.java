package com.nfc.security;

import com.nfc.security.auth.AuthenticationService;
import com.nfc.security.auth.RegisterRequest;
import com.nfc.security.user.Permission;
import com.nfc.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
	
		Permission read=new Permission();read.setName("READ");
		Permission create=new Permission();read.setName("CREATE");
		Permission update=new Permission();read.setName("UPDATE");
		Permission delete=new Permission();read.setName("DELETE");

		

		Set<Permission> adminPermission=new HashSet<>();
		adminPermission.add(read);adminPermission.add(create);adminPermission.add(update);adminPermission.add(delete);
		
		Role adminRole=new Role();
		adminRole.setPermissions(adminPermission);
		adminRole.setRoleName("ADMIN");
		adminRole.setRoleDesc("Administrator");
		Set<Role> adminRoles =new HashSet<Role>();
		adminRoles.add(adminRole);
		
			
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.roles(adminRoles)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

//			var manager = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("manager@mail.com")
//					.password("password")
//					.roles(managerRoles)
//					.build();
//			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
