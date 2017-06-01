package cn.leizhang.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.leizhang.cart.CartItem;

public class Cart {
		private Map<Integer,CartItem> map=new HashMap<Integer,CartItem>();
		public Map<Integer, CartItem> getMap() {
			return map;
		}
		public Collection<CartItem> getCartItems(){
			return map.values();
		}
		private Double total = 0d;
		public Double getTotal() {
			return total;
		}
		
		public void addCart(Integer pid,CartItem cartItem)
		{
			if(map.containsKey(pid))
			{
				map.get(pid).setCount(map.get(pid).getCount()+cartItem.getCount());
				
				
			}
			else
			{
				map.put(pid, cartItem);
				
				
				
			}
			total+=cartItem.getSubtotal();
			
		}
		public void clearCart()
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().removeAttribute("cart");
			
			
		}
		public void removeCart(Integer pid) {
			CartItem cartItem=map.remove(pid);
			total-=cartItem.getSubtotal();
			
		}
}
