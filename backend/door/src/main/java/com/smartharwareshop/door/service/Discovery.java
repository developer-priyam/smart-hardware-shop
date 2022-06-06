package com.smartharwareshop.door.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Discovery {
  List<ServiceInstance> getApplicationInstance(String serviceName);
}
