package rpalcolea.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import rpalcolea.domain.Role
import rpalcolea.repository.RoleRepository

@Controller
class RoleController {

    @Autowired private RoleRepository roleRepository

    @RequestMapping("/role/list")
    @ResponseBody
    ResponseEntity<Role> list() {
        new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK)
    }

    @RequestMapping("/role/add")
    @ResponseBody
    ResponseEntity add(@RequestParam String id) {
        Role role = roleRepository.findOne(id)
        if(role) {
            return new ResponseEntity<String>(HttpStatus.CONFLICT)
        }
        role = roleRepository.save(new Role(id))
        new ResponseEntity<Role>(role, HttpStatus.OK)
    }

    @RequestMapping("/role/delete")
    @ResponseBody
    ResponseEntity delete(@RequestParam String id) {
        Role role = roleRepository.findOne(id)
        if(!role) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND)
        }
        roleRepository.delete(role)
        new ResponseEntity<String>("Role ${id} deleted", HttpStatus.OK)
    }
}
