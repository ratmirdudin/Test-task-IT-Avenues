package com.ratmirdudin.userservice.repositories;

import com.ratmirdudin.userservice.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
