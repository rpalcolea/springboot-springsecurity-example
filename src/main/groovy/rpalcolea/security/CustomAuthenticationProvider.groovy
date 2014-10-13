package rpalcolea.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import rpalcolea.domain.User
import rpalcolea.service.UserService

@Component
class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider  {
    private final Logger logger = LoggerFactory.getLogger(getClass())

    @Autowired UserService userService
    @Autowired private PasswordEncoder encoder

    @Override
    public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        String password = (String) authentication.credentials

        if (!password) {
            throw new BadCredentialsException("Password no provided, please try again")
        }

        User user = userService.getByUsername(username)

        if (!user) {
            throw new UsernameNotFoundException("Username doesn't exists")
        }

        if (!encoder.matches(password, user.password)) {
            throw new BadCredentialsException("Invalid password, please try again")
        }

        if (!user.enabled ) {
            throw new BadCredentialsException("User disabled")
        }

        final List<GrantedAuthority> authorities = (user.roles) ? AuthorityUtils.commaSeparatedStringToAuthorityList(user.roles.id.join(',')) : AuthorityUtils.NO_AUTHORITIES

        return new org.springframework.security.core.userdetails.User(username, password, user.enabled, true, true, true, authorities)
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException { }

}
