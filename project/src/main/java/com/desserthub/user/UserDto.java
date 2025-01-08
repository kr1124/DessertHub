package com.desserthub.user;

public class UserDto {
    private String user_id;
    private String user_pw;
    private String user_em; //email
    private String user_nn; //nickname
    private String user_pi; //profile image - base64

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_pw() {
        return user_pw;
    }
    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }
    public String getUser_em() {
        return user_em;
    }
    public void setUser_em(String user_em) {
        this.user_em = user_em;
    }
    public String getUser_nn() {
        return user_nn;
    }
    public void setUser_nn(String user_nn) {
        this.user_nn = user_nn;
    }
    public String getUser_pi() {
        return user_pi;
    }
    public void setUser_pi(String user_pi) {
        this.user_pi = user_pi;
    }
}
