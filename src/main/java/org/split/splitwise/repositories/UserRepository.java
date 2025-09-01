package org.split.splitwise.repositories;

import org.split.splitwise.models.BaseModel;
import org.split.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
