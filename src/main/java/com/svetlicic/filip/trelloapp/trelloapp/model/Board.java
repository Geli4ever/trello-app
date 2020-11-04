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
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;
    private String keyString;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private Set<Cards> cardsSet = new HashSet<>();

    @ManyToMany(mappedBy = "boards")
    private Set<User> users = new HashSet<>();
}
