package rpalcolea.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import rpalcolea.domain.Role
import rpalcolea.domain.User

import javax.annotation.PostConstruct

@Component
@Profile(value="dev")
class DataInit {

    @Autowired private MongoOperations operations
    @Autowired private UserService userService
    @Autowired private PasswordEncoder encoder

    @PostConstruct
    public void init() {
        operations.dropCollection("user")
        operations.dropCollection("role")

        operations.insert(new Role("ROLE_USER"), "role")
        operations.insert(new Role("ROLE_ADMIN"), "role")

        User admin = userService.create("admin", "password")
        userService.addRoleToUser(admin, "ROLE_ADMIN")

        User user1 = userService.create("user1", "password")
        userService.addRoleToUser(user1, "ROLE_USER")

        User user2 = userService.create("user2", "password")
        userService.addRoleToUser(user2, "ROLE_USER")
    }
}
