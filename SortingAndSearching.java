import java.util.ArrayList;

public class SortingAndSearching {
    public static int linearSearch(String gameName, ArrayList<Game> gameList){
        //gameName'de dönebilir game objesi de, duruma göre
        for(int i = 0; i < gameList.size(); i ++){
            if(gameName.equals(gameList.get(i))){
                return i;
            }
        }
        return -1;
    }

    public static String binarySearch(String gameName, ArrayList<Game> gameList){  //bunun bubble sort'unu sanırım methpdu çağırdığımız yerde yapacağız
        // bunu recursive yazalım
        return null;
    }

    public static void bubbleSort(ArrayList<Game> gameList){  //serpil abla double demişti ama bunu kastetti sanırım

    }

    public static void selectionSort(ArrayList<Game> gameList){

    }

    public static void insertionSort(ArrayList<Game> gameList){

    }
}
