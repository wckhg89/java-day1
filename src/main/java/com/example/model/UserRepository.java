package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 강홍구 on 2016-11-28.
 */

public interface UserRepository extends JpaRepository<User, Long> { // <type, pk>
    User findByUserId(String userId); // 메소드 컨벤션에 의해서 쿼리를 만들 수 있다.

}
