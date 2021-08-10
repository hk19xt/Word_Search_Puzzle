package Assign_1;

import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions


/** This class ...
  *
  * @author Hyejin Kim
  * @student# 6823116
  * @version 1.0 (1/30/2020)                                                        */

public class WordSearch {
  
  
  // instance variables
  private ASCIIDataFile pData;
  private ASCIIDisplayer display;
  private char[][] puzzle;
  private String word;
  
  
  /** This constructor ...                                                     */
  
  public WordSearch () {
    
    // local variables
    pData = new ASCIIDataFile();
    display = new ASCIIDisplayer(35,70);
    loadPuzzle();
    displayPuzzle();
    
    for( ; ; ){
      
      word = pData.readString();
      if(pData.isEOF())break;
      findWords();
    }
    pData.close();
    display.close();
  }// constructor
  
  
  // methods
  private void loadPuzzle(){
    
    puzzle = new char[18][18];
    pData.readLine();
    for(int i=0; i<puzzle.length; i++){
      for(int j=0;j<puzzle[i].length;j++){
        puzzle[i][j]=pData.readChar();
        
      }
    }
  }//loadPuzzle
  
  private void displayPuzzle(){
    
    for(int i=0; i<puzzle.length; i++){
      for(int j=0;j<puzzle[i].length;j++){
        display.writeChar(puzzle[i][j]);
      }
      display.newLine();
    }
    display.newLine();
  }//displayPuzzle
  
  private boolean searchDown(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int index = x; 
    for(char letter:charArray){
      if(!checkBoundary(index, y)) //Check the boundary index x and y
        return false;
      if(puzzle[index][y]!=letter){ //Go through the index (vertical down flow) line
        return false;
    }
      index++;                      
    }  
    return true;
  }//searchDown
  
  private boolean searchUp(int x, int y, String word){
    
     char[]charArray = word.toCharArray();
     
    int index = x;
    for(char letter: charArray){
      if(!checkBoundary(index, y)) //Check the boundary index x and y
        return false;
      if(puzzle[index][y]!=letter){ //Go through the index (vertical up flow) line
        return false;
      }
       index--;
    }  
    return true;
  }//searchUp
  
  private boolean searchLeft(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int index = y;
    for(char letter: charArray){
      if(!checkBoundary(x, index)) //Check the boundary x and index y
        return false;
      if(puzzle[x][index]!=letter) //Go through the index (horizontal right->left) line
        return false;
      index--;
    }  
    return true;
  }//searchLeft
  
  private boolean searchRight(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int index = y;
    for(char letter: charArray){
      if(!checkBoundary(x,index)) //Check the boundary x and index y
        return false;
      if(puzzle[x][index]!=letter){ //Go through the index (horizontal left->right) line
        return false;
    }
      index++;
    }  
    return true;
  }//searchRight
  
  private boolean searchRightDown(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int indexX = x, indexY = y;
    for(char letter: charArray){
      if(!checkBoundary(indexX, indexY)) //Check the boundary index x and index y
        return false;
      if(puzzle[indexX][indexY]!=letter) //Go through the index (diagonally right down) line
        return false;
      indexX++;
      indexY++;
    }  
    return true;
  }//searchRightDown
  
  private boolean searchRightUp(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int indexX = x, indexY=y;
    for(char letter: charArray){
      if(!checkBoundary(indexX, indexY)) //Check the boundary index x and index y
        return false;
      if(puzzle[indexX][indexY]!=letter) //Go through the index (diagonally right up) line
        return false;
      indexX--;
      indexY++; 
    }  
    return true;
  }//searchRightUp
  
  private boolean searchLeftUp(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int indexX = x, indexY = y;
    for(char letter: charArray){
      if(!checkBoundary(indexX, indexY)) //Check the boundary index x and index y
        return false;
      if(puzzle[indexX][indexY]!=letter) //Go through the index (diagonally left up) line
        return false;
      indexX--;
      indexY--;
    }  
    return true;
  }//searchLeftUp
  
  private boolean searchLeftDown(int x, int y, String word){
    
    char[]charArray = word.toCharArray();
    
    int indexX = x, indexY = y;
    for(char letter: charArray){
      if(!checkBoundary(indexX, indexY)) //Check the boundary index x and index y
        return false;
      if(puzzle[indexX][indexY]!=letter) //Go through the index (diagonally left down) line
        return false;
      indexX++;
      indexY--;
    }  
    return true;
  }//searchLeftDown
  
  
  /* In total 8 directions to find words in puzzle.
   * 1) search left 
     2) search down 
     3) search right 
     4) search up
     5) search left up (diagonally)
     6) search right up (diagonally)
     7) search left down (diagonally)
     8) search right down (diagonally)*/
  private void findWords(){

    for(int x=0;x<puzzle.length;x++){
      for(int y=0;y<puzzle[0].length;y++){
//        if(x==17 || y==17)
//          System.out.println("x="+x+" y="+y);
        if(searchLeft(x,y,word)){
          display.writeLine(word + " found (left) at (" + x +" ," + y + ")");} 
        
        if(searchDown(x,y,word)){
          display.writeLine(word + " found (down) at (" + x +" ," + y + ")");}  
       
        if(searchRight(x,y,word)){
          display.writeLine(word + " found (right) at (" + x +" ," + y + ")");}
       
        if(searchUp(x,y,word)){
          display.writeLine(word + " found (up) at (" + x +" ," + y + ")"); } 
       
        if(searchLeftUp(x,y,word)){
          display.writeLine(word + " found (leftup) at (" + x +" ," + y + ")");}        
        
        if(searchRightUp(x,y,word)){
          display.writeLine(word + " found (rightup) at (" + x +" ," + y + ")");}        
        
        if(searchLeftDown(x,y,word)){
          display.writeLine(word + " found (leftdown) at (" + x +" ," + y + ")");}        
       
        if(searchRightDown(x,y,word)){
          display.writeLine(word + " found (rightdown) at (" + x +" ," + y + ")");}
          
        }
        
      }
      
    }//findWords
    
  private boolean checkBoundary(int x, int y){
    if(x<0 || y<0 || x>=puzzle.length || y >= puzzle[0].length){
      return false;}
    return true;
  }
  
  public static void main ( String[] args ) { WordSearch c = new WordSearch(); };
  
  
} // <WordSearch>
