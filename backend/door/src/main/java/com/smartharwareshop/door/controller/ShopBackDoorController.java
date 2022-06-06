package com.smartharwareshop.door.controller;

import com.smartharwareshop.door.service.Discovery;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopBackDoorController {

  private final Discovery discovery;

  public ShopBackDoorController(Discovery discovery) {
    this.discovery = discovery;
  }

  @RequestMapping(value = "/door/{serviceName}/{uri}")
  public void routeToBackendService(@PathVariable String serviceName, @PathVariable String uri) {
    ServiceInstance serviceInstance = discovery.getApplicationInstance(serviceName).get(0);
//    String serviceUrl =
  }

}
