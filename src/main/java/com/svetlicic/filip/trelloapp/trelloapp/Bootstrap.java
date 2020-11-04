package com.svetlicic.filip.trelloapp.trelloapp;

import com.svetlicic.filip.trelloapp.trelloapp.model.*;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.BoardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.CardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.CardsRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CardsRepository cardsRepository;
    private final CardRepository cardRepository;

    public Bootstrap(UserRepository userRepository, BoardRepository boardRepository, CardsRepository cardsRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.cardsRepository = cardsRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User filip = new User();
        filip.setFirstName("Filip");
        filip.setLastName("Svetlicic");
        filip.setEmail("svetli.filip@gmail.com");

        Login filipLogin = new Login();
        filipLogin.setUsername("geli4ver");
        filipLogin.setPassword("78919062Deja");
        filipLogin.setUser(filip);
        filip.setLogin(filipLogin);

        Set<Board> filipBoards = new HashSet<>();

        Board filipBoard1 = new Board();
        filipBoard1.setBoardName("Trello Project");
        filipBoard1.setKeyString("143fd32d3");

        Set<Cards> cardsSet = new HashSet<>();

        Cards filipBoardToDoCards = new Cards();
        filipBoardToDoCards.setKeyString("dj88932j38dj8");
        filipBoardToDoCards.setListName("To do");
        Set<Card> toDoCards = new HashSet<>();
        Card toDoCard = new Card();
        toDoCard.setKeyString("klsdjaskljd22");
        toDoCard.setDescription("Initialize project trello");
        toDoCard.setCards(filipBoardToDoCards);
        Card toDoCard1 = new Card();
        toDoCard1.setKeyString("fjkdjfkl2f39fu03");
        toDoCard1.setDescription("Add Entities");
        toDoCard1.setCards(filipBoardToDoCards);
        toDoCards.add(toDoCard);
        toDoCards.add(toDoCard1);
        filipBoardToDoCards.setCards(toDoCards);
        filipBoardToDoCards.setBoard(filipBoard1);
        cardsSet.add(filipBoardToDoCards);


        Cards filipBoardMeetings = new Cards();
        filipBoardMeetings.setKeyString("54op5opi34p");
        filipBoardMeetings.setListName("Meetings");
        Set<Card> meetingCards = new HashSet<>();
        Card meetingCard = new Card();
        meetingCard.setKeyString("3j9d903j");
        meetingCard.setDescription("Next meeting will be tomorrow");
        meetingCard.setCards(filipBoardMeetings);
        Card meetingCard1 = new Card();
        meetingCard1.setKeyString("fj2390j903j903");
        meetingCard1.setDescription("Topic: Filip will work at Ericsson");
        meetingCard1.setCards(filipBoardMeetings);
        meetingCards.add(meetingCard);
        meetingCards.add(meetingCard1);
        filipBoardMeetings.setCards(meetingCards);
        filipBoardMeetings.setBoard(filipBoard1);
        cardsSet.add(filipBoardMeetings);

        filipBoard1.setCardsSet(cardsSet);
        filipBoard1.getUsers().add(filip);
        filipBoards.add(filipBoard1);

        Board filipBoard2 = new Board();
        filipBoard2.setBoardName("Pet Clinic Project");
        filipBoard2.setKeyString("yi969y5690yi");

        Set<Cards> cardsSet1 = new HashSet<>();

        Cards filipBoardToDoCards1 = new Cards();
        filipBoardToDoCards1.setKeyString("d1kljjkl1ljk1");
        filipBoardToDoCards1.setListName("To do");
        Set<Card> toDoCards1 = new HashSet<>();
        Card toDoCard2 = new Card();
        toDoCard2.setKeyString("jj3d90j9032j90");
        toDoCard2.setDescription("Initialize project Pet Clinic");
        toDoCard2.setCards(filipBoardToDoCards1);
        Card toDoCard3 = new Card();
        toDoCard3.setKeyString("dj239dj9023j90");
        toDoCard3.setDescription("Add PC Entities");
        toDoCard3.setCards(filipBoardToDoCards1);
        toDoCards1.add(toDoCard2);
        toDoCards1.add(toDoCard3);
        filipBoardToDoCards1.setCards(toDoCards1);
        filipBoardToDoCards1.setBoard(filipBoard2);
        cardsSet1.add(filipBoardToDoCards1);

        Cards filipBoardMeetings1 = new Cards();
        filipBoardMeetings1.setKeyString("stu12y21tuu12yt");
        filipBoardMeetings1.setListName("Meetings");
        Set<Card> meetingCards1 = new HashSet<>();
        Card meetingCard2 = new Card();
        meetingCard2.setKeyString("030d033dd");
        meetingCard2.setDescription("Next PC meeting will be tomorrow");
        meetingCard2.setCards(filipBoardMeetings1);
        Card meetingCard3 = new Card();
        meetingCard3.setKeyString("d93i90393k90903");
        meetingCard3.setDescription("Topic: Filip will work at Ericsson");
        meetingCard3.setCards(filipBoardMeetings1);
        meetingCards1.add(meetingCard2);
        meetingCards1.add(meetingCard3);
        filipBoardMeetings1.setCards(meetingCards1);
        filipBoardMeetings1.setBoard(filipBoard2);
        cardsSet1.add(filipBoardMeetings1);

        filipBoard2.setCardsSet(cardsSet1);
        filipBoard2.getUsers().add(filip);
        filipBoards.add(filipBoard2);
        filip.setBoards(filipBoards);

        userRepository.save(filip);

//        User andrea = new User();
//        andrea.setFirstName("Andrea");
//        andrea.setLastName("Abrlic");
//        andrea.setEmail("abrlic.andrea@gmail.com");
//
//        Login andreaLogin = new Login();
//        andreaLogin.setUsername("leadea");
//        andreaLogin.setPassword("78919062Filip");
//        andreaLogin.setUser(andrea);
//        andrea.setLogin(andreaLogin);
//
//        Set<Board> andreaBoards = new HashSet<>();
//        Board andreaBoard = boardRepository.findById(1L).get();
//        andreaBoard.getUsers().add(andrea);
//        andreaBoards.add(boardRepository.save(andreaBoard));
//        Board andreaBoard1 = boardRepository.findById(2L).get();
//        andreaBoard1.getUsers().add(andrea);
//        andreaBoards.add(boardRepository.save(andreaBoard1));
//        andrea.setBoards(andreaBoards);
//
//        userRepository.save(andrea);
    }
}
