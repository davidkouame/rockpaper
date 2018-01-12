package droidmentor.tabwithviewpager.Model;

/**
 * Created by bootnet on 18/11/2017.
 */

public class Demande {
    private int id;
    private String libelle;
    private String date_demande;
    private String picture;
    private boolean isStatut;

    public Demande() {
    }

    public Demande(String libelle){
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(String date_demande) {
        this.date_demande = date_demande;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isStatut() {
        return isStatut;
    }

    public void setStatut(boolean statut) {
        isStatut = statut;
    }
}
