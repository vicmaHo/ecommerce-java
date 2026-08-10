package com.vicma.microservices.product_microservice.exceptions;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(Integer productId, Integer requestedQuantity, Integer availableQuantity) {
        super(String.format("Not enough stock for product with ID %d. Requested: %d, Available: %d",
                productId, requestedQuantity, availableQuantity));
    }
}
