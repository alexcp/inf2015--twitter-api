package twitter_api;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class DocumentXml {
    private Document document;

    public DocumentXml(String url) throws Exception{
        this.document = obtenirLeContenuDeLurl(url);
    }

    public String obtenirTexteDeLElement(Node parent,String nomElement){
        String resultat = null;
        NodeList liste = parent.getChildNodes();

        for(int i=0; i < liste.getLength(); i++){
            if(liste.item(i).getNodeName().equals(nomElement)){
                resultat = liste.item(i).getTextContent();
            }
        }
        
        return resultat;
    }

    public NodeList obtenirLesElements(String nomDesElements){
        return document.getElementsByTagName(nomDesElements);
    }

    private Document obtenirLeContenuDeLurl(String url) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder  builder = factory.newDocumentBuilder();
        return builder.parse(url);
    }

}
