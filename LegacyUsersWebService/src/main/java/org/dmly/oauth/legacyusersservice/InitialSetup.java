package org.dmly.oauth.legacyusersservice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import org.dmly.oauth.legacyusersservice.data.UserEntity;
import org.dmly.oauth.legacyusersservice.data.UsersRepository;

@Component
public class InitialSetup {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserEntity user = new UserEntity(
                1L,
                "hcuichyYy8jklj7678f5",
                "User_123_f",
                "User_123_l",
                "test2@test.com",
                bCryptPasswordEncoder.encode("password"),
                "",
                false);

        usersRepository.save(user);
    }
}
