package rpalcolea.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@Document
public class Role {

    @Id
    String id

    public Role(String id) {
        super()
        this.id = id
    }
}