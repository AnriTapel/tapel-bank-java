package hello.dao;

import hello.entities.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ANRI on 15.05.2019.
 */

public interface ClientDao extends CrudRepository<Client, Long>{

    Client findById(int id);

    Client findByClientPassport(String clientPassport);

    Client findByClientNameAndClientLastname(String name, String lastName);

}
