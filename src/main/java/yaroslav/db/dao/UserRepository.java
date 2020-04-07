package yaroslav.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yaroslav.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT user from User user where user.username = :username")
    User findByUsername(String username);
}
