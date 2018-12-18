package day13;

public class Cart {

    int row;
    int column;
    char nextJunctionMove='L';
    int oldX;
    int oldY;
    boolean processedInthispass=false;
   char symbol;
   boolean collided=false;

   /* @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cart){
            Cart anotherCart = (Cart)obj;
            if(anotherCart.row == this.row && anotherCart.column== this.column )
                return true;
        }
        return false;
    }
    @Override
    public int hashCode(){

        int prime = 31;
        int hashcode = 1;
       // hashcode = row.hashCode()+"-".hashCode()+column.hashCode();
        hashcode = prime * hashcode + row;
        hashcode = prime * hashcode + column;

        return hashcode;
    }*/

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