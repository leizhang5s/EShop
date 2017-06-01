package cn.leizhang.product;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.leizhang.product.Product;



public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHotList() {
		
		//List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product where is_hot=?", new Object[]{1}, 0, 10));
	    List<Product> list=this.getHibernateTemplate().find("from Product where is_hot=?",1);
		return list;
	}

	public List<Product> findNewList() {
		List<Product> list=this.getHibernateTemplate().find("from Product where is_hot=?",1);
		return list;
	}

	public List<Product> findByCid(Integer cid) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().find(hql,cid);
		return list;
	}

	public Product findProductByPid(Integer pid) {
	 return this.getHibernateTemplate().get(Product.class, pid);
	}

	public List<Product> findByCsid(Integer csid) {
		String hql="select p from Product p join p.categorySecond cs join cs.category c where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().find(hql,csid);
		
		
		return list;
	}

}
