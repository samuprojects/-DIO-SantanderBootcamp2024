package me.dio.service;

import me.dio.domain.model.User;

@SuppressWarnings("ALL")
public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
