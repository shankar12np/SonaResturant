package com.example.sona.Repository;

import com.example.sona.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuId(String menuId);
}
