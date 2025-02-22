package com.pbank.Cards.service.impl;

import com.pbank.Cards.constants.CardsConstants;
import com.pbank.Cards.dto.CardsDto;
import com.pbank.Cards.entity.Cards;
import com.pbank.Cards.exception.CardAlreadyExistsException;
import com.pbank.Cards.exception.ResourceNotFoundException;
import com.pbank.Cards.mapper.CardsMapper;
import com.pbank.Cards.respsitory.CardsRepository;
import com.pbank.Cards.service.ICardsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
public class CardsServiceImpl implements ICardsService {

    CardsRepository cardsRepository;

    public CardsServiceImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
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
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        //CardsDto cardsDto = CardsMapper.mapToCardsDto(cards, new CardsDto());
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
