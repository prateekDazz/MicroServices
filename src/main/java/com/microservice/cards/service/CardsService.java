package com.microservice.cards.service;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;

public interface CardsService {

    boolean checkExistMobileNo(String mobileNo);

    void createCard(String mobileNo);
    Cards findByMobileNo(String MobileNo);
    public CardsDto updateAccount(CardsDto cardsDto);


    void deleteCard(Long cardId);
}
