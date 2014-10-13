package rpalcolea.config

import com.mongodb.Mongo
import com.mongodb.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration

@Configuration
class MongoConfig extends AbstractMongoConfiguration {

    @Override
    String getDatabaseName() {
        "springboot-security-notifications"
    }

    @Override
    Mongo mongo() throws Exception {
        new MongoClient()
    }
}