package com.smartharwareshop.shoppingcart.model;

import java.util.UUID;

public record CartItem(UUID productId, int quantity) {}
