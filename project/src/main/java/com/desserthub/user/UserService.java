package com.desserthub.user;

public interface UserService {
    public UserDto get_user_data();
    public boolean login_check(String id, String pass);
    public boolean register_check(UserDto userDto);
}
