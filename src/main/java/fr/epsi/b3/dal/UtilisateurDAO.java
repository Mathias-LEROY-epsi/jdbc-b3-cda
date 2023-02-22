package fr.epsi.b3.dal;

import fr.epsi.b3.bo.Utilisateur;

public interface UtilisateurDAO {

    Utilisateur authentificationSecurisee(String login, String mdp) throws Exception;
}
