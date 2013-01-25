package twitter_api;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;


public class DocumentXml {
    private Document document;

    public DocumentXml(String url) throws Exception{
        this.document = obtenirLeContenuDeLurl(url);
    }

    public DocumentXml() throws Exception{
        this.document = nouveauDocument();
    }

    public Element nouvelleElement(String nom){
        return document.createElement(nom);
    }

    public void ajouterElement(Element element){
          this.document.appendChild(element);
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

    public void enregistrerSous(String nomFichier) throws Exception{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(nomFichier));
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(source, result);
    }

    private Document obtenirLeContenuDeLurl(String url) throws Exception{
        return nouveauDocumentBuilder().parse(url);
    }

    private Document nouveauDocument() throws Exception{
        return nouveauDocumentBuilder().newDocument();
    }

    private DocumentBuilder nouveauDocumentBuilder() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder();
    }

}
