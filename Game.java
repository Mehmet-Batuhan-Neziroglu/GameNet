public class Game {
    private String gameName;
    private String gameImagePath;
    private String gameType;
    private String gameRate;
    public Game(String gameName, String gameImagePath){
        this.gameName = gameName;
        this.gameImagePath = gameImagePath;
    }

    public Game(String gameName, String gameType, String gameRate){
        this.gameName = gameName;
        this.gameType = gameType;
        this.gameRate = gameRate;
    }

    public String getGameName(){
        return  gameName;
    }

    public String getGameImagePath(){
        return gameImagePath;
    }

    public String getGameType() {
        return gameType;
    }

    public String getGameRate() {
        return gameRate;
    }
}
