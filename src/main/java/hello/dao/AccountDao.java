package hello.dao;

import hello.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    /*@Autowired
    PasswordEncoder customPasswordEncoder;

    public boolean isKeywordMatched(){
        return customPasswordEncoder.matches("Karnava" +
                "", "$2a$04$WxZbY/U2kJ4KLjswcnLk7uFCz5HJFU2v3ZEHkcLOU15amZfIsIVp.");
    }*/

    public List getClientAccountsById(int id) throws SQLException {
        String sql = "SELECT account FROM accounts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, List.class);
    }

    public boolean createNewAccount(Account account, int client_id) throws SQLException {
        /*System.out.println(isKeywordMatched());
        String encodedPassword = customPasswordEncoder.encode(account.getClient_keyword());*/
        String sql = "insert into accounts(client_id, account, cvc, keyword, balance) values (" + client_id + ", '" +
                account.getClient_account() + "', '" + account.getClient_cvc() + "', '" +
                account.getClient_keyword() + "', " + account.getClient_balance() + ")";
        int result = jdbcTemplate.update(sql);
        return result > 0;
    }

    public Account getClientAccountByAccount(String account) {
        String sql = "select * from account where account='" + account + "';";
        return jdbcTemplate.queryForObject(sql, Account.class);
    }

}
