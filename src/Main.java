import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws IOException{
//        Не совсем понял что брать за значение в HashMap. Взял за строку, потому что html можно представить строкой.
        HashMap<URL, StringBuilder> yesterday = new HashMap<>();
        HashMap<URL, StringBuilder> today = new HashMap<>();

//        добавление страниц в хешмапы
        new CreatorHashMap(yesterday, today);

        ArrayList<URL> checkAddingWebPage = checkAddingWebPage(yesterday, today);
        ArrayList<URL> checkDeleteWebPage = checkDeletingWebPage(yesterday, today);
        ArrayList<URL> checkChangeWebPage = checkChangeWebPage(yesterday, today);

        printInConsole(checkAddingWebPage, checkDeleteWebPage, checkChangeWebPage);
        printInFile(checkAddingWebPage, checkDeleteWebPage, checkChangeWebPage);
    }

    //метод, записывающий исчезнувшие, появившиеся, изменённые страницы в файл
    private static void printInFile(ArrayList<URL> checkAddingWebPage, ArrayList<URL> checkDeleteWebPage,
                                    ArrayList<URL> checkChangeWebPage) {
        File file = new File("FileMessage.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("Здравствуйте, дорогая и.о. секретаря\n");
            fr.write("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n");
            fr.write("Исчезли следующие страницы:\n");
            for(URL url : checkAddingWebPage){
                fr.write(url.toString() + "\n");
            }
            fr.write("Появились следующие новые страницы:\n");
            for(URL url : checkDeleteWebPage){
                fr.write(url.toString() + "\n");
            }
            fr.write("Изменились следующие страницы:\n");
            for(URL url : checkChangeWebPage){
                fr.write(url.toString() + "\n");
            }
            fr.write("С уважением, автоматизированная система мониторинга.");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    метод, записывающий исчезнувшие, появившиеся, изменённые страницы в консоль
    private static void printInConsole(ArrayList<URL> checkAddingWebPage, ArrayList<URL> checkDeleteWebPage,
                                       ArrayList<URL> checkChangeWebPage) {
        System.out.println("Здравствуйте, дорогая и.о. секретаря");
        System.out.println("За последние сутки во вверенных Вам сайтах произошли следующие изменения:");
        System.out.println("Исчезли следующие страницы:");
        for(URL url : checkAddingWebPage){
            System.out.println(url);
        }
        System.out.println("Появились следующие новые страницы:");
        for(URL url : checkDeleteWebPage){
            System.out.println(url);
        }
        System.out.println("Изменились следующие страницы:");
        for(URL url : checkChangeWebPage){
            System.out.println(url);
        }
        System.out.println("С уважением, автоматизированная система мониторинга.");
    }

//    поиск на исчезнувших страниц
    private static ArrayList<URL> checkDeletingWebPage(HashMap<URL, StringBuilder> yesterday,
                                                       HashMap<URL, StringBuilder> today) {
        ArrayList<URL> deletingURL = new ArrayList<>();
        for(URL url : yesterday.keySet()){
            if(!today.containsKey(url)){
                deletingURL.add(url);
            }
        }
        return deletingURL;
    }


//    поиск добавленных страниц
    private static ArrayList<URL> checkAddingWebPage(HashMap<URL, StringBuilder> yesterday,
                                                     HashMap<URL, StringBuilder> today){
        ArrayList<URL> addedURL = new ArrayList<>();
        for (URL url : today.keySet()){
            if(!yesterday.containsKey(url)){
                addedURL.add(url);
            }
        }
        return addedURL;
    }

//    поиск изменённых страниц
    private static ArrayList<URL> checkChangeWebPage(HashMap<URL, StringBuilder> yesterday,
                                                     HashMap<URL, StringBuilder> today){
        ArrayList<URL> changedURLWebPage = new ArrayList<>();
        for (URL url : yesterday.keySet()) {
            if (today.containsKey(url)) {
                if (!today.get(url).toString().equals(yesterday.get(url).toString())) {
                    changedURLWebPage.add(url);
                }
            }
        }
        return changedURLWebPage;
    }
}
