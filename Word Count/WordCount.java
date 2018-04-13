//
//  Name:       Trinh, Michael
//  Homework:   1
//  Due:        October 25,2017
//  Course:     cs-241-02-f17
//
//  Description:    
//              Implement the ADT dictionary using a BST and use it as a 
//                  word count program.
//

import TreePackage.Dictionary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
public class WordCount {
    public static void main(String[] args) { // arg should be filename
        System.out.println("M. Trinh's Word Count \n");
        Dictionary<String,Integer> WordCount = new Dictionary();
        // make a dictionary
        
        if(args.length == 0) { // checks if filename is entered
            System.out.println("A filename of .txt extension must be entered");
            System.exit(1);
        } // end if
        
        Scanner data = new Scanner(System.in);
        try {
                data = new Scanner(new File(args[0]));
        }
            catch (FileNotFoundException e) { // exception if file not found 
                System.out.println("The file hasn't been found.");
            } // end try-catch 
        
        // make all data lowercase so that uppercase are counted
        while (data.hasNext()) { // keeps going until end of file
            String nextword = data.next();
            nextword = nextword.toLowerCase(); 
            // convert to lowercase so uppercase is counted
            if (WordCount.contains(nextword)) {
                WordCount.add(nextword,WordCount.getValue(nextword) + 1);                    
            } // adds word already indictionary and adds its own count by 1
            else { 
                WordCount.add(nextword ,1);
                // adds new word and count of 1 if not in dictionary yet
            } // end if
        } // end while
        
        Iterator<String> textIterator = WordCount.getKeyIterator();
        while(textIterator.hasNext()) { // keeps going until end of dictionary
            String nextkey = textIterator.next();
            System.out.println(nextkey + ", " + WordCount.getValue(nextkey));        
        } // end while
    } // end main  
} // end WordCount
