package cn.leizhang.order;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import cn.leizhang.utils.PaymentUtil;
import cn.leizhang.cart.Cart;
import cn.leizhang.cart.CartItem;
import cn.leizhang.order.Order;
import cn.leizhang.user.User;

import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport{
		private Order order;
		private String pd_FrpId;

		public Order getOrder() {
			return order;
		}
		public void setPd_FrpId(String pd_FrpId) {
			this.pd_FrpId = pd_FrpId;
		}
		//注入orderService 
		private OrderService orderService;
		public void setOrderService(OrderService orderService) {
			this.orderService = orderService;
		}

		public String saveOrder()
		{
			order = new Order();
			order.setOrdertime(new Date());
			order.setState(1);
			HttpServletRequest request=ServletActionContext.getRequest();
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			order.setTotal(cart.getTotal());
			// 订单所属的用户:
			User existUser = (User) request.getSession().getAttribute("existUser");
			if (existUser == null) {
				this.addActionMessage("您还没有登录!请先去登录!");
				return "msg";
			}
			order.setUser(existUser);
			/******************** 封装订单项数据 *************/
			// 订单项数据从 购物项的数据获得.
			for (CartItem cartItem : cart.getCartItems()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setOrder(order);

				order.getOrderItems().add(orderItem);
			}
			// 清空购物车.
			cart.clearCart();
				
			// 保存订单:
			Integer oid = orderService.save(order);
			order.setOid(oid);
			// order = orderService.findByOid(oid);
			// System.out.println(order);
			return "saveOrderSuccess";
		}
		
		
		public String payOrder() throws IOException {
			// 修改订单:
			// 查询这个id的订单:
			Order currOrder = orderService.findByOid(order.getOid());
			currOrder.setAddr(order.getAddr());
			currOrder.setName(order.getName());
			currOrder.setPhone(order.getPhone());

			orderService.update(currOrder);
			// 付款:
			// 定义付款的参数:
			String p0_Cmd = "Buy";
			String p1_MerId = "10001126856";
			String p2_Order = order.getOid().toString();
			String p3_Amt = "0.01";
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			String p8_Url = "http://192.168.40.110:8090/shop/order_callBack.action";
			String p9_SAF = "";
			String pa_MP = "";
			String pr_NeedResponse = "1";
			String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
					p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
					pd_FrpId, pr_NeedResponse, keyValue);

			StringBuffer sb = new StringBuffer(
					"https://www.yeepay.com/app-merchant-proxy/node?");
			sb.append("p0_Cmd=").append(p0_Cmd).append("&");
			sb.append("p1_MerId=").append(p1_MerId).append("&");
			sb.append("p2_Order=").append(p2_Order).append("&");
			sb.append("p3_Amt=").append(p3_Amt).append("&");
			sb.append("p4_Cur=").append(p4_Cur).append("&");
			sb.append("p5_Pid=").append(p5_Pid).append("&");
			sb.append("p6_Pcat=").append(p6_Pcat).append("&");
			sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			sb.append("p8_Url=").append(p8_Url).append("&");
			sb.append("p9_SAF=").append(p9_SAF).append("&");
			sb.append("pa_MP=").append(pa_MP).append("&");
			sb.append("pd_FrpId=").append(pd_FrpId).append("&");
			sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sb.append("hmac=").append(hmac);
			System.out.println(sb.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			response.sendRedirect(sb.toString());
			return NONE;
		}
		
		
		
}
