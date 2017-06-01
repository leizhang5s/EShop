package cn.leizhang.product;

import java.util.List;

import cn.leizhang.category.Category;
import cn.leizhang.category.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	//接收用户cid
	private Integer cid;
	//接收csid
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	private ProductService productService;
	private CategoryService categoryService; 
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	private List<Product> pageBean;
	public List<Product> getPageBean() {
		return pageBean;
	}
	public Product getModel() {
		
		return product;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//查询一级分类的商品
	public String findByCid()
	{
		List<Category> categoryList=categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		pageBean=productService.findByCid(cid);
		//System.out.println(pageBean.get(0).getPname());
		return "findByCidSuccess";
	}
	//查询二级分类上平
	public String findByCsid()
	{
		List<Category> categoryList=categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		pageBean=productService.findByCsid(csid);
		return "findByCsidSuccess";
		
		
	}
	public String findByPid()
	{
		System.out.println(product.getPid());
		List<Category> categoryList=categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		product=productService.findProductByPid(product.getPid());
		return "findByPidSuccess";
	}
		
}
