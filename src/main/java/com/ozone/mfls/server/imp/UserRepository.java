package com.ozone.mfls.server.imp;

import com.ozone.mfls.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
        User findByUserName(String userName);
}
