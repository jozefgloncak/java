package gloncak.jozef.springboot.restfulwebservice.user;

import java.util.List;

public interface UserDAO {
    List<User> findUsers();
}
