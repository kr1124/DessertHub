package com.desserthub.user;

public class UserDAO {
    // UserServiceImpl에서 구현한 함수에서 호출할, DB와 연결하여 사용할 함수 작성 필요
    // ex)
    public UserDto get_user_data() {
        UserDto ud = new UserDto();

        //DB에서 필요한 데이터를 긇어와 ud에 저장하는 코드

        return ud;
    }
}
