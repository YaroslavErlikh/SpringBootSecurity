package yaroslav.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaroslav.model.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
