package com.smartharwareshop.productInventory.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.model.ProductId;
import com.smartharwareshop.productInventory.model.SearchText;
import com.smartharwareshop.productInventory.service.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Test - For testing integration between Inventory Controller and HTTP Layer 7
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerIntegrationTest {

  @MockBean
  private Inventory inventory;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getProductsEndpointTest() throws Exception {
    mockMvc.perform(get("/products")
        .contentType("application/json"))
      .andExpect(status().isOk());
  }

  @Test
  public void getHighlightedProductsEndpointTest() throws Exception {
    mockMvc.perform(get("/productHighlight")
        .contentType("application/json"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByIdEndpointTest() throws Exception {
    List<ProductId> ids = List.of(new ProductId(UUID.randomUUID()), new ProductId(UUID.randomUUID()));
    mockMvc.perform(post("/products", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(ids)))
        .andExpect(status().isOk());
  }

  @Test
  public void searchProductsdEndpointTest() throws Exception {
    SearchText text = new SearchText("test");
    List<ProductId> ids = List.of(new ProductId(UUID.randomUUID()), new ProductId(UUID.randomUUID()));
    mockMvc.perform(post("/search", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(text)))
      .andExpect(status().isOk());
  }

  @Test
  public void addProductEndpointTest() throws Exception {
    ProductDescription description = new ProductDescription("test", 1, 10.5, 5, false);
    List<ProductId> ids = List.of(new ProductId(UUID.randomUUID()), new ProductId(UUID.randomUUID()));
    mockMvc.perform(post("/product", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(description)))
      .andExpect(status().isOk());
  }

  @Test
  public void updateProductQuantityEndpointTest() throws Exception {
    ProductDescription description = new ProductDescription("test", 1, 10.5, 5, false);
    Product product = new Product(new ProductId(UUID.randomUUID()), description);
    List<ProductId> ids = List.of(new ProductId(UUID.randomUUID()), new ProductId(UUID.randomUUID()));
    mockMvc.perform(put("/product", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(product)))
      .andExpect(status().isOk());
  }
}
