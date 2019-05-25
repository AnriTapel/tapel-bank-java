package hello.dao;

import hello.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by ANRI on 15.05.2019.
 */

@Service
public class ClientDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Client> getClientDataById(int id) {
        String sql = "select * from clients where id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<Client>(Client.class));
    }

    public List<Client> getClientDataByPassport(String passport){
        String sql = "select * from clients where passport = ?";
        return jdbcTemplate.query(sql, new Object[]{passport}, new BeanPropertyRowMapper<Client>(Client.class));
    }

    public int createNewClient(Client client_data) throws SQLException {
        String sql = "insert into clients(first_name, last_name, passport, birtday, phone) values ('" +
                client_data.getClient_name() + "', '" + client_data.getClient_lastname() + "', '" +
                client_data.getClient_passport() + "', '" + client_data.getBirth_date() + "', '" +
                client_data.getClient_phone() + "')";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
                else
                    throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }
}
