package com.smartharwareshop.user.service;

import com.smartharwareshop.user.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserStore {

  @Override
  public User getDefaultUser() {
    return new User(UUID.fromString("6affa395-91a9-465a-be13-6d7d023087d"), "Dummy User");
  }
}
