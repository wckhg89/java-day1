package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 강홍구 on 2016-11-28.
 */

public interface UserRepository extends JpaRepository<User, Long> { // <type, pk>


}
