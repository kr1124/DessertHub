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

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public boolean updateUser(Long id, User userDetails) {
        // User user = userRepository.findById(userCode).orElseThrow(null);

        // user.setUserPw(userDetails.getUserPw());
        // user.setUserEm(userDetails.getUserEm());
        // user.setUserNn(userDetails.getUserNn());

        // return userRepository.save(user);

        boolean result = false;
        User target_user = null;
        
        try {
            target_user = userRepository.findById(id).orElseThrow(null);
        } catch (Exception e) {
            // null
        }

        if(target_user != null) {
            if (userDetails.getUserPw() != null && userDetails.getUserPw() != "") {
                target_user.setUserPw(userDetails.getUserPw());
            }
            if (userDetails.getUserEm() != null && userDetails.getUserEm() != "") {
                target_user.setUserEm(userDetails.getUserEm());
            }
            if (userDetails.getUserNn() != null && userDetails.getUserNn() != "") {
                target_user.setUserNn(userDetails.getUserNn());
            }

            userRepository.save(target_user);

            result = true;
        }

        return result;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean deleteUser(String userId, String userPw) {
        User user = null;
        boolean result = false;
        try {
            user = userRepository.findByUserId(userId).orElseThrow(null);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (user != null) {
            if(userId.equals(user.getUserId()) && userPw.equals(user.getUserPw())) {
                deleteUser(user.getId());
                result = true;
            }
        }

        return result;
    }

    public boolean updateUserProfileImage(Long id, String string) {
        boolean result = false;
        User target_user = null;
        
        try {
            target_user = userRepository.findById(id).orElseThrow(null);
        } catch (Exception e) {
            // null
        }

        if(target_user != null) {
            target_user.setUserPi(string);
            userRepository.save(target_user);
            result = true;
        }

        return result;
    }

    public boolean login_check(User login_user) {
        boolean result = false;

        User user = null;

        try {
            user = userRepository.findByUserId(login_user.getUserId()).orElseThrow(null);
        } catch (Exception e) {
            //e.printStackTrace(); //NullPointerException
        }

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
        User target_user = null;
        
        try {
            target_user = userRepository.findByUserId(user.getUserId()).orElseThrow(null);
        } catch (Exception e) {
            // null
        }

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
