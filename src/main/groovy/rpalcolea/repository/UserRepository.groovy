package rpalcolea.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import rpalcolea.domain.User

@Repository
interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String name)
}