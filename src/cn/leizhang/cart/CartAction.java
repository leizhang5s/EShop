package cn.leizhang.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.leizhang.product.Product;
import cn.leizhang.product.ProductService;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
		private ProductService productService;
		//用以接受商品id
	    private Integer pid;
	    //用以接收商品的数量；
	    private Integer count;	
	public void setPid(Integer pid) {
			this.pid = pid;
		}


		public void setCount(Integer count) {
			this.count = count;
		}


	public void setProductService(ProductService productService) {
			this.productService = productService;
		}

	public  Cart getCart(HttpServletRequest request)
	{
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		//判断
		if(cart==null)
		{
			cart=new Cart();
		request.getSession().setAttribute("cart", cart);
			
		}
		
		
		return cart;
	}


	public String addCart()
	{
		Product product=productService.findProductByPid(pid);
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.addCart(pid,cartItem);
		//request.getSession().setAttribute("cart", cart);
		return "addCartSuccess";
	}
	//清空购物车
	public String clearCart()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("cart");
		return "clearCartSuccess";
	}
	public String removeCart()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
	public String myCart()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		return "myCartSuccess";
	}
	
}
