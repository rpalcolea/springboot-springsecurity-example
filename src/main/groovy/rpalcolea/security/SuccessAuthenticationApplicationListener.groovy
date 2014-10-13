package rpalcolea.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.springframework.stereotype.Component
import rpalcolea.service.LoginAttemptService

@Component
class SuccessAuthenticationApplicationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired private LoginAttemptService loginAttemptService

    @Override
    void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        loginAttemptService.loginSuccess(event.authentication.principal.username)
    }

}
