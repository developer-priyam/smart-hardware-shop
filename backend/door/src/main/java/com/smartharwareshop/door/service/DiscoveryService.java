package com.smartharwareshop.door.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryService implements Discovery {

  private final DiscoveryClient discoveryClient;

  public DiscoveryService(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  public List<ServiceInstance> getApplicationInstance(String serviceName) {
    return discoveryClient.getInstances(serviceName);
  }
}
