import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Computer extends Thread {
    String name;
    
    // Method to read a text file and add the content into a TextFile object
    public static TextFile ReadAFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder(); // Object to append the content of the file
        
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return new TextFile(contentBuilder.toString());
    }

    // Method to create a print job
    PrintJob createPrintJob(TextFile textFile) throws TypeNotSupportedException {
        return new PrintJob(textFile);
    }

    // Method to submit a print job
    public synchronized void  submitPrintJob(PrintJob printJob, SharedQueue sharedQueue) throws SharedQueueFullException{
        sharedQueue.addJob(printJob);
    }
}

