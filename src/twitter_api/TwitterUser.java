package twitter_api;
import org.w3c.dom.*;
import java.util.ArrayList;

public class TwitterUser {
    String userName;
    private DocumentXml userData;

    public TwitterUser(String userName, String option){
       self.userName = userName; 
       userData = new DocumentXml(constuireUrl(userName,options));
    }

    public String[] tweets(){
        NodeList listeDeBalises = balisesTweets();
        ArrayList<String> contenuDesTweets = new ArrayList<String>();

        for(int i=0; i < listeDeBalises.getLength();i++){
            contenuDesTweets.add(tweetContent(i));
        }

        return contenuDesTweets.toArray(new String[contenuDesTweets.size()]);
    }


    private NodeList balisesTweets(){
        return userData.obtenirLesElements("status");
    }

    private String tweetContent(int position){
        return userData.obtenirTexteDeLElement(balisesTweets.item(position),"text");
    }

    private static String constuireUrl(String screenName, String option){
        String url = "https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
        url += screenName;
        //url += "&count=5";

        if(option){
            url += "&" + option;
        }

        return url;
    }

}
