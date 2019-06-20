package hello.mappers;

import hello.entities.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Xiaomi on 30.05.2019.
 */
public class UsersEntityMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet resultSet, int i) throws SQLException {
        Users client = new Users();

        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setLastname(resultSet.getString("lastname"));
        client.setBirthday(resultSet.getDate("birthday"));
        client.setUsername(resultSet.getString("username"));
        client.setPassport(resultSet.getString("passport"));
        client.setEnabled(resultSet.getBoolean("enabled"));
        client.setPassword(resultSet.getString("password"));

        return client;
    }
}
