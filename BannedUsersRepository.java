package com.ECESWA.eRegistration.repository;

import com.ECESWA.eRegistration.entity.BannedUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedUsersRepository extends JpaRepository<BannedUsers, Integer> {
}
