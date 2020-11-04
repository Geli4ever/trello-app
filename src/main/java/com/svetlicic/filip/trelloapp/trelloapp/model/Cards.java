package com.svetlicic.filip.trelloapp.trelloapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String listName;
    private String keyString;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cards")
    private Set<Card> cards = new HashSet<>();
}
