/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.fall23.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hd
 */
public class Cart {

    private Map<String, Integer> items;
    private List<Product> productsInCart;

    public Cart() {
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public boolean addItemToCart(Product product) throws Exception {
        boolean check = false;

        try {
            if (this.items == null) {
                this.items = new HashMap<>();
                this.productsInCart = new ArrayList();
            }
            if (this.items.containsKey(product.getProductID())) {
                int currentQuantity = this.items.get(product.getProductID());
                this.items.put(product.getProductID(), currentQuantity + 1);
            } else {
                this.items.put(product.getProductID(), 1);
                this.productsInCart.add(product);
            }
            check = true;
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }

    public boolean updateQuantity(String productID, int quantity) {
        boolean check = false;
        try {
            if (this.items != null) {
                if (this.items.containsKey(productID)) {
                    this.items.replace(productID, quantity);
                    check = true;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
}
