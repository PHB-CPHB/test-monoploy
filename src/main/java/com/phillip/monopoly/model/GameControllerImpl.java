package com.phillip.monopoly.model;

import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameControllerImpl implements GameController {
    List<Player> players = new ArrayList<>();

    Player currentTurn;

    @Inject
    Game game;

    @Override
    public void move(Player player) throws Exception {
        if (!currentTurn.equals(player)) {
            throw new Exception();
        }
        game.move(player);
    }

    @Override
    public GameController connect(Player player, Piece piece) {
        piece.addPlayer(player);
        game.addPiece(piece);
        players.add(player);
        return this;
    }

    @Override
    public void startGame() {
        currentTurn = players.get(0);
        game.init();
    }
}
