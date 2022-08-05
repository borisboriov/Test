package com.example.TestTask.repositories;

import com.example.TestTask.entities.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {


    @Query("select s from Socks s where s.color = :color and s.quantity = :operation and s.cotton =  :cotton")
    List<Socks> getSocksWithParams(@Param("color") String color,@Param("operation") int operation,@Param("cotton") int cotton);

    @Query("select s  from Socks s where s.color = :color  and s.cotton = :cotton")
    Optional<Socks> findSockByColorAndCottonPart(@Param("color") String color,@Param("cotton") int cotton);

    @Modifying
    @Query(value = "update Socks s set  quantity = :quantity where id = :id", nativeQuery = true)
    void changeQuantity(@Param("id") Long id, @Param("quantity") int quantity);

}

