package hello.dao;

import hello.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ANRI on 15.05.2019.
 */

@Service
public class AccountDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List getClientAccounts(int id) throws SQLException{
        String sql = "select account from accounts where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},List.class);
    }

    public boolean createNewAccount(Account account, int client_id) throws SQLException{
        String sql = "insert into accounts(client_id, account, cvc, keyword, balance) values (" + client_id + ", '" +
                account.getClient_account() + "', '" + account.getClient_cvc() + "', '" +
                account.getClient_keyword() + "', " + account.getClient_balance() + ")";
        int result = jdbcTemplate.update(sql);
        return result > 0;
    }

}
