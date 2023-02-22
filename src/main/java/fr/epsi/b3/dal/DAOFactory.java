package fr.epsi.b3.dal;

import java.util.ResourceBundle;

import fr.epsi.b3.dal.jdbc.ArticleJDBCDAO;
import fr.epsi.b3.dal.jdbc.FournisseurJDBCDAO;
import fr.epsi.b3.dal.jdbc.UtilisateurJDBCDAO;
import fr.epsi.b3.dal.jpa.ArticleJPADAO;
import fr.epsi.b3.dal.jpa.FournisseurJPADAO;
import fr.epsi.b3.dal.jpa.UtilisateurJPADAO;
import fr.epsi.b3.dal.xml.ArticleXMLDAO;
import fr.epsi.b3.dal.xml.FournisseurXMLDAO;
import fr.epsi.b3.dal.xml.UtilisateurXMLDAO;
import fr.epsi.b3.error.StoreModeNotFoundException;

public class DAOFactory {

    private static final String STORE_MODE;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("app-config");
        STORE_MODE = bundle.getString("store.mode");
    }

    private DAOFactory() {
    }

    public static FournisseurDAO getFournisseurDAO() throws StoreModeNotFoundException {
        return switch (STORE_MODE) {
            case "JDBC" -> new FournisseurJDBCDAO();
            case "XML" -> new FournisseurXMLDAO();
            case "JPA" -> new FournisseurJPADAO();
            default -> throw new StoreModeNotFoundException(STORE_MODE);
        };
    }

    public static UtilisateurDAO getUtilisateurDAO() throws StoreModeNotFoundException {
        return switch (STORE_MODE) {
            case "JDBC" -> new UtilisateurJDBCDAO();
            case "XML" -> new UtilisateurXMLDAO();
            case "JPA" -> new UtilisateurJPADAO();
            default -> throw new StoreModeNotFoundException(STORE_MODE);
        };
    }

    public static ArticleDAO getArticleDAO() throws StoreModeNotFoundException {
        return switch (STORE_MODE) {
            case "JDBC" -> new ArticleJDBCDAO();
            case "XML" -> new ArticleXMLDAO();
            case "JPA" -> new ArticleJPADAO();
            default -> throw new StoreModeNotFoundException(STORE_MODE);
        };
    }
}
