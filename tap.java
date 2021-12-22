import java.io.*;
import java.util.*;

public class tap {
  public static void main(String[] args) throws Exception {
	  
	  
	  //For reading streams of characters from text file and pass to java file :  reading hapens here
	  
    FileInputStream fis=new FileInputStream("C:\\Users\\Surabhi\\OneDrive\\Desktop\\input.txt");   
    Scanner sc=new Scanner(fis);
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
	  
	  //spliting occurs whenever this symbol ": " appears
	  // after splitting it stores to array 
    ArrayList<Item> goodies_items = new ArrayList<Item>();
	  
//again when new line encounters in input.txt file, remaining elements appended to array using same split function 
    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.price - b.price; 
      } 
    });

	  
	  //input the number of employees

//print the sorted  (according to the difference) resultant array elemets --- from index 0 till index is equal to no of employee
	  
	  int min_diff = goodies_items.get(goodies_items.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) {
      int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    //writes output on output.txt file

    FileWriter fw = new FileWriter("C:\\Users\\Surabhi\\OneDrive\\Desktop\\output.txt");
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
  }
}




class Item {
  String name;
  int price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}
