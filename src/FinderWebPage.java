import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FinderWebPage {
    HashMap<URL, StringBuilder> yesterday;
    HashMap<URL, StringBuilder> today;
    public FinderWebPage(HashMap<URL, StringBuilder> yesterday, HashMap<URL, StringBuilder> today) {
        this.yesterday = yesterday;
        this.today = today;
    }

    //    поиск изменённых страниц
    public Set<URL> checkChangeWebPage(){
        Set<URL> changedURLWebPage = new HashSet<>();
        for (URL url : yesterday.keySet()) {
            if (today.containsKey(url)) {
                if (!today.get(url).toString().equals(yesterday.get(url).toString())) {
                    changedURLWebPage.add(url);
                }
            }
        }
        return changedURLWebPage;
    }

    //    поиск добавленных страниц
    public Set<URL> checkAddingWebPage(){
        Set<URL> addedURL = new HashSet<>();
        for (URL url : today.keySet()){
            if(!yesterday.containsKey(url)){
                addedURL.add(url);
            }
        }
        return addedURL;
    }

    //    поиск на исчезнувших страниц
    public Set<URL> checkDeletingWebPage() {
        Set<URL> deletingURL = new HashSet<>();
        for(URL url : yesterday.keySet()){
            if(!today.containsKey(url)){
                deletingURL.add(url);
            }
        }
        return deletingURL;
    }
}
