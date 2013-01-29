package twitter_api;
import org.w3c.dom.*;

public class Twitter_api {

    public static void main(String[] args) throws Exception {

        DocumentXml nouveauDocument = new DocumentXml();

        Element racine = nouveauDocument.nouvelleElement("users");
        nouveauDocument.ajouterElement(racine);

        DocumentXml xmlUsers = new DocumentXml("users.xml");
        NodeList listeDeBaliseUser = xmlUsers.obtenirLesElements("user");

        for(int x=0; x < listeDeBaliseUser.getLength();x++){

            String username = xmlUsers.obtenirAttributDe(listeDeBaliseUser.item(x),"name");

            Element user = nouveauDocument.nouvelleElement("user");
            user.setAttribute("name", username);
            racine.appendChild(user);

            String[] tweets = new TwitterUser(username,"count=5").tweets();
            
            for(String tweet : tweets){
                Element baliseTweet = nouveauDocument.nouvelleElement("tweet");
                baliseTweet.setTextContent(tweet);
                user.appendChild(baliseTweet);
            }
        }

        nouveauDocument.enregistrerSous("result.xml");
    }
}
