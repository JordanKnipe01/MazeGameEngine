import mazerunner.engine.GameEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameEngine {
    @Test
    void testGetSize() {
       GameEngine ge = new GameEngine(10,3);

        assertEquals(10, ge.getSize());

    }

    @Test
    void TestDifficulty(){
        GameEngine ge = new GameEngine(10, 21);
        //Apples on scene
        assertEquals(0, ge.stats.getAppleAmount());
        //Traps on scene / difficulty
        assertEquals(10,ge.stats.getDifficultyLevel());
        //Max difficulty = 10
    }
    @Test
    void MoveUp(){
        GameEngine ge = new GameEngine(10, 1);
        assertEquals(ge.map[9][0], 8);
        ge.moveUp();
        assertEquals(ge.map[8][0], 8);

    }
    @Test
    void MoveRight(){
        GameEngine ge = new GameEngine(10, 1);
        assertEquals(ge.map[9][0], 8);
        ge.moveRight();
        assertEquals(ge.map[9][1], 8);
    }
    @Test
    void MoveLeft(){
        GameEngine ge = new GameEngine(10, 1);
        assertEquals(ge.map[9][0], 8);
        ge.moveRight();
        assertEquals(ge.map[9][1], 8);
        ge.moveLeft();
        assertEquals(ge.map[9][0],8);
    }
    @Test
    void MoveDown(){
        GameEngine ge = new GameEngine(10, 1);
        //star position
        assertEquals(ge.map[9][0], 8);
        //Move up
        ge.moveUp();
        assertEquals(ge.map[8][0], 8);
        //Move Down
        ge.moveDown();
        assertEquals(ge.map[9][0],8);
    }
}
