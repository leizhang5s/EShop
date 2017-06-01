package cn.leizhang.order;

public class OrderService {
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Integer save(Order order) {
		
		return orderDao.save(order);
	}

	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}

	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}

}
