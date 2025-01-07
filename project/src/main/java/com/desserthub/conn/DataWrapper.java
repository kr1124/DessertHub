package com.desserthub.conn;

import java.sql.*;

//class for return int + ResultSet
public class DataWrapper {
    //int number means success(1) or fail(0) and another(-1, or etc.)
    private int number;
    //rs means returned result of DB
    private ResultSet rs;

    // 생성자
    public DataWrapper() {
        this.number = 0;
    }

    public DataWrapper(int number) {
        this.number = number;
    }
    public DataWrapper(int number, ResultSet rs) {
        this.number = number;
        this.rs = rs;
    }

    // Getter와 Setter
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void close() {
        if(this.rs != null) {try {this.rs.close();} catch(SQLException e) {}}
    }
}
