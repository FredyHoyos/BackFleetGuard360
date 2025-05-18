package com.fleetguard.api.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUp {
    private String username;
    private String password;
    private String name;

    public SignUp(){}

    public SignUp(String username, String password, String name) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
