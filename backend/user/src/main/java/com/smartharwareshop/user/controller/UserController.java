package com.smartharwareshop.user.controller;

import com.smartharwareshop.user.model.User;
import com.smartharwareshop.user.service.UserStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-service")
public class UserController {

  private final UserStore userStore;

  public UserController(UserStore userStore) {
    this.userStore = userStore;
  }

  @GetMapping("/user")
  public User getDefaultUser() {
    return userStore.getDefaultUser();
  }
}
