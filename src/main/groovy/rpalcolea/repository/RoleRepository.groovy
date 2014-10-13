package rpalcolea.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import rpalcolea.domain.Role

@Repository
interface RoleRepository extends MongoRepository<Role, String> {

}