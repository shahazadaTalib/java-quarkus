package eagel.tailung.service;

import eagel.tailung.dto.Account;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AccountService {
    private static final Logger LOGGER = Logger.getLogger(AccountService.class);
    public boolean isAccountAlreadyExist(Account account1){
        LOGGER.info("Response::isAccountAlreadyExist");
     Account account = getAccountByAccNumber(account1.getAccountNumber());
     if(account != null){
         LOGGER.info("Returning from isAccountAlreadyExist::isAccountAlreadyExist");
         return true;
     }
        LOGGER.info("Returning from isAccountAlreadyExist::isAccountAlreadyExist");
        return openNewAccount(account1);
    }
    private boolean openNewAccount(Account accountNumber){
        LOGGER.info("Response::openNewAccount");
        return true;
    }
    private Account getAccountByAccNumber(long accountNumber){
        LOGGER.info("Response::getAccountByAccountNumber");
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setName("Rakesh");
        LOGGER.info("Returning from getAccountByAccNumber::getAccountByAccountNumber");
        return  account;
    }
}
