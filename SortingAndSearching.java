import java.util.ArrayList;

public class SortingAndSearching {

    public static void linearSearch(String searchedText){
        Navigator.gamesList.clear();
        for(int i = 0; i < Database.getUsersGames(Navigator.getUser().getUserID()).size(); i ++){
            if(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName().toLowerCase().contains(searchedText.toLowerCase())){
                Navigator.gamesList.add(Database.getUsersGames(Navigator.getUser().getUserID()).get(i));
                System.out.println(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName());
            }
        }

    }

    public static void binarySearch(String SearchedText){
        // bunu recursive yazalım
    }

    public static void bubbleSort(){
        //ArrayList<Game> userGameList = Database.getUsersGames(Navigator.getUser().getUserID());
        ArrayList<Game> userGameList = Navigator.gamesList;
        int listSize = userGameList.size();
        Game temp;

        for(int i = 0; i < listSize; i++){
            for(int j = 1; j < (listSize - i); j++){
                if(userGameList.get(j - 1).getGameName().compareTo(userGameList.get(j).getGameName()) > 0){
                    temp = userGameList.get(j - 1);
                    userGameList.set(j - 1, userGameList.get(j));
                    userGameList.set(j, temp);
                }

            }
        }
        //Navigator.gamesList = userGameList;
    }

    public static void selectionSort() {
        ArrayList<Game> userGameList = Navigator.gamesList;
        int listSize = userGameList.size();
        int minIndex = 0;
        Game temp;


        for (int i = 0; i < listSize - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < listSize; j++) {
                if (userGameList.get(j).getGameName().compareTo(userGameList.get(minIndex).getGameName()) < 0) {
                    minIndex = j;
                }
            }

            temp = userGameList.get(minIndex);
            userGameList.set(minIndex, userGameList.get(i));
            userGameList.set(i, temp);
        }
        //Navigator.gamesList = userGameList;
    }

    public static void insertionSort(){
        ArrayList<Game> userGameList = Navigator.gamesList;
        int listSize = userGameList.size();
        Game key;
        int index;

        for (int j = 1; j < listSize; j++) {
            key = userGameList.get(j);
            index = j - 1;
            while ( (index > -1) && ( userGameList.get(index).getGameName().compareTo(key.getGameName()) > 0 ) ) {
                userGameList.set(index + 1, userGameList.get(index));
                index--;
            }
            userGameList.set(index + 1, key);
        }
        //Navigator.gamesList = userGameList;
    }
}
