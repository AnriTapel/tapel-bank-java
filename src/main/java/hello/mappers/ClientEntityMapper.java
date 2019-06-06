package hello.mappers;

import hello.entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Xiaomi on 30.05.2019.
 */
public class ClientEntityMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();

        client.setId(resultSet.getInt("id"));
        client.setClient_name(resultSet.getString("first_name"));
        client.setClient_lastname(resultSet.getString("last_name"));
        client.setBirth_date(resultSet.getDate("birthday"));
        client.setClient_phone(resultSet.getString("phone"));
        client.setClient_passport(resultSet.getString("passport"));

        return client;
    }
}
