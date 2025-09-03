package org.split.splitwise.repositories;

import org.split.splitwise.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Groups,Long> {

}
