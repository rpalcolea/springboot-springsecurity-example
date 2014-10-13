package rpalcolea.domain

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.index.Indexed

@EqualsAndHashCode
@Document
class User {
    @Id
    String id

    @Indexed(unique=true)
    String username
    String password
    Boolean enabled

    @DBRef
    List<Role> roles = new ArrayList<Role>()
}