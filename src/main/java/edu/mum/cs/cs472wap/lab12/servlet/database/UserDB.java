package edu.mum.cs.cs472wap.lab12.servlet.database;

import edu.mum.cs.cs472wap.lab12.servlet.model.User;

import java.util.HashMap;

public class UserDB {

    private HashMap<String, User> users;

    public HashMap<String, User> getUsers() {
        users = new HashMap<>();
        users.put("admin", new User("admin","password"));

        return users;
    }
}
