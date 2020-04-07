package yaroslav.service.interfaces;

import yaroslav.model.User;
import yaroslav.model.role.Role;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    boolean addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    List<Role> getAllRoles();

    boolean userIsExist(User user);
}
