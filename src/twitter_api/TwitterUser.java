package twitter_api;
import org.w3c.dom.*;
import java.util.ArrayList;

public class TwitterUser {
    String userName;
    private DocumentXml userData;

    public TwitterUser(String userName, String option) throws Exception{
       this.userName = userName; 
       userData = new DocumentXml(constuireUrl(userName,option));
    }

    public String[] tweets(){
        ArrayList<String> contenuDesTweets = new ArrayList<String>();

        for(int i=0; i < balisesTweets().getLength();i++){
            contenuDesTweets.add(tweetContent(i));
        }

        return contenuDesTweets.toArray(new String[contenuDesTweets.size()]);
    }


    private NodeList balisesTweets(){
        return userData.obtenirLesElements("status");
    }

    private String tweetContent(int position){
        return userData.obtenirTexteDeLElement(balisesTweets().item(position),"text");
    }

    private static String constuireUrl(String screenName, String option){
        String url = "https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
        url += screenName;
        //url += "&count=5";

        if(option != null){
            url += "&" + option;
        }

        return url;
    }

}
