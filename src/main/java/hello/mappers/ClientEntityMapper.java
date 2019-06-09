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
        client.setClientName(resultSet.getString("first_name"));
        client.setClientLastname(resultSet.getString("last_name"));
        client.setBirthday(resultSet.getDate("birthday"));
        client.setClientPhone(resultSet.getString("phone"));
        client.setClientPassport(resultSet.getString("passport"));

        return client;
    }
}
