package com.smartharwareshop.productInventory.model;

public record ProductDescription(String name,
                                 int categoryId,
                                 double price,
                                 int availableCount,
                                 boolean isHighlighted) {
}
