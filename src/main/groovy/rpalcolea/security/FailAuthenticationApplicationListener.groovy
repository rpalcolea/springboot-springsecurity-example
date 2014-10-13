package rpalcolea.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent
import org.springframework.stereotype.Component
import rpalcolea.service.LoginAttemptService
import org.springframework.context.ApplicationListener

@Component
class FailAuthenticationApplicationListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired private LoginAttemptService loginAttemptService

    @Override
    void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        loginAttemptService.failLogin(event.authentication.principal)
    }

}
