package com.smartharwareshop.shoppingcart;

import com.smartharwareshop.shoppingcart.controller.CartController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShoppingcartApplicationTests {

  @Autowired
  private CartController controller;

	@Test
	void contextLoads() {
    assertThat(controller).isNotNull();
	}

}
