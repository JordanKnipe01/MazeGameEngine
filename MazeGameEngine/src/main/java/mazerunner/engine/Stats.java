package mazerunner.engine;
import java.util.ArrayList;

public class Stats {
    /**
     * An example board to store the current game state.
     * <p>
     * Note: depending on your game, you might want to change this from 'int' to String or something?
     */
    public int[][] map;

    /**
     * Coin Amount
     */
    private int countAmount = 0;

    /**
     * max amount of coins on the map
     */
    private int maxCoinSpawnAmount = 5;
    /**
     * returns max coin spawn amount
     * @return
     */
    public int getMaxCoinSpawnAmount(){ return maxCoinSpawnAmount; }

    /** add to current coin amount
     *
     * @param addAmount
     */
    public void addToCoins(int addAmount){
        countAmount += addAmount;
        System.out.printf("%d" + " Coins \n", getCoinCount());
    }

    /** remove value from current coins
     *
     * @param removeAmount
     */
    public void removeFromCoins(int removeAmount){
        countAmount -= removeAmount;
        System.out.printf("%d" + " Coins \n", getCoinCount());
    }

    /**
     * get current coin amount
     * @return
     */
    public int getCoinCount(){
        return countAmount;
    }


    /**
     * trap positions on the map
     */
    private ArrayList<Integer[][]> trapPositions = new ArrayList<Integer[][]>();

    /**
     * get trap positions in the array
     * @return
     */
    public ArrayList<Integer[][]> getTrapPositions(){
        return trapPositions;
    }

    /**
     * difficulty level
     */
    private int difficultyLevel = 5;

    /** get difficulty level
     *
     * @return
     */
    public int getDifficultyLevel(){return difficultyLevel;}

    /**
     * set difficulty to parameter value
     * @param difficulty
     */
    public void setDifficultyLevel(int difficulty) {difficultyLevel = difficulty;}

    /**
     * amount of apples on the map
     */
    private int appleAmount = 10;

    /** return appleAmount
     *
     * @return
     */
    public int getAppleAmount() {return appleAmount - getDifficultyLevel();}
    /**
     * returns stamina value
     * @return stamina
     */
        public int getStamina(){ return stamina; }
    /**
     * Stamina Amount
     */
    private int stamina = 12;

    /**
     * reduces stamina by param amount
     * @param reduceValueBy
     */
    public void reduceStamina(int reduceValueBy){
        stamina -= reduceValueBy;
    }

    /**
     * increases stamina by param amount
     * @param increaseValueBy
     */
    public void increaseStamina(int increaseValueBy){
        stamina += increaseValueBy;
    }

    /** value of the cell before player touches it
     *
     */
    private int preValue;

    /**
     * get cell moving to
     * @return
     */
    public int getPreValue() {
        return preValue;
    }

    /** set cell moving to
     *
     * @param preValue
     */
    public void setPreValue(int preValue) {
        this.preValue = preValue;
    }

    /**
     * Last Item Collided With
     */
    private int lastPositionValue;

    /**
     * get last player position
     * @return
     */
    public int getLastPositionValue() {
        return lastPositionValue;
    }

    /**
     * set last player position
     * @param lastPositionValue
     */
    public void setLastPositionValue(int lastPositionValue) {
        this.lastPositionValue = lastPositionValue;
    }

}
