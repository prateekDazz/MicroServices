package com.microservice.cards.service;

import com.microservice.cards.constants.CardsConstants;
import com.microservice.cards.controller.CardsMapper;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;
import com.microservice.cards.exception.ResourceNotFoundException;
import com.microservice.cards.exception.UserAlreadyExistsException;
import com.microservice.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CardsServiceImpl implements CardsService{
    @Autowired
    private CardsRepository cardsRepository;
    @Override
    public boolean checkExistMobileNo(String mobileNo) {

Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNo);
return card.isPresent();

    }

    @Override
    public void createCard(String mobileNo) {
        if(checkExistMobileNo(mobileNo))
        {
            throw new UserAlreadyExistsException("user with given "+mobileNo+" already exists ");
        }
Cards  card = createNewCard(mobileNo);
        card.setUpdatedAt(LocalDateTime.now());
        card.setUpdatedBy("PRT");
        card.setCreatedAt(LocalDateTime.now());
        card.setCreatedBy("PRT");
        cardsRepository.save(card);

    }

    @Override
    public Cards findByMobileNo(String mobileNo) {

        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNo);
if(!card.isPresent())
{
    throw new ResourceNotFoundException("card","mobileNo",mobileNo);
}
return card.get();
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto updateAccount(CardsDto cardsDto) {
        Cards cards = CardsMapper.mapToCards(cardsDto,new Cards());

//        accounts.setCustomerId(customer.getCustomerId());
        Optional<Cards> savedCard =  cardsRepository.findById(cards.getCardId());
        if(!savedCard.isPresent())
        {
            throw new ResourceNotFoundException("card","id",cards.getCardNumber().toString());
        }

        Cards updtCard = savedCard.get();
        updtCard.setMobileNumber(cards.getMobileNumber());
        updtCard.setCardNumber(cards.getCardNumber());
        updtCard.setCardType(cards.getCardType());
        updtCard.setAmountUsed(cards.getAmountUsed());
        updtCard.setAvailableAmount(cards.getAvailableAmount());
        cardsRepository.save(updtCard);

        return cardsDto;

    }

    @Override
    public void deleteCard(Long cardId) {
        Optional<Cards>card =cardsRepository.findById(cardId);
        if(!card.isPresent())
        {
            throw new ResourceNotFoundException("card","id",cardId.toString());

        }
        cardsRepository.delete(card.get());

    }
}
