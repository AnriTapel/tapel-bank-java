package hello.dao;

import hello.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ANRI on 15.05.2019.
 */

public interface ClientDao extends CrudRepository<Client, Long>{

    Client findById(int id);

    Client findByClientPassport(String clientPassport);

    List<Client> findByClientNameAndClientLastname(String name, String lastName);

}
