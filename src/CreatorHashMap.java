import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

//класс для добавление страниц в хешмапы
public class CreatorHashMap {
    HashMap<URL, StringBuilder> yesterday;
    HashMap<URL, StringBuilder> today;
    public CreatorHashMap(HashMap<URL, StringBuilder> yesterday, HashMap<URL, StringBuilder> today) throws IOException {
        this.yesterday = yesterday;
        this.today = today;
        createTables();
    }

//    Создание табличек
    private void createTables() throws IOException {
        createYesterday();
        createToday();
    }

//     Создание таблички вчерашних состояний страничек
    private void createYesterday() throws IOException {
        addInHashMapByPath(yesterday, "directorywithpage/yesterdayDelete1.html");
        addInHashMapByPath(yesterday, "directorywithpage/yesterdayDelete2.html");
        addInHashMapByPath(yesterday, "directorywithpage/yesterdayDelete3.html");
        addInHashMapByPath(yesterday, "directorywithpage/permanent.html");
        addInHashMapByURL(yesterday, new URL("https://time.is/"));
    }

//    создание таблички сегодняшних состояний страничек
    private void createToday() throws IOException {
        addInHashMapByPath(today, "directorywithpage/todayAdding1.html");
        addInHashMapByPath(today, "directorywithpage/todayAdding2.html");
        addInHashMapByPath(today, "directorywithpage/todayAdding3.html");
        addInHashMapByPath(today, "directorywithpage/permanent.html");
        addInHashMapByURL(today, new URL("https://time.is/"));
    }

//    метод, который добавляет в хешмапу страничку по URL'у
    private static void addInHashMapByURL(HashMap<URL, StringBuilder> hashMap, URL url){
        hashMap.put(url, readFromURL(url));
    }

//    метод, который добавляет в хешмапу страничку из файла
    private static void addInHashMapByPath(HashMap<URL, StringBuilder> hashMap, String path) throws MalformedURLException {
        File file = new File(path);
        URI uri = file.toURI();
        hashMap.put(uri.toURL(), readFromURL(uri.toURL()));
    }

//    метод, позволяющий прочитать страничку по Url'у
    private static StringBuilder readFromURL(URL url){
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb;
    }

}
