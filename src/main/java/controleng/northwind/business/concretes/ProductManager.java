package controleng.northwind.business.concretes;

import controleng.northwind.business.abstracts.ProductService;
import controleng.northwind.core.utilities.results.DataResult;
import controleng.northwind.core.utilities.results.Result;
import controleng.northwind.core.utilities.results.SuccessDataResult;
import controleng.northwind.core.utilities.results.SuccessResult;
import controleng.northwind.dataAccess.abstracts.ProductDao;
import controleng.northwind.entities.concretes.Product;
import controleng.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductManager implements ProductService {
    private ProductDao productDao;
    @Autowired

    public ProductManager(ProductDao productDao) {

        super();
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(),"Data listelendi.");

    }

    @Override
    public DataResult <Product> findById(int id) {
        return new SuccessDataResult<Product>(this.productDao.findById(id),"İşlem başarılı");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent(),"İşlem başarılı.");
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort=Sort.by(Sort.Direction.ASC,"productName");
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"İşlem başarılı");
    }


    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün eklendi.");
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.getByProductName(productName),"Data listelendi.");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategory_CategoryId(String productName, int categoryId) {
        return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId),"Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategory_CategoryId(String productName, int categoryId) {

        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName,categoryId),"Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByCategoryIn(List<Integer> categories) {

        return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIn(categories),"Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {

        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName),"Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {

        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName),"Data listelendi.");
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails(),"Data listelendi.");
    }

}
