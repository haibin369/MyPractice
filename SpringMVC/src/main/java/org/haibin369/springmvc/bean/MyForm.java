package org.haibin369.springmvc.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MyForm {
    @Pattern(regexp = "[a-zA-Z0-9]{3,6}",
            message = "Username must be between 3 and 6 characters long without any special characters!")
    private String username;
    @Size(min = 3, max = 6, message = "Password must be between 3 and 6 characters long!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
