package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.mapper.CardMapper;
import com.svetlicic.filip.trelloapp.trelloapp.model.Card;
import com.svetlicic.filip.trelloapp.trelloapp.model.Cards;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.CardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.CardsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CardServiceImpl implements CardService{

    private final CardsRepository cardsRepository;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardsRepository cardsRepository, CardRepository cardRepository, CardMapper cardMapper) {
        this.cardsRepository = cardsRepository;
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public CardDTO saveCardDTO(Long cardsId, CardDTO cardDTO) {
        Optional<Cards> cardsOptional = cardsRepository.findById(cardsId);

        if(!cardsOptional.isPresent()){
            //todo impl error handling
            log.error("board not found with id: " + cardsId);
            return new CardDTO();
        } else {
            Cards cards = cardsOptional.get();

            String keyString = "";
            boolean quit = false;

            while(!quit){
                quit = true;
                keyString = GeneratedString.INSTANCE.generateRandomString();
                for(Card card : cardRepository.findAll()){
                    if(card.getKeyString().equals(keyString)){
                        quit = false;
                        break;
                    }
                }
            }

            if(cardDTO.getId() != null){
                Optional<Card> cardOptional = cards.getCards()
                        .stream()
                        .filter(card -> card.getId().equals(cardDTO.getId()))
                        .findFirst();
                if(cardOptional.isPresent()){
                    Card cardFound = cardOptional.get();
                    cardFound.setDescription(cardDTO.getDescription());
                } else {
                    Card card = cardMapper.cardDtoToCard(cardDTO);
                    card.setId(null);
                    card.setCards(cards);
                    card.setKeyString(keyString);
                    cards.getCards().add(card);
                }
            } else {
                Card card = cardMapper.cardDtoToCard(cardDTO);
                card.setCards(cards);
                card.setKeyString(keyString);
                cards.getCards().add(card);
            }

            Cards savedCards = cardsRepository.save(cards);

            String finalKeyString = keyString;
            Optional<Card> savedCardsOptional = savedCards.getCards().stream()
                    .filter(card -> card.getKeyString().equals(finalKeyString))
                    .findFirst();

            if(!savedCardsOptional.isPresent()){
                log.error("something went wrong!");
            }

            return cardMapper.cardToCardDTO(savedCardsOptional.get());
        }
    }
}
