package com.simplecoding.simpledms.service.cart;

import com.simplecoding.simpledms.mapper.cart.CartMapper;
import com.simplecoding.simpledms.vo.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    // 장바구니에 추가
    public void addToCart(Cart cart) {
        cartMapper.addToCart(cart);
    }

    // 이메일로 상세조회해서 장바구니 출력: 배열
    public List<Cart> getItemsFromEmail(String userEmail) {
        return cartMapper.getItemsFromEmail(userEmail);
    }

    // 개별 삭제
    public void deleteFromCartId(int cartId) {
        cartMapper.deleteFromCartId(cartId);
    }

    // 전체 삭제
    public void deleteFromEmail(String userEmail) {
        cartMapper.deleteFromEmail(userEmail);
    }

    // 장바구니에 담긴 개수 세기
    public int countCartItems(String userEmail) {
        return cartMapper.countCartItems(userEmail);
    }


}
