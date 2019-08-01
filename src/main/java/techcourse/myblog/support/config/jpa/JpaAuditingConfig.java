package techcourse.myblog.support.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import techcourse.myblog.domain.User;
import techcourse.myblog.support.auth.UserAuthentication;
import techcourse.myblog.support.auth.UserSessionContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "blogAuditorAware")
public class JpaAuditingConfig {
    private UserAuthentication userAuthentication;

    public JpaAuditingConfig(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Bean
    public AuditorAware<User> blogAuditorAware() {
        return () -> Optional.of(UserSessionContextHolder.getUserAuthentication(userAuthentication));
    }
}
