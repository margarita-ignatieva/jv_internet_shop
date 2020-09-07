package com.internet.shop.dao;

import com.internet.shop.model.User;
import java.util.List;

public interface UserDao {
    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}
