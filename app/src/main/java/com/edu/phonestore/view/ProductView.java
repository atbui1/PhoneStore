package com.edu.phonestore.view;

import com.edu.phonestore.model.Product;

import java.util.List;

public interface ProductView {
    void productSuccess(List<Product> products);
    void productFailed(String msg);
}
