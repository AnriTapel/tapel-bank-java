package hello.dao;

import hello.entities.Accounts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ANRI on 15.05.2019.
 */

public interface AccountsDao extends CrudRepository<Accounts, Long>{

    Accounts findById(int id);

    List<Accounts> findAllByClientId(int id);

    Accounts findByAccount(String account);

}
