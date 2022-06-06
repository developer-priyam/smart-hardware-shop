package com.smartharwareshop.shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartharwareshop.shoppingcart.controller.CartController;
import com.smartharwareshop.shoppingcart.model.CartItem;
import com.smartharwareshop.shoppingcart.service.Cart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Test - For testing integration between Inventory Controller and HTTP Layer 7
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
public class CartControllerIntegrationTest {

  @MockBean
  private Cart cart;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getShoppingCartEndpointTest() throws Exception {
    UUID userId = UUID.randomUUID();
    mockMvc.perform(get("/cart/{userId}", userId)
           .contentType("application/json"))
           .andExpect(status().isOk());
  }

  @Test
  public void addProductToTheCartEndpointTest() throws Exception {
    UUID userId = UUID.randomUUID();
    CartItem item = new CartItem(UUID.randomUUID(), 5);
    mockMvc.perform(post("/cart/{userId}", userId)
           .contentType("application/json")
           .content(objectMapper.writeValueAsString(item)))
           .andExpect(status().isOk());
  }

  @Test
  public void updateProductQuantityEndpointTest() throws Exception {
    UUID userId = UUID.randomUUID();
    CartItem item = new CartItem(UUID.randomUUID(), 5);
    mockMvc.perform(put("/cart/{userId}", userId)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(item)))
      .andExpect(status().isOk());
  }

  @Test
  public void removeProductFromUserCartEndpointTest() throws Exception {
    UUID userId = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    mockMvc.perform(delete("/cart/{userId}/{productId}", userId, productId)
           .contentType("application/json"))
           .andExpect(status().isOk());
  }

  @Test
  public void clearCartEndpointTest() throws Exception {
    UUID userId = UUID.randomUUID();
    mockMvc.perform(put("/cart/clear/{userId}", userId)
        .contentType("application/json"))
      .andExpect(status().isOk());
  }
}
