package sample;

// Import the classes for file handling
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

// Import the IOException class to handle errors
import java.io.IOException;



public class HighScoreManaging {

 static File file =new File("HGHSCORE1.txt");
// static FileWriter fW;

//    static void creatingFile() {
////        try {
////            //creating instance of FileWriter to write into the file
////             fW = new FileWriter(file);
////        }
////        catch(IOException exception){
////            System.out.println("Error");
////        }
//    }

    static void WritingToFile(int highscore) {
        try
        {

            FileWriter fW=new FileWriter(file);

            //storing highscore in file
            fW.write(highscore);

            //closing file
            fW.close();

        }
        catch (IOException exception)
        {
            System.out.println("Error");
        }
    }

   static int ReadingFromFile(){

        //variable to store data from file
        int i=0;

        try
        {

            //creating instance of FileReader to read from the file
            FileReader fR=new FileReader(file);


                //reading from file
                i = fR.read();

                //closing file
                fR.close();

        }

        catch(IOException exception)
        {
            System.out.println("Error");
        }

        return i;

    }


//    public static void main(String[] args){
//
//
//        HighScoreManaging h=new HighScoreManaging();
//
//        System.out.println(h.ReadingFromFile());
//
//    }

}
