package rpalcolea.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import rpalcolea.domain.Role
import rpalcolea.domain.User
import rpalcolea.repository.RoleRepository
import rpalcolea.repository.UserRepository

@Service
class UserService {
    @Autowired private UserRepository userRepository
    @Autowired private RoleRepository roleRepository
    @Autowired private PasswordEncoder encoder

    public Role getRole(String role) {
        return roleRepository.findOne(role);
    }

    public User create(String username, String password) {
        if (userRepository.findByUsername(username)) {
            return null
        }

        User user = new User()
        user.username = username
        user.password = encoder.encode(password)
        user.enabled = true
        userRepository.save(user)
        return user
    }

    public User addRoleToUser(User user, String desiredRole) {
        Role role = getRole(desiredRole)
        if(!role)
            throw new Exception("Couldn´ find role ${desiredRole}")
        user.roles.add(role)
        save(user)
    }

    public void save(User user) {
        userRepository.save(user)
    }

    public void delete(User user) {
        userRepository.delete(user)
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
    }
}
