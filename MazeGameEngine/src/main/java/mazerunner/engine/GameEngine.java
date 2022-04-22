package mazerunner.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class GameEngine {

    public Stats stats = new Stats();
    /**
     * An example board to store the current game state.
     * <p>
     * Note: depending on your game, you might want to change this from 'int' to String or something?
     */
    public int[][] map;

    /**
     * exit position x and y
     */
    public int exitx;
    public int exity;

    /**
     * result of players actions
     */
    public String actionResult;


    /** numpad arrow direction
     *
     * @param value
     */
    public void setDirectionValue(int value) {
        this.directionValue = value;
    }
    private int directionValue;

    /**
     * Creates a square game board.
     *
     * @param size the width and height.
     */
    public GameEngine(int size, int difficulty) {

        map = new int[size][size];
        System.out.printf("The size of map is %d * %d\n", this.getSize(), this.getSize());
        if(difficulty > 10){
            stats.setDifficultyLevel(10);
        }
        else {
            stats.setDifficultyLevel(difficulty);

        }
        this.GenerateEntities(stats.getDifficultyLevel());

        System.out.printf("Coins: %d \n", this.stats.getCoinCount());
        System.out.printf("Stamina Left: %d\n", this.stats.getStamina());
        System.out.println(Arrays.deepToString(map)
                .replace("], ", "]\n")
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }
    /**
     * The size of the current game.
     *
     * @return this is both the width and the height.
     */
    public int getSize() {
        return map.length;
    }

    /**
     * Generates Map Entities/Populate Map
     * @param difficulty
     */
    public void GenerateEntities(int difficulty) {
        Random rand = new Random();
        stats.setDifficultyLevel(difficulty);
        //exit position
        int randx = rand.nextInt(map.length - 1);
        int randy = rand.nextInt(map.length);
        map[randx][randy] = 9;
        exitx = randx;
        exity = randy;
        //created items
        int coinsCreated = 0;
        int trapsCreated = 0;
        int applesCreated = 0;

        //Player position
        map[9][0] = 8;

        //Populate map
        while(true){
            int coins = 2;
            int traps = 3;
            int apples = 1;
            int randValueX = rand.nextInt(map.length - 1);
            int randValueY = rand.nextInt(map.length);
            if(coinsCreated < stats.getMaxCoinSpawnAmount() && map[randValueX][randValueY] == 0) {
                map[randValueX][randValueY] = coins;
                coinsCreated += 1;
            }
            else if(trapsCreated < stats.getDifficultyLevel() && map[randValueX][randValueY] == 0){
                map[randValueX][randValueY] = traps;
                trapsCreated += 1;
            }
            else if(applesCreated < stats.getAppleAmount() && map[randValueX][randValueY] == 0) {
                map[randValueX][randValueY] = apples;
                applesCreated += 1;
            }
            else if(applesCreated == stats.getAppleAmount() && trapsCreated == stats.getDifficultyLevel() && coinsCreated == stats.getMaxCoinSpawnAmount()){
                break;
            }
        }
        //produce map info
        System.out.println(stats.getTrapPositions());
        System.out.printf("There are %d traps on the map \n", stats.getDifficultyLevel());
        System.out.printf("There are %d Coins on the map \n", stats.getMaxCoinSpawnAmount());
        System.out.printf("There are %d apples on the map \n", stats.getAppleAmount());


    }

    /**
     * Checks the players state, such as position in the array and values of stats.
     */
    private void CheckState(){

        if(stats.getLastPositionValue() == 2){
            actionResult = "Moneeeeey!";
            stats.addToCoins(1);

        }
        else if(stats.getLastPositionValue() == 1){
            actionResult = "Nom! Nom! Apple!";
            stats.increaseStamina(4);
        }
        else if(stats.getLastPositionValue() == 3){
            actionResult = "TRAPPED!";
            System.out.println(actionResult);
            stats.removeFromCoins(1);
        }
        else if(stats.getLastPositionValue() == 0){
            actionResult = "Nothing Here..";
            System.out.println(actionResult);
        }
        else if(stats.getLastPositionValue() == 9)
        {
            actionResult = "Winner!";
            System.out.println(actionResult);
        }
        if(stats.getCoinCount() < 0){
            actionResult = "No coins to pay the troll!";
            System.out.println(actionResult);
            System.out.println("\n !GAME OVER!");
            System.exit(0);
        }
        if(stats.getStamina() <= 0){
            actionResult = "No Stamina";
            System.out.println(actionResult + "\n !GAME OVER!");
            System.exit(0);
        }
    }

    /**
     * Moves the player based on the numpad arrow keys (4,8,5,2)
     * tries to receive a valid input, otherwise throws exception
     */
    public void MovePosition() {

        System.out.println(directionValue);
        try {
            if (directionValue == 8) {
                moveUp();

            } else if (directionValue == 2) {
                moveDown();
            } else if (directionValue == 6) {
                moveRight();
            } else if (directionValue == 4) {
                moveLeft();
            } else {
                System.out.println("Wrong Input.. Try Again..  (8 = up, 4 = left, 6 = right, 2 = down)");
                }
            stats.reduceStamina(1);
            System.out.println(Arrays.deepToString(map)
                    .replace("], ", "]\n")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", ""));
        } catch (Exception error) {
            System.out.println("Wrong Direction.. Try Again");
            System.out.println(error);
            }
        System.out.printf("Coins: %d \n", this.stats.getCoinCount());
        System.out.printf("Stamina Left: %d\n", this.stats.getStamina());
        CheckState();
        //return q;
    }

    /**
     * Moves the player upwards in the array
     */
    public void moveUp() {
        try {
            x:
            for (int i = 0; i < map.length; ++i) {
                y:
                for (int j = 0; j < map.length; ++j) {
                    if (map[i][j] == 8) {
                        if (map[i-1][j] < map.length || map[i-1][j] > map.length) {
                            stats.setLastPositionValue(map[i - 1][j]);
                            if (map[i][j] == map[exitx][exity]) {
                                map[i][j] = 9;
                            }else if(stats.getPreValue() == 3){
                                map[i][j] = stats.getPreValue();
                                stats.setPreValue(0);
                            }
                            else {
                                map[i][j] = 0;
                            }
                            stats.setPreValue(map[i - 1][j]);
                            map[i - 1][j] = 8;
                            break x;

                        }
                    }
                }
            }
        }
        catch (Exception error){
            System.out.println("Wrong Direction.. Try Again");
            System.out.println(error);
            }
    }
    /**
     * Moves the player downward in the array
     */
    public void moveDown(){
        try {
            x:
            for (int i = 0; i < map.length; ++i) {
                y:
                for (int j = 0; j < map.length; ++j) {
                    if (map[i][j] == 8) {
                        if (map[i + 1][j] < map.length || map[i + 1][j] > map.length) {
                            stats.setLastPositionValue(map[i + 1][j]);
                            if (map[i][j] == map[exitx][exity]) {
                                map[i][j] = 9;
                            }
                            else if(stats.getPreValue() == 3){
                                map[i][j] = stats.getPreValue();
                                stats.setPreValue(0);
                            }
                            else {
                                map[i][j] = 0;
                            }
                            stats.setPreValue(map[i + 1][j]);
                            map[i + 1][j] = 8;
                            break x;
                           }
                    }


                }
            }
        }
        catch (Exception e){
            System.out.println("Wrong Direction.. Try Again");
            System.out.println(e);
          }
    }
    /**
     * Moves the player right in the array
     */
    public void moveRight() {
        try {
            x:
            for (int i = 0; i < map.length; ++i) {
                y:
                for (int j = 0; j < map.length; ++j) {
                    if (map[i][j] == 8) {
                        if (map[i][j + 1] < map.length || map[i][j + 1] > map.length) {
                            stats.setLastPositionValue(map[i][j + 1]);
                            if (map[i][j] == map[exitx][exity]) {
                                map[i][j] = 9;
                            } else if (stats.getPreValue() == 3) {
                                map[i][j] = stats.getPreValue();
                                stats.setPreValue(0);
                            } else {
                                map[i][j] = 0;
                            }
                            stats.setPreValue(map[i][j + 1]);
                            map[i][j + 1] = 8;
                            break x;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Wrong Direction.. Try Again");
            System.out.println(e);
           }
    }
    /**
     * Moves the player left in the array
     */
    public void moveLeft() {
        try {
            x:
            for (int i = 0; i < map.length; ++i) {
                y:
                for (int j = 0; j < map.length; ++j) {
                    if (map[i][j] == 8) {
                        if (map[i][j - 1] < map.length || map[i][j - 1] > map.length) {
                            stats.setLastPositionValue(map[i][j - 1]);
                            if (map[i][j] == map[exitx][exity]) {
                                map[i][j] = 9;
                            }else if(stats.getPreValue() == 3){
                                map[i][j] = stats.getPreValue();
                                stats.setPreValue(0);
                            }
                            else {
                                map[i][j] = 0;
                            }
                            stats.setPreValue(map[i][j - 1]);
                            map[i][j - 1] = 8;
                            break x;
                            }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Wrong Direction.. Try Again");
            System.out.println(e);
           }
    }
}
