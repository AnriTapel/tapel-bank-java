package hello.dao;

import hello.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ANRI on 15.05.2019.
 */

public interface AccountDao extends CrudRepository<Account, Long>{

    List<Account> findAllByClientId(int id);

    Account findByClientAccount(String account);

    /*@Autowired
    PasswordEncoder customPasswordEncoder;

    public boolean isKeywordMatched(){
        return customPasswordEncoder.matches("Karnava" +
                "", "$2a$04$WxZbY/U2kJ4KLjswcnLk7uFCz5HJFU2v3ZEHkcLOU15amZfIsIVp.");
    }*/

}
