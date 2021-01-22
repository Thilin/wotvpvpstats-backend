package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrNickName(String email, String nickName);
}
