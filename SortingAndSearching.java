import java.util.ArrayList;

public class SortingAndSearching {

    public static void linearSearch(String searchedText){

        Navigator.gamesList.clear();
        //gameName'de dönebilir game objesi de, duruma göre
        for(int i = 0; i < Database.getUsersGames(Navigator.getUser().getUserID()).size(); i ++){
            if(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName().toLowerCase().contains(searchedText.toLowerCase())){
                Navigator.gamesList.add(Database.getUsersGames(Navigator.getUser().getUserID()).get(i));
                System.out.println(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName());
            }
        }

    }

    public static void binarySearch(String SearchedText){  //bunun bubble sort'unu sanırım methpdu çağırdığımız yerde yapacağız
        // bunu recursive yazalım
    }

    public static void bubbleSort(ArrayList<Game> gameList){  //serpil abla double demişti ama bunu kastetti sanırım

    }

    public static void selectionSort(ArrayList<Game> gameList){

    }

    public static void insertionSort(ArrayList<Game> gameList){

    }
}
