package twitter_api;
import org.w3c.dom.*;

public class Twitter_api {

    public static String constuireUrl(String screenName){
        String url = "https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
        url += screenName;
        url += "&count=5";
        return url;
    }

    public static void main(String[] args) {
        String url = constuireUrl(args[0]);

        try{
            DocumentXml xml = new DocumentXml(url);
            NodeList listeDeBalises = xml.obtenirLesElements("status");

            for(int i=0; i < listeDeBalises.getLength();i++){
                System.out.println(xml.obtenirTexteDeLElement(listeDeBalises.item(i),"text"));
            }

        }catch(Exception e){
            System.out.println("Erreur: Impossible de lire l'url");
        }
    }
}
