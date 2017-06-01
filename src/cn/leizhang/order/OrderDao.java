package cn.leizhang.order;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.leizhang.order.Order;

public class OrderDao extends HibernateDaoSupport{

	public Integer save(Order order) {
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		return oid;
		
	}

	public Order findByOid(Integer oid) {
		
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
		
	}
		
}
