package example.account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;


public class AccountManagerTest {
    @Test
    void successToWithdrawIfBalanceIsPositive(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(1500);
        AccountManagerImpl accountManager = new AccountManagerImpl();
        //act
        String result = accountManager.withdraw(customer,1000);
        //assert
        Assertions.assertEquals("success",result);
    }
    @Test
    void failsToWithdrawIfCreditNotAllowed(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(1500);
        Mockito.when(customer.isCreditAllowed()).thenReturn(false);
        AccountManagerImpl accountManager = new AccountManagerImpl();
        //act
        String result = accountManager.withdraw(customer,1504);
        //assert
        Assertions.assertEquals("insufficient account balance",result);
    }
    @Test
    void failsToWithDrawIfExpectedBalanceLessThanMaxCredit(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(1000);
        Mockito.when(customer.isCreditAllowed()).thenReturn(true);
        Mockito.when(customer.isVip()).thenReturn(false);
        AccountManagerImpl accountManager = new AccountManagerImpl();
        //act
        String result = accountManager.withdraw(customer,2001);
        //assert
        Assertions.assertEquals("maximum credit exceeded",result);
    }
    @Test
    void  successToWithdrawIfCustomerVip(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(1000);
        Mockito.when(customer.isCreditAllowed()).thenReturn(true);
        Mockito.when(customer.isVip()).thenReturn(true);
        AccountManagerImpl accountManager = new AccountManagerImpl();
        //act
        String result = accountManager.withdraw(customer,2001);
        //assert
        Assertions.assertEquals("success",result);
    }
    @Test
    void  successToWithdrawIfExpectedBalanceMoreThanMaxCredit(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(1000);
        Mockito.when(customer.isCreditAllowed()).thenReturn(true);
        Mockito.when(customer.isVip()).thenReturn(false);
        AccountManagerImpl accountManager = new AccountManagerImpl();
        //act
        String result = accountManager.withdraw(customer,2000);
        //assert
        Assertions.assertEquals("success",result);
    }


}
