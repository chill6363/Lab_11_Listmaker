import java.util.Scanner;
import java.util.ArrayList;

public class List {
    private static ArrayList<String> mainList = new ArrayList<>();


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        menuContainer(in);
    }

    private static void menuOptions(){
        System.out.println("Menu Options:\nA - Add an item to the list\nD – Delete an item from the list\nI – Insert an item into the list\nP – Print the list\nQ – Quit the program");
    }

    // Contains loop for menu
    private static void menuContainer(Scanner in){
        boolean isQuit = false;
        do{
            menuOptions();
            String userInput = SafeInput.getNonZeroLenString(in, "Input a Command");
            isQuit = readUserInput(in, userInput);
            System.out.println("The list is now as follows: ");
            System.out.println(mainList);
        }while(!isQuit);
    }

    // Takes user string, reads it, and does 1 of 5 different operations based on the input
    private static boolean readUserInput(Scanner in, String input){
        boolean isValid = true;
        boolean isQuit = false;
        do{
            if(input.equalsIgnoreCase("A")){
                addItem(in);
                isValid = true;
            }
            else if(input.equalsIgnoreCase("D")){
                deleteItem(in);
                isValid = true;
            }
            else if(input.equalsIgnoreCase("I")){
                insertItem(in);
                isValid = true;
            }
            else if(input.equalsIgnoreCase("P")){
                printList(in);
                isValid = true;
            }
            else if(input.equalsIgnoreCase("Q")){
                isQuit = quitList(in);
                isValid = true;
            }
            else{
                System.out.println("Invalid Input");
                input = SafeInput.getNonZeroLenString(in, "Input a Command");
                isValid = false;
            }
        }while(!isValid);
        return isQuit;
    }

    //Menu Functions
    private static void addItem(Scanner in){
        String input = SafeInput.getNonZeroLenString(in, "Enter the item");
        mainList.add(input);
    }
    private static void deleteItem(Scanner in){
        if(mainList.isEmpty()){
            System.out.println("The list is empty. Operation can't be done.");
        }
        else if(mainList.size() == 1){
            if(SafeInput.getYNConfirm(in, "Only one item is in the list. Still remove? (Y/N)")){
                mainList.removeFirst();
            }
        }
        else{
            int val = SafeInput.getRangedInt(in, "Enter the index of the item", 0, mainList.size()-1);
            mainList.remove(val);
        }
    }
    private static void insertItem(Scanner in){
        if(mainList.isEmpty()){
            System.out.println("The list is empty. Using the Add command instead.");
            addItem(in);
        }
        else if(mainList.size() == 1){
            String input = SafeInput.getNonZeroLenString(in, "Enter the item");
            System.out.println("Only one item is in the list.");
            mainList.set(0, input);
        }
        else{
            String input = SafeInput.getNonZeroLenString(in, "Enter the item");
            int val = SafeInput.getRangedInt(in, "Enter the index of the item", 0, mainList.size()-1);
            mainList.set(val, input);
        }
    }
    private static void printList(Scanner in){
        System.out.println(mainList);
    }
    private static boolean quitList(Scanner in){
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
    }
}
