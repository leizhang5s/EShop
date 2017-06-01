package cn.leizhang.cart;

import cn.leizhang.product.Product;

public class CartItem {
		private Product product;
		//数量
		private Integer count;
		//该商品的总价；
		private Double subtotal=0d;
		public Double getSubtotal() {
			return count*product.getShop_price();
		}
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
		
		
		
		
}
