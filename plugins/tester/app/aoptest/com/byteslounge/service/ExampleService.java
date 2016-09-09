package aoptest.com.byteslounge.service;

import aoptest.Account;
import org.springframework.stereotype.Service;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 16-7-13.
 */
@Service
public class ExampleService {

    public void updateAccountBalance(Account account, Long amount) {
        System.out.println("Inside updateAccountBalance(). Account: "
                + account.getSeqNo() + ", amount: " + amount);
    }

    public List<Account> findCustomerAccounts(Long customerId) {
        System.out.println("Finding accounts for customer: " + customerId);
        List<Account> result = new ArrayList<Account>();
        result.add(new Account("000001", "Account 1"));
        return result;
    }

    public void updateAccountDescription(Account account) {
        System.out.println("Updating account description to :"
                + account.getAccountName());
    }

    public <T> void methodUsingGenerics(T parameter) {
        System.out.println("Inside methodUsingGenerics: "
                + parameter.getClass().getName());
    }

    public void showHello(){
        Logger.info("HELLO");
        Integer i = 10;
        System.out.println(i.doubleValue());
    }
}
