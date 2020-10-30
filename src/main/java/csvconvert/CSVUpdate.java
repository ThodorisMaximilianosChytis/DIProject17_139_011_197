package csvconvert;


import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.Writer;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.File;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class CSVUpdate {

    private File inputFile;
    private List<String[]> csvBody;
    private int nofrows;

    public CSVUpdate(String filetoUpdate)throws IOException, CsvException{
        inputFile = new File(filetoUpdate);
        this.Readfile();

    }

    private void Readfile()throws IOException, CsvException{
        CSVReader reader = new CSVReader(new FileReader(this.inputFile));
        this.csvBody = reader.readAll();
        System.out.println(this.csvBody.size());
        this.nofrows=(this.csvBody.size());
        reader.close();
    }

    private void Writefile()throws IOException, CsvException{
// Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(this.inputFile));
        writer.writeAll(this.csvBody);
        writer.flush();
        writer.close();
    }

    public void update(String replace, int row, int col) throws IOException, CsvException {
        this.csvBody.get(row)[col] = replace;
        this.Writefile();
    }

    public int getNofrows() {
        return nofrows;
    }
}
