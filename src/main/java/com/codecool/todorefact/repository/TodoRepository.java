package com.codecool.todorefact.repository;

import com.codecool.todorefact.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Modifying
    @Query("update Todo t set t.status = ?1 where t.id = ?2")
    @Transactional
    void toggleUpdate(boolean status, Long id);

    @Modifying
    @Query("update Todo t set t.status = ?1")
    @Transactional
    void toggleAll(boolean status);

    List<Todo> findByStatusTrue();

    List<Todo> findByStatusFalse();
}
