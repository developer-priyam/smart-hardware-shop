package com.smartharwareshop.productInventory.util;

import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.service.InMemoryProductInventory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyDataLoader implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
    InMemoryProductInventory inMemoryProductInventory = InMemoryProductInventory.getInstance();
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Screwdriver", 0, 100.0, 5, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Light Hammer", 6, 120.0, 3, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Heavy Hammer", 6, 100.0, 4, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Light Drill Machine", 4, 555.0, 7, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Heavy Drill Machine", 4, 1200.0, 7, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Nails", 1, 5.0, 20, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Wire", 1, 6.0, 35, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("small bolts", 1, 2.0, 2, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Big Bolts", 1, 4.0, 7, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("locks", 3, 55.0, 6, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("small clamps", 2, 15.0, 9, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Nose Pliers", 0, 80.0, 10, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Plucker", 0, 47.4, 11, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Rivet", 0, 35.0, 45, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Clip", 0, 2.0, 22, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Mallet", 6, 300.0, 15, true));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Spanner", 5, 180.0, 12, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Ring Spammer", 5, 220.0, 24, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Monkey Wrench", 5, 200.0, 44, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Philips Screwdriver", 0, 110.0, 12, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Bradawl", 7, 45.0, 32, false));
    inMemoryProductInventory.addProductIntoInventory(new ProductDescription("Chisel", 8, 50.0, 17, false));
  }
}
