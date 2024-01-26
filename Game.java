public class Game {
    private String gameName;
    private String gameImagePath;
    public Game(String gameName, String gameImagePath){
        this.gameName = gameName;
        this.gameImagePath = gameImagePath;
    }

    public String getGameName(){
        return  gameName;
    }

    public String getGameImagePath(){
        return gameImagePath;
    }


}
