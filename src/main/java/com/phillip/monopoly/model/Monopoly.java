package com.phillip.monopoly.model;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class Monopoly implements Game {

    List<Piece> pieces = new ArrayList<>();
    List<Field> fields;

    @Inject
    Dice dice1;

    @Inject
    Dice dice2;

    @Override
    public void init() {
        this.fields = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            fields.add(new Field());
        }

        pieces.forEach(piece -> {
            piece.setLocation(0);
        });
    }

    @Override
    public void move(Player player) {
        int roll = roll();
        Piece piece = getPiece(player);
        int oldLocation = piece.getLocation();

        getPiece(player).setLocation(calculatePosition(oldLocation, roll));
    }

    @Override
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    @Override
    public Piece getPiece(Player player) {
        for (Piece piece : pieces) {
            if (piece.getPlayer().equals(player)) {
                return piece;
            }
        }
        return null;
    }

    @Override
    public void endGame() {
        this.fields = new ArrayList<>();
        this.pieces = new ArrayList<>();
    }

    private int roll() {
        return dice1.roll() + dice2.roll();
    }

    private int calculatePosition(int oldPosition, int roll) {
        int total = oldPosition + roll;
        if (total >= fields.size()) {
            return total - fields.size();
        } else {
            return total;
        }
    }

}
