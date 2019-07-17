package techcourse.myblog;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;

@Component
public class MyblogRunner implements ApplicationRunner {
    private UserRepository userRepository;

    public MyblogRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new User("CU", "root@gmail.com", "PassWord!1"));
    }
}
