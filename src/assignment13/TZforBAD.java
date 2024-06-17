package assignment13;

import java.util.ArrayList;

public class TZforBAD {
    public static void main(String[] args){
        //true case
        ArrayList<Integer> array = new ArrayList<>();
        {
            array.add(1);
            array.add(9);
            array.add(14);
            array.add(19);
            array.add(1);
        }

        int num = medianNumber(array);
        System.out.println(num); // 11

    }
    private ArrayList<Integer> fileRead(){
        return null;
    }
    private void maxNumber(){

    }
    //true case , done
    private static Integer medianNumber(ArrayList<Integer> array){
        if(array.size() % 2 == 0){
            int firstNum = array.get((array.size() / 2) - 1);
            int secondNum = array.get(array.size() / 2);
            return (firstNum + secondNum) / 2;
        }
        else{
            return array.get(array.size() / 2);
        }
    }
}
