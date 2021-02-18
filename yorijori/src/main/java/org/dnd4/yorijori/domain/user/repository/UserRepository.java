package org.dnd4.yorijori.domain.user.repository;

import org.dnd4.yorijori.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
