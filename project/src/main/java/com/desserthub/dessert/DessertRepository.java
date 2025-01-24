package com.desserthub.dessert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DessertRepository extends JpaRepository<Dessert, Long> {
    
    Dessert findByDessertCharaOrDessertName(String dessertChara, String dessertName);
    
    // name이 특정 텍스트로 시작하는 Dessert 검색
    List<Dessert> findByDessertNameStartingWith(String dessertName);

    // name에 특정 텍스트가 포함된 Dessert 검색
    List<Dessert> findByDessertNameContaining(String dessertName);

    // name이 특정 텍스트로 끝나는 Dessert 검색
    List<Dessert> findByDessertNameEndingWith(String dessertName);

    // chara에 특정 텍스트가 포함된 Dessert 검색
    List<Dessert> findByDessertCharaContaining(String dessertChara);
}