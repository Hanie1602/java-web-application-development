package duonglnt.cart;

import duonglnt.product.ProductDAO;
import duonglnt.product.ProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

public class CartObject implements Serializable {

    private Map<String, ProductDTO> items;

    public Map<String, ProductDTO> getItems() {
        return items;
    }

//Bo do vao ngan chua do
    public boolean addItemToCart(String id)
            throws SQLException, NamingException {
        boolean result = false;

        // 1. Check valid id
        if (id == null || id.trim().isEmpty()) {
            return result;
        }

        // 2. Check existing items
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        // 3. Check existing item
        ProductDAO dAO = new ProductDAO();
        ProductDTO dTO = this.items.get(id);
        if (dTO != null) {
            // Item exists, increase quantity
            int quantity = dAO.getProductQuantity(id);
            if (quantity > dTO.getQuantity()) {
                dTO.setQuantity(dTO.getQuantity() + 1);
            }
        } else {
            // Item does not exist, create a new item and add it to the cart
            ProductDTO newProductDTO = dAO.getProductDetail(id);
            if (newProductDTO != null) {
                newProductDTO.setQuantity(1); // Set initial quantity to 1
                this.items.put(id, newProductDTO); // Update the map with the new item
            }
        }

        result = true;
        return result;
    }

    //Bo do ra khoi gio
    public boolean removeItemFromCart(String id) {
        boolean result = false;
        //1. check existed items 
        if (this.items != null) {
            //2. check existed item
            if (this.items.containsKey(id)) {
                //3. remove item from cart
                this.items.remove(id);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                result = true;
            }//end item has existed
        }//items have existe

        return result;
    }

    public void clear() {
        this.items.clear();
    }
}

/*
    Khi new 1 Map moi, ko add bat ki cai gi vao map dc
    De lam sao biet 1 Map hay 1 Connection co du lieu: 
*/