
public class MockitoTest {
	
	public Product getProductLookUpData() {
		ProductCacheDao productCacheDao = new ProductCacheDao();
		ProductDao productDao = new ProductDao();
        Product product = null;
        try{
            // Try to get value from cacheable method
            product = productCacheDao.getProductLookUpData();
            //statements
        } catch (Exception ex) {
            // getting value from db
            product = productDao.getIpacMetricCodeLookUpData();
            //statements
        }

        return product;
    }


}
