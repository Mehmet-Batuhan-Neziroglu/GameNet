import java.util.ArrayList;

public class SortingAndSearching {

    public static void linearSearch(String searchedText) {
        Navigator.gamesList.clear();
        for (int i = 0; i < Database.getUsersGames(Navigator.getUser().getUserID()).size(); i++) {
            if (Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName().toLowerCase().contains(searchedText.toLowerCase())) {
                Navigator.gamesList.add(Database.getUsersGames(Navigator.getUser().getUserID()).get(i));
            }
        }

    }

    //binary çalışmıyor
    public static void binarySearch(String searchedText) {
        Navigator.gamesList.clear();
        ArrayList<Game> userGameList = Database.getUsersGames(Navigator.getUser().getUserID());
        bubbleSort(userGameList);

        int first = 0;
        int last = userGameList.size() - 1;
        boolean didFound = false;

        int mid = (first + last) / 2;

        for(int i = 0; i < userGameList.size() && !didFound; i++) {
            while (first <= last) {
                if (userGameList.get(mid).getGameName().compareTo(searchedText) < 0) {
                    first = mid + 1;
                } else if (userGameList.get(mid).getGameName().equals(searchedText)) {
                    Navigator.gamesList.add(userGameList.get(mid));
                    didFound = true;
                    break;
                } else {
                    last = mid - 1;
                }
                mid = (first + last) / 2;
            }
        }
    }


    public static void bubbleSort(ArrayList<Game> gameArrayList){
        //ArrayList<Game> userGameList = Database.getUsersGames(Navigator.getUser().getUserID());
        int listSize = gameArrayList.size();
        Game temp;

        for(int i = 0; i < listSize; i++){
            for(int j = 1; j < (listSize - i); j++){
                if(gameArrayList.get(j - 1).getGameName().compareTo(gameArrayList.get(j).getGameName()) > 0){
                    temp = gameArrayList.get(j - 1);
                    gameArrayList.set(j - 1, gameArrayList.get(j));
                    gameArrayList.set(j, temp);
                }

            }
        }
        //Navigator.gamesList = userGameList;
    }

    public static void selectionSort(ArrayList<Game> gameArrayList) {
        int listSize = gameArrayList.size();
        int minIndex = 0;
        Game temp;


        for (int i = 0; i < listSize - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < listSize; j++) {
                if (gameArrayList.get(j).getGameName().compareTo(gameArrayList.get(minIndex).getGameName()) < 0) {
                    minIndex = j;
                }
            }

            temp = gameArrayList.get(minIndex);
            gameArrayList.set(minIndex, gameArrayList.get(i));
            gameArrayList.set(i, temp);
        }
        //Navigator.gamesList = userGameList;
    }

    public static void insertionSort(ArrayList<Game> gameArrayList){
        int listSize = gameArrayList.size();
        Game key;
        int index;

        for (int j = 1; j < listSize; j++) {
            key = gameArrayList.get(j);
            index = j - 1;
            while ( (index > -1) && ( gameArrayList.get(index).getGameName().compareTo(key.getGameName()) > 0 ) ) {
                gameArrayList.set(index + 1, gameArrayList.get(index));
                index--;
            }
            gameArrayList.set(index + 1, key);
        }
        //Navigator.gamesList = userGameList;
    }
}
