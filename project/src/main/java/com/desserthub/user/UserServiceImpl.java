package com.desserthub.user;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private String temp_id = "qwer";
    private String temp_pass = "1234";

    // TODO method stub
    // 예제, DAO와 DB 연결이 완료되면 수정해야함
    // @Autowired
    // UserDAO userDAO = new UserDAO();

    // @Override
    // public UserDto get_user_data() {
    //     UserDto ud = userDAO.get_user_data();
    //     return ud;
    // }

    public UserServiceImpl() {}

    @Override
    public UserDto get_user_data() {
        return null;
    }


    @Override
    public boolean login_check(String id, String pass) {
        boolean result = false;

        if(id.equals(this.temp_id)) {
            if (pass.equals(this.temp_pass)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean register_check(UserDto userDto) {
        boolean result = false;

        //DAO를 호출해 가능한 id인지 검증하는 작업 필요
        //또한, DAO를 호출해 해당 Dto를 DB에 저장해야함

        //검증 성공 시 true 반환, 지금은 무조건 성공한다고 가정
        if(true) {
            result = true;
        }

        return result;
    }
}
