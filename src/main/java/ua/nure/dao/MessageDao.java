package ua.nure.dao;

import org.springframework.data.repository.CrudRepository;
import ua.nure.entity.Message;

import java.util.List;

public interface MessageDao extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
