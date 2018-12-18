package day13;

public class Cart {

    int row;
    int column;
    char nextJunctionMove='L';

   char symbol;
   boolean collided=false;

  

public int compareTo(Cart cart){

        if(row==cart.row){
            if(column>cart.column)
                return 1;
            else if(column<cart.column)
                return -1;
            else
                return 0;
        }else if(row>cart.row)
        {
            return 1;
        }
        else
            return -1;

}




}