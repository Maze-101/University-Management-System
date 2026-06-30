package main.java.com.university.model;

import java.util.Objects;

public abstract class User {
    private final String id;
    private final String name;
    private String email;
    private String password;
    private final Role role;

    public User(String id, String name, String email, String password, Role role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean validateCredentials(String password, String email){
        return Objects.equals(this.email, email) && Objects.equals(this.password, password);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }
}