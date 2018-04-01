package com.phillip.monopoly;

import com.phillip.monopoly.model.Dice;
import com.phillip.monopoly.model.Game;
import com.phillip.monopoly.model.Piece;
import com.phillip.monopoly.model.Player;
import com.phillip.monopoly.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerTests {

    @Inject
    Game monopoly;

    @MockBean(name = "dice1")
    Dice dice1;

    @MockBean(name = "dice2")
    Dice dice2;

    Piece piece;

    private Player player;

    @Before
    public void setup() {
        this.piece = new PieceStub();
        player = mock(Player.class);
        this.piece.addPlayer(this.player);
        this.monopoly.addPiece(this.piece);
        this.monopoly.init();
    }

    @After
    public void tearDown() {
        monopoly.endGame();
    }

    @Test
    public void takeTurnTest() throws Exception {
        int oldPosition = piece.getLocation();
        move();
        assertEquals(oldPosition + 10, piece.getLocation());
    }

    @Test
    public void takeTurnBoundaryTest() throws Exception {
        piece.setLocation(39);
        move();
        assertEquals(9, piece.getLocation());
    }

    @Test
    public void takeTurnBoundaryTest2() throws Exception {
        piece.setLocation(29);
        move();
        assertEquals(39, piece.getLocation());
    }

    @Test
    public void takeTurnBoundaryTest3() throws Exception {
        piece.setLocation(30);
        move();
        assertEquals(0, piece.getLocation());
    }

    @Test
    public void piecesTest() {
        Piece piece = monopoly.getPiece(player);
        assertNotNull(piece);
        assertEquals(player, piece.getPlayer());
    }

    private void move() {
        when(dice1.roll()).thenReturn(5);
        when(dice2.roll()).thenReturn(5);
        monopoly.move(player);
    }
}
