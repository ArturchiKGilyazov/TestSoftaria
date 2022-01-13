import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class Main {


    public static void main(String[] args) throws IOException{
//        Не совсем понял что брать за значение в HashMap. Взял за строку, потому что html можно представить строкой.
        HashMap<URL, StringBuilder> yesterday = new HashMap<>();
        HashMap<URL, StringBuilder> today = new HashMap<>();

//        добавление страниц в хешмапы
        new CreatorHashMap(yesterday, today);

//        поиск нужных нам страничек
        FinderWebPage finderWebPage = new FinderWebPage(yesterday, today);
        Set<URL> checkAddingWebPage = finderWebPage.checkAddingWebPage();
        Set<URL> checkDeleteWebPage = finderWebPage.checkDeletingWebPage();
        Set<URL> checkChangeWebPage = finderWebPage.checkChangeWebPage();

//        вывод нужного сообщения
        WritterMessage writterMessage = new WritterMessage(checkAddingWebPage, checkDeleteWebPage,
                checkChangeWebPage);
        writterMessage.printInConsole();
        writterMessage.printInFile();
    }
}
