package techcourse.myblog.support.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import techcourse.myblog.domain.User;
import techcourse.myblog.support.config.UserSessionContext;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "blogAuditorAware")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<User> blogAuditorAware() {
        return () -> Optional.of(UserSessionContext.getUser());
    }
}
