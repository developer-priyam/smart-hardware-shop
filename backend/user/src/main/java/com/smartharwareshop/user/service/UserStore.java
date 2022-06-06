package com.smartharwareshop.user.service;

import com.smartharwareshop.user.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserStore {

  User getDefaultUser();
}
