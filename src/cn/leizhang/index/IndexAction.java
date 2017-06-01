package cn.leizhang.index;

import java.util.List;

import cn.leizhang.category.Category;
import cn.leizhang.category.CategoryService;
import cn.leizhang.product.Product;
import cn.leizhang.product.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
		private CategoryService categoryService;
		private ProductService productService;
		private List<Product> hotList;
		private List<Product> newList;
	public void setCategoryService(CategoryService categoryService) {
			this.categoryService = categoryService;
		}
		public void setProductService(ProductService productService) {
			this.productService = productService;
		}

	public List<Product> getHotList() {
			return hotList;
		}
		public List<Product> getNewList() {
			return newList;
		}
		
		
	public String execute()
		{
			List<Category> categoryList=categoryService.findAll();
			ActionContext.getContext().getSession().put("categoryList",categoryList);
			hotList=productService.findHotList();
			newList=productService.findNewList();
			return "IndexSuccess";
		}
}
