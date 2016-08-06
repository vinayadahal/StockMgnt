package ApplicationModels;

import DataSource.Datasource;
import ManagedBean.Product;
import SystemModels.DeleteModel;
import SystemModels.InsertModel;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductModel {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    InsertModel objInsert = new InsertModel();
    UpdateModel objUpdate = new UpdateModel();
    DeleteModel objDelete = new DeleteModel();
    List<Product> list = new ArrayList();
    private final String tableName = "product";

    public List<Product> getAllProductBean() {
        objSelect.select("*");
        objSelect.from(tableName);
        List<Map> result = objSelect.runQuery();
        CategoryModel objCategoryModel = new CategoryModel();
        for (Map product : result) {
            Product objProduct = new Product();
            objProduct.setId(product.get("id").toString());
            objProduct.setCategoryId(objCategoryModel.getSingleCategoryBean(product.get("category_id").toString()).getName());
            objProduct.setName(product.get("name").toString());
            objProduct.setDescription(product.get("description").toString());
            objProduct.setPurchasePrice(product.get("purchasePrice").toString());
            objProduct.setSellingPrice(product.get("sellingPrice").toString());
            list.add(objProduct);
        }
        return list;
    }

    public Map<String, String> getAllCategoryList() {
        objSelect.select("*");
        objSelect.from("category");
        List<Map> result = objSelect.runQuery();
        Map<String, String> CategoryList = new HashMap<>();
        for (Map category : result) {
            CategoryList.put(category.get("name").toString(), category.get("id").toString());
        }
        return CategoryList;
    }

    public Product getSingleProduct(String id) {
        objSelect.select("*");
        objSelect.from(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objSelect.where(cols, vals);
        List<Map> result = objSelect.runQuery();
        Product objProduct = new Product();
        for (Map product : result) {
            objProduct.setId(product.get("id").toString());
            objProduct.setCategoryId(product.get("category_id").toString());
            objProduct.setName(product.get("name").toString());
            objProduct.setDescription(product.get("description").toString());
            objProduct.setPurchasePrice(product.get("purchasePrice").toString());
            objProduct.setSellingPrice(product.get("sellingPrice").toString());
        }
        return objProduct;
    }

    public int addProduct(String name, String pp, String sp, String desc, String category_id) {
        objInsert.insert(tableName);
        String[] cols = {"name", "purchasePrice", "sellingPrice", "description", "category_id"};
        String[] vals = {name, pp, sp, desc, category_id};
        objInsert.values(cols, vals);
        return objInsert.runUpdate();
    }

    public int updateProduct(String name, String pp, String sp, String desc, String category_id, String id) {
        objUpdate.update(tableName);
        String[] col = {"name", "purchasePrice", "sellingPrice", "description", "category_id"};
        String[] val = {name, pp, sp, desc, category_id};
        System.out.println(id);
        objUpdate.set(col, val);
        String[] whereCol = {"id"};
        String[] whereVal = {id};
        objUpdate.where(whereCol, whereVal);
        return objUpdate.runQuery();
    }

    public void delete(String id) {
        objDelete.delete(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objDelete.where(cols, vals);
        objDelete.runQuery();
    }

}
