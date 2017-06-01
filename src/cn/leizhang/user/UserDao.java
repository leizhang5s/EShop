package cn.leizhang.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.leizhang.product.Product;

public class UserDao extends HibernateDaoSupport{

	public User existUser(User user) {
		String hql="from User where username=? and password=?";
		List<User> list =this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
		
		if(list.size()!=0)
		{
			return list.get(0);
		}
		return null;
	}
	
	

	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}



	public User findByUserName(String username) {
		String hql="from User where username=?";
		List<User> list=this.getHibernateTemplate().find(hql,username);
		if(list.size()!=0)
		{
			return list.get(0);
		}
		return null;
	}
		
}
