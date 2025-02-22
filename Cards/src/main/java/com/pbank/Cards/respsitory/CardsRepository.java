package com.pbank.Cards.respsitory;

import com.pbank.Cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Long> {
   Optional<Cards> findByMobileNumber(String mobileNumber);

   Optional<Cards> findByCardNumber( String cardNumber);
}
