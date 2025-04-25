package dataProviders;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestData {

    @DataProvider(name = "urls")
    public static Object[][] getUrls() throws IOException {
        return readFromCSV();
    }


    private static Object [][] readFromCSV() throws IOException {
        String[] data;
        List<Object []> urls = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String filePath = "src/test/resources/urls.csv";
        String delimiter = ";";
        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                data = line.split(delimiter);
                urls.add(data);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("Coudn't find csv file located at: %s", filePath));
        } catch (IOException e) {
            throw new IOException("Couldn't read from csv file. Check its contents!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("Couldn't close csv file. Close it manually!");
                }
            }
        }
        return urls.toArray(new Object[0][]);
    }
}
