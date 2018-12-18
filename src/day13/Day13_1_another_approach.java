package day13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day13_1_another_approach {


    public static void main(String[] args) {
        List<String> input = new ArrayList<String>();
        try {
            input = readData();
        } catch (Exception e) {
        }

        List<String> inputWithoutCarts = new ArrayList<String>();
        try {
            inputWithoutCarts = readData1();
        } catch (Exception e) {
        }

        //int noOfCarts=2;
        int noOfRows = 150;
        int noOfColums = 150;
        char[][] pathsWithoutCarts = new char[noOfRows][noOfColums];

        List<Cart> carts = new ArrayList<Cart>();

        int i = 0;
        int index = -2;
        for (String s : input) {
            index = -2;
            while (index != -1) {
                index = s.indexOf('<', index + 1);
                if (index != -1) {
                    Cart c = new Cart();
                    c.row = i;
                    c.column = index;
                    c.symbol='<';
                    carts.add(c);
                }
            }
            index = -2;
            while (index != -1) {
                index = s.indexOf('>', index + 1);
                if (index != -1) {
                    Cart c = new Cart();
                    c.row = i;
                    c.column = index;
                    c.symbol='>';
                    carts.add(c);
                }
            }
            index = -2;
            while (index != -1) {
                index = s.indexOf('^', index + 1);
                if (index != -1) {
                    Cart c = new Cart();
                    c.row = i;
                    c.column = index;
                    c.symbol='^';
                    carts.add(c);
                    }
            }
            index = -2;
            while (index != -1) {
                index = s.indexOf('v', index + 1);
                if (index != -1) {
                    Cart c = new Cart();
                    c.row = i;
                    c.column = index;
                    c.symbol='v';
                    carts.add(c);
                }
            }



            i++;
        }

        i = 0;
        for (String s : inputWithoutCarts) {
            int j = 0;
            while (j < s.toCharArray().length) {
                pathsWithoutCarts[i][j] = s.toCharArray()[j];
                j++;
            }

            for (; j < noOfColums; j++) {
                pathsWithoutCarts[i][j] = ' ';
            }

            i++;
        }

        boolean collisionOccurred = false;
        int tick = 0;
        while (!collisionOccurred) {
            tick++;
            carts.sort(new CartComparator());
            System.out.println("Tick = "+tick);



            for (Cart cart : carts) {
                if(!cart.collided) {
                    System.out.println("row=" + cart.row + " col=" + cart.column + " symb=" + cart.symbol);
                    cart.symbol = decidenextmove(cart, pathsWithoutCarts);
                    collisionOccurred = checkcollission(carts, cart);
                    if (collisionOccurred) {
                        System.out.println("Collision has occured");
                    }
                }
            }


            carts.removeIf(c->c.collided);
            if(carts.size()==1)
                System.out.println();
            else
                collisionOccurred=false;

        }
        System.out.println();

}

    public static boolean checkcollission(List<Cart> carts, Cart cart) {

        boolean collisionOccurred = false;

        for (Cart cc : carts)
            if(!cc.equals(cart) && !cc.collided) {
                if (cc.row == cart.row && cc.column == cart.column) {
                    collisionOccurred = true;
                    cc.collided=true;
                    cart.collided=true;
                    System.out.println("Collision= " + cc.row + "-" + cc.column);

                    break;
                }
            }
          return collisionOccurred;
    }

    public static char setNextJunctionMove(char nextJunctionMove) {
        char nownextmove = ' ';
        switch (nextJunctionMove) {
            case 'L':
                nownextmove = 'S';
                break;
            case 'S':
                nownextmove = 'R';
                break;
            case 'R':
                nownextmove = 'L';
                break;
            default:
                System.out.println("ERROR IN SETTING NEXT JUNCTION MOVE");
                break;
        }
        return nownextmove;
    }

    public static char decidenextmove(Cart cart, char[][] tracks) {
        char current = cart.symbol;

        char nextMove = ' ';
        char next;
        switch (current) {
            case '>':
                 next=tracks[cart.row][cart.column+1];
                switch (next) {
                    case '-':
                        nextMove = '>';
                        break;
                    case '\\':
                        nextMove = 'v';
                        break;
                    case '/':
                        nextMove = '^';
                        break;
                    case '+':
                        char nextJunctionMove= cart.nextJunctionMove;

                        switch (nextJunctionMove) {
                            case 'L':
                                nextMove = '^';
                                break;
                            case 'R':
                                nextMove = 'v';
                                break;
                            case 'S':
                                nextMove = '>';
                                break;
                            default:
                                System.out.println("ERROR in JUNCTION MOVE");
                                break;
                        }
                        cart.nextJunctionMove= setNextJunctionMove(nextJunctionMove);
                        break;
                    default:
                        System.out.println("ERROR in PATH");
                        break;
                }
                cart.column=cart.column+1;
                break;
            case '<':
                next=tracks[cart.row][cart.column-1];
                switch (next) {
                    case '-':
                        nextMove = '<';
                        break;
                    case '\\':
                        nextMove = '^';
                        break;
                    case '/':
                        nextMove = 'v';
                        break;
                    case '+':
                        char nextJunctionMove= cart.nextJunctionMove;

                        switch (nextJunctionMove) {
                            case 'L':
                                nextMove = 'v';
                                break;
                            case 'R':
                                nextMove = '^';
                                break;
                            case 'S':
                                nextMove = '<';
                                break;
                            default:
                                System.out.println("ERROR in JUNCTION MOVE");
                                break;
                        }
                        cart.nextJunctionMove= setNextJunctionMove(nextJunctionMove);
                        break;
                    default:
                        System.out.println("ERROR in PATH");
                        break;
                }
                cart.column=cart.column-1;
                break;
            case '^':
                next=tracks[cart.row-1][cart.column];
                switch (next) {
                    case '|':
                        nextMove = '^';
                        break;
                    case '\\':
                        nextMove = '<';
                        break;
                    case '/':
                        nextMove = '>';
                        break;
                    case '+':
                        char nextJunctionMove= cart.nextJunctionMove;

                        switch (nextJunctionMove) {
                            case 'L':
                                nextMove = '<';
                                break;
                            case 'R':
                                nextMove = '>';
                                break;
                            case 'S':
                                nextMove = '^';
                                break;
                            default:
                                System.out.println("ERROR in JUNCTION MOVE");
                                break;
                        }
                        cart.nextJunctionMove= setNextJunctionMove(nextJunctionMove);
                        break;
                    default:
                        System.out.println("ERROR in PATH");
                        break;
                }
                cart.row=cart.row-1;
                break;
            case 'v':
                next=tracks[cart.row+1][cart.column];
                switch (next) {
                    case '|':
                        nextMove = 'v';
                        break;
                    case '\\':
                        nextMove = '>';
                        break;
                    case '/':
                        nextMove = '<';
                        break;
                    case '+':
                        char nextJunctionMove= cart.nextJunctionMove;

                        switch (nextJunctionMove) {
                            case 'L':
                                nextMove = '>';
                                break;
                            case 'R':
                                nextMove = '<';
                                break;
                            case 'S':
                                nextMove = 'v';
                                break;
                            default:
                                System.out.println("ERROR in JUNCTION MOVE");
                                break;
                        }
                        cart.nextJunctionMove= setNextJunctionMove(nextJunctionMove);
                        break;
                    default:
                        System.out.println("ERROR in PATH");
                        break;
                }
                cart.row=cart.row+1;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        return nextMove;
    }

    public static List<String> readData() throws IOException {
        int count = 0;
        String file = "C:\\adventOfCode_2018\\Resources\\input13_big.csv";
        List<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }

        return content;
    }

    public static List<String> readData1() throws IOException {
        int count = 0;

        String file = "C:\\adventOfCode_2018\\Resources\\input13_big_withoutCarts.csv";
        List<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }

        return content;
    }
}
