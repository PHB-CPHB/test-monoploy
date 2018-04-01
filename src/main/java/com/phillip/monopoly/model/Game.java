package com.phillip.monopoly.model;

public interface Game {
    void init();
    void move(Player player);
    void addPiece(Piece piece);
    Piece getPiece(Player player);
    void endGame();
}
