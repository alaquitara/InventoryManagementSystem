package com.company;

/**
 * Copyright (c) 1999 - 2016 Commerce Technologies Inc. All rights reserved.
 * Implements of this interface including access to shared data must  be thread-safe
 */
public interface InventoryManagementSystem {
    /**
     * Deduct 'amountToPick' of the given 'productId' from inventory.
     *
     * @param productId
     * @param amountToPick
     *
     * @return to be implemented
     */
    PickingResult pickProduct(String productId, int amountToPick);
    /**
     * Add 'amountToRestock' of the given productId to inventory.
     *
     * @param productId
     * @param amountToRestock
     *
     * @return to be implemented
     */
     RestockingResult restockProduct(String productId, int amountToRestock);

}
