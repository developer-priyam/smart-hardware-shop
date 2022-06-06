package com.smartharwareshop.productInventory;

import com.smartharwareshop.productInventory.controller.InventoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductInventoryApplicationTests {

  @Autowired
  private InventoryController controller;

	@Test
	void contextLoads() {
    assertThat(controller).isNotNull();
	}

}
