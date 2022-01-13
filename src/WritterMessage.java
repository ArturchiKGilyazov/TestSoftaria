import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class WritterMessage {
    public StringBuilder message;

    public WritterMessage(Set<URL> checkAddingWebPage, Set<URL> checkDeleteWebPage,
                          Set<URL> checkChangeWebPage){
        this.message = new StringBuilder("""
                Здравствуйте, дорогая и.о. секретаря
                За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                "Исчезли следующие страницы:
                """ +
                stringWithCheckDeleteWebPage(checkDeleteWebPage) +
                "Появились следующие новые страницы:\n" +
                stringWithCheckAddingWebPage(checkAddingWebPage) +
                "Изменились следующие страницы:\n" +
                stringWithCheckChangeWebPage(checkChangeWebPage) +
                "С уважением, автоматизированная система мониторинга."
        );
    }

    //    метод, записывающий исчезнувшие, появившиеся, изменённые страницы в консоль
    public void printInConsole(){
        System.out.println(message);
    }

    //метод, записывающий исчезнувшие, появившиеся, изменённые страницы в файл
    public void printInFile() {
        File file = new File("FileMessage.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(message.toString());
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

//    Метод, который приводит URL'ы добавленных страничек в отдельную строку
    private StringBuilder stringWithCheckAddingWebPage(Set<URL> checkAddingWebPage){
        StringBuilder stringBuilder = new StringBuilder();
        for(URL url : checkAddingWebPage){
            stringBuilder.append(url.toString()).append("\n");
        }
        return stringBuilder;
    }

//    Метод, который приводит URL'ы удалённых страничек в отдельную строку
    private StringBuilder stringWithCheckDeleteWebPage(Set<URL> checkDeleteWebPage){
        StringBuilder stringBuilder = new StringBuilder();
        for(URL url : checkDeleteWebPage){
            stringBuilder.append(url.toString()).append("\n");
        }
        return stringBuilder;
    }

//    Метод, который приводит URL'ы изменённых страничек в отдельную строку
    private StringBuilder stringWithCheckChangeWebPage(Set<URL> checkChangeWebPage){
        StringBuilder stringBuilder = new StringBuilder();
        for(URL url : checkChangeWebPage){
            stringBuilder.append(url.toString()).append("\n");
        }
        return stringBuilder;
    }
}
