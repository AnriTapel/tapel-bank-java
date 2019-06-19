package hello.dao;

import hello.entities.Users;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Created by ANRI on 15.05.2019.
 */

public interface UsersDao extends CrudRepository<Users, Long>{

    Users findById(int id);

    Users findByPassport(String clientPassport);

    List<Users> findByNameAndLastname(String name, String lastName);

}
