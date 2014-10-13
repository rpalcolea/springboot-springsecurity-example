package rpalcolea.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import rpalcolea.domain.User
import rpalcolea.repository.UserRepository
import rpalcolea.service.UserService

@Controller
class UserController {

    @Autowired private UserRepository userRepository
    @Autowired private UserService userService

    @RequestMapping("/user/list")
    @ResponseBody
    ResponseEntity<User> list() {
        new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK)
    }

    @RequestMapping("/user/add")
    @ResponseBody
    ResponseEntity add(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        User user = userRepository.findByUsername(username)
        if(user) {
            return new ResponseEntity<String>(HttpStatus.CONFLICT)
        }

        user = userService.create(username, password)
        userService.addRoleToUser(user, role)
        new ResponseEntity<User>(user, HttpStatus.OK)
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    ResponseEntity delete(@RequestParam String username) {
        User user = userRepository.findByUsername(username)
        if(!user) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND)
        }

        userRepository.delete(use())
        new ResponseEntity<String>("User ${username} deleted", HttpStatus.OK)
    }
}
