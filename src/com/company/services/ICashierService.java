package com.company.services;

import com.company.distribution.CartItem;

import java.time.Instant;
import java.util.List;

public interface ICashierService {
    void printReceipt(List<CartItem> items, Instant dateOfPurchase);
}
