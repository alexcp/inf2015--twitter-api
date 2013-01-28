package twitter_api;
import org.w3c.dom.*;

public class Twitter_api {

    public static String constuireUrl(String screenName) {
        String url = "https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
        url += screenName;
        url += "&count=5";
        return url;
    }

    public static void main(String[] args) throws Exception {

        DocumentXml nouveauDocument = new DocumentXml();

        Element racine = nouveauDocument.nouvelleElement("users");
        nouveauDocument.ajouterElement(racine);

        DocumentXml xmlUsers = new DocumentXml("users.xml");

        NodeList listeDeBaliseUser = xmlUsers.obtenirLesElements("user");

        for(int x=0; x < listeDeBaliseUser.getLength();x++){

            String username = xmlUsers.obtenirAttributDe(listeDeBaliseUser.item(x),"name");

            String url = constuireUrl(username);

            Element user = nouveauDocument.nouvelleElement("user");
            user.setAttribute("name", username);
            racine.appendChild(user);

            DocumentXml xml = new DocumentXml(url);
            NodeList listeDeBalises = xml.obtenirLesElements("status");

            for(int i=0; i < listeDeBalises.getLength();i++){
                Element tweet = nouveauDocument.nouvelleElement("tweet");
                tweet.setTextContent(xml.obtenirTexteDeLElement(listeDeBalises.item(i),"text"));
                user.appendChild(tweet);
            }
        }

        nouveauDocument.enregistrerSous("result.xml");
    }
}
