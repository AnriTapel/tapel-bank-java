package hello.dao;

import hello.entities.Operations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Xiaomi on 23.06.2019.
 */
public interface OperationsDao extends CrudRepository<Operations, Long> {

    List<Operations> findAllByReceiverAccount(String account);
    List<Operations> findAllBySenderAccount(String account);
    List<Operations> findAllByReceiverId(int id);
    List<Operations> findAllBySenderId(int id);

}
