package example.store;

import example.account.AccountManagerImpl;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StoreTest {
    @Test
    void failToBuyIfProductQuantityEqualZero(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Product product = Mockito.mock(Product.class);
        AccountManagerImpl accountManager = Mockito.mock(AccountManagerImpl.class);
        StoreImpl store = new StoreImpl(accountManager);
        Mockito.when(product.getQuantity()).thenReturn(0);
        //act and Assert
        try {
            store.buy(product,customer);
            Assertions.fail();
        } catch (Exception ex){
            Assertions.assertEquals("Product out of stock",ex.getMessage());
        }
    }
    @Test
    void failToBuyIfCustomerFailToWithdraw(){
        Customer customer = Mockito.mock(Customer.class);
        Product product = Mockito.mock(Product.class);
        AccountManagerImpl accountManager = Mockito.mock(AccountManagerImpl.class);
        StoreImpl store = new StoreImpl(accountManager);
        Mockito.when(accountManager.withdraw(customer,product.getPrice())).thenReturn("insufficient account balance");
        Mockito.when(product.getQuantity()).thenReturn(2);
        try {
            store.buy(product,customer);
            Assertions.fail();
        } catch (Exception ex){
            Assertions.assertEquals("Payment failure: insufficient account balance",ex.getMessage());
        }
    }
    @Test
    void successToBuy(){
        Customer customer = Mockito.mock(Customer.class);
        Product product = Mockito.mock(Product.class);
        AccountManagerImpl accountManager = Mockito.mock(AccountManagerImpl.class);
        StoreImpl store = new StoreImpl(accountManager);
        Mockito.when(accountManager.withdraw(customer,product.getPrice())).thenReturn("success");
        Mockito.when(product.getQuantity()).thenReturn(2);
        try {
            store.buy(product,customer);
        }catch (Exception ex){
            Assertions.fail();
        }

    }

}
