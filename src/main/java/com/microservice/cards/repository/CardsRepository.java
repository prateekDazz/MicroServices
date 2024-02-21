package com.microservice.cards.repository;

import com.microservice.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {

    public Optional<Cards> findByMobileNumber(String mobileNumber);
}
