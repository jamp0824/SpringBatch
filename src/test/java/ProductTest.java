import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

public class ProductTest {
    private static final Product CACHED_PRODUCT = new Product("some parameters for cached product");
    private static final Product DB_PRODUCT = new Product("some parameters for DB product");

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
        when(productCacheDaoMock.getProductLookUpData(CoreMatchers.any())).thenReturn(CACHED_PRODUCT);

        final Product product = service.getProductLookUpData();

        Assert.assertEquals(CACHED_PRODUCT, product);
        verify(productCacheDaoMock, times(1)).getProductLookUpData(Mockito.any());
        verify(productDaoMock, never()).getIpacMetricCodeLookUpData(Mockito.any());
    }

    @Test
    public void testDB() {
        when(productCacheDaoMock.getProductLookUpData(any())).thenThrow(new IllegalStateException());
        when(productDaoMock.getIpacMetricCodeLookUpData(any())).thenReturn(DB_PRODUCT);

        final Product product = service.getProductLookUpData();

        Assert.assertEquals(DB_PRODUCT, product);
        verify(productCacheDaoMock, times(1)).getProductLookUpData(any());
        verify(productDaoMock, times(1)).getIpacMetricCodeLookUpData(any());
    }
}
