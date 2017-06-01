package cn.leizhang.categorysecond;

import java.util.HashSet;
import java.util.Set;

import cn.leizhang.category.Category;
import cn.leizhang.product.Product;



/**
 * 二级分类的实体类对象
 * @author 传智.郭嘉
 *CREATE TABLE `categorysecond` (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `FK936FCAF21DB1FD15` (`cid`),
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
 */
public class CategorySecond {
	private Integer csid;
	private String csname;
	// 二级分类所属的一级分类:
	private Category category;
	// 关联的商品的集合
	private Set<Product> products = new HashSet<Product>();
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
