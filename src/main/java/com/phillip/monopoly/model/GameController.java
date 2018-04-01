package com.phillip.monopoly.model;

public interface GameController {
    void move(Player player) throws Exception;
    GameController connect(Player player, Piece piece);
    void startGame();
}
