package com.desserthub.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userCode) {
        return userRepository.findById(userCode);
    }

    public User createUser(User board) {
        return userRepository.save(board);
    }

    public User updateUser(Long userCode, User userDetails) {
        User user = userRepository.findById(userCode).orElseThrow(null);

        user.setUserPw(userDetails.getUserPw());
        user.setUserEm(userDetails.getUserEm());
        user.setUserNn(userDetails.getUserNn());

        return userRepository.save(user);
    }

    public void deleteUser(Long userCode) {
        userRepository.deleteById(userCode);
    }


    public User updateUserProfileImage(Long userCode, User userDetails) {
        User user = userRepository.findById(userCode).orElseThrow(null);
        user.setUserPi(userDetails.getUserPi());
        return userRepository.save(user);
    }

    public boolean login_check(User login_user) {
        boolean result = false;
        User user = userRepository.findByUserId(login_user.getUserId()).orElseThrow(null);

        if(user != null) {
            if(login_user.getUserId().equals(user.getUserId())) {
                if (login_user.getUserPw().equals(user.getUserPw())) {
                    result = true;
                }
            }
        }

        return result;
    }


    public boolean register_check(User user) {
        boolean result = false;

        User target_user = userRepository.findByUserId(user.getUserId()).orElseThrow(null);

        if(target_user == null) {
            target_user = new User();
            target_user.setUserId(user.getUserId());
            target_user.setUserPw(user.getUserPw());
            target_user.setUserEm(user.getUserEm());
            target_user.setUserNn(user.getUserNn());

            userRepository.save(target_user);

            result = true;
        }

        return result;
    }
}
