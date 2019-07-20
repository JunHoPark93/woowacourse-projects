package techcourse.myblog.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import techcourse.myblog.support.util.BCryptImpl;
import techcourse.myblog.support.util.EncryptHelper;
import techcourse.myblog.web.interceptor.AuthInterceptor;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer interceptorConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AuthInterceptor())
                        .addPathPatterns("/users")
                        .addPathPatterns("/mypage-edit")
                        .addPathPatterns("/mypage")
                        .addPathPatterns("/mypage/*");
            }
        };
    }

    @Bean
    public EncryptHelper encryptConfigure() {
        return new BCryptImpl();
    }
}
