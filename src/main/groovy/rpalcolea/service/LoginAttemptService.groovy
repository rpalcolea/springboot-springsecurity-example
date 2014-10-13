package rpalcolea.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import java.util.concurrent.TimeUnit
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate

/**
 * Created by rpalcolea on 10/12/14.
 */
@Service
class LoginAttemptService {

    @Autowired private SimpMessagingTemplate template
    private LoadingCache<String, Integer> attempts
    private int allowedNumberOfAttempts = 3

    @PostConstruct
    void init() {
        attempts = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .build({ 0 } as CacheLoader)
    }

    public void failLogin(String username) {
        int numberOfAttempts = attempts.get(username)
        numberOfAttempts++

        if(numberOfAttempts >= allowedNumberOfAttempts) {
            sendAlert(username, numberOfAttempts)
        }

        attempts.put(username, numberOfAttempts)
    }

    public void loginSuccess(String username) {
        attempts.invalidate(username)
    }

    public void sendAlert(String username, int attempts) {
        String message = "The user ${username} has reached the max number of attempts. Current count: ${attempts}"
        template.convertAndSend("/topic/failedLogins", [message: message])
    }
}
