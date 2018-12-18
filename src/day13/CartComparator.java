package day13;

import day13.Cart;

import java.util.Comparator;

public class CartComparator implements Comparator<Cart> {
    public int compare(Cart c1, Cart c2) {
        return c1.compareTo(c2);
    }
}