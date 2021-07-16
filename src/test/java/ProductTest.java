import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ProductTest {
    private static final Product CACHED_PRODUCT = new Product();
    private static final Product DB_PRODUCT = new Product();

    private ProductService service;
    private ProductDao productDaoMock;
    private ProductCacheDao productCacheDaoMock;

    @Before
    public void setup() {
        service = new ProductService();
        productDaoMock = mock(ProductDao.class);
        service.setProductDao(productDaoMock);

        productCacheDaoMock = mock(ProductCacheDao.class);
        service.setProductCacheDao(productCacheDaoMock);
    }

    @Test
    public void testCache() {
        Mockito.when(productCacheDaoMock.getProductLookUpData(new Object())).thenReturn(CACHED_PRODUCT);

        final Product product = service.getProductLookUpData();

        Assert.assertEquals(CACHED_PRODUCT, product);
        verify(productCacheDaoMock, times(1)).getProductLookUpData(new Object());
        verify(productDaoMock, never()).getIpacMetricCodeLookUpData();
    }

    @Test
    public void testDB() {
       // Mockito.when(((OngoingStubbing<Product>) productCacheDaoMock.getProductLookUpData(new Object())).thenThrow(new IllegalStateException());
        Mockito.when(productDaoMock.getIpacMetricCodeLookUpData()).thenReturn(DB_PRODUCT);

        final Product product = service.getProductLookUpData();

        Assert.assertEquals(DB_PRODUCT, product);
        verify(productCacheDaoMock, times(1)).getProductLookUpData(new Object());
        verify(productDaoMock, times(1)).getIpacMetricCodeLookUpData();
    }
}
