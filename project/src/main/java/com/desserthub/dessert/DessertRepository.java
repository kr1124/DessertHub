package com.desserthub.dessert;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    
    Dessert findByDescriptionOrName(String dessertChara, String dessertName);
    
    // name이 특정 텍스트로 시작하는 Dessert 검색
    List<Dessert> findByNameStartingWith(String dessertName);

    // name에 특정 텍스트가 포함된 Dessert 검색
    List<Dessert> findByNameContaining(String dessertName);

    // name이 특정 텍스트로 끝나는 Dessert 검색
    List<Dessert> findByNameEndingWith(String dessertName);

    // chara에 특정 텍스트가 포함된 Dessert 검색
    List<Dessert> findByDescriptionContaining(String dessertChara);
}