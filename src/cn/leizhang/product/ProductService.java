package cn.leizhang.product;

import java.util.List;

public class ProductService {
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHotList() {
		
		return productDao.findHotList();
	}

	public List<Product> findNewList() {
		
		return productDao.findNewList();
	}

	public List<Product> findByCid(Integer cid) {
		
		return productDao.findByCid(cid);
	}

	public Product findProductByPid(Integer pid) {
		
		return productDao.findProductByPid(pid);
	}

	public List<Product> findByCsid(Integer csid) {
		
		return productDao.findByCsid(csid);
	}

	

	

}
