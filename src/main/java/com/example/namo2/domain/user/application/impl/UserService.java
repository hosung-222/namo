package com.example.namo2.domain.user.application.impl;

import static com.example.namo2.global.common.response.BaseResponseStatus.*;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.namo2.domain.category.domain.Category;
import com.example.namo2.domain.user.domain.User;
import com.example.namo2.domain.user.dao.repository.UserRepository;
import com.example.namo2.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER_FAILURE));
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public void updateRefreshToken(Long userId, String refreshToken
    ) throws BaseException {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FOUND_USER_FAILURE));
        user.updateRefreshToken(refreshToken);
    }
}
