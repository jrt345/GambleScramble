package game.games.scratchoff;

public class Ticket {

    private int multiplier = 1;
    private int[] luckyNumbers = new int[3];
    private int[][] gridNumbers = new int[3][3];

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int[] getLuckyNumbers() {
        return luckyNumbers;
    }

    public void setLuckyNumbers(int[] luckyNumbers) {
        this.luckyNumbers = luckyNumbers;
    }

    public int[][] getGridNumbers() {
        return gridNumbers;
    }

    public void setGridNumbers(int[][] gridNumbers) {
        this.gridNumbers = gridNumbers;
    }
}
