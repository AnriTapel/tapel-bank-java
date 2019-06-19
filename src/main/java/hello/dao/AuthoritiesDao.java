package hello.dao;

import hello.entities.Authorities;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Xiaomi on 17.06.2019.
 */


public interface AuthoritiesDao extends CrudRepository<Authorities, Long> {

    Authorities findByUsername(String username);

}
