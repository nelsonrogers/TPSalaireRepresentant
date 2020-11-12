package representation;

import java.util.HashMap;

public class Representant {

	private final int numero;
	private final String nom;
	private final String prenom;
	private String adresse;
	private float salaireFixe;
        private ZoneGeographique secteur;
        private float caMensuel;
        private float indemniteRepas;
        private float salaireMensuel;
        HashMap <Integer, Float> CA = new HashMap<>();

	public Representant(int numero, String nom, String prenom, ZoneGeographique secteur, String adresse) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
                this.secteur = secteur;
                this.adresse = adresse;
	}

	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public float getSalaireFixe() {
		return salaireFixe;
	}

	public void setSalaireFixe(float salaireFixe) {
		this.salaireFixe = salaireFixe;
	}

	public ZoneGeographique getSecteur() {
		return secteur;
	}

	public void setSecteur(ZoneGeographique secteur) {
                this.secteur = secteur;
	}
        
        public float getCA(int mois) {
                return CA.get(mois);
        }
        
	/**
	 * Enregistre le CA de ce représentant pour un mois donné. 
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param montant le CA réalisé pour ce mois (positif ou nul)
	 **/
	public void enregistrerCA(int mois, float montant) {
		// vérifier les paramètres
		if (mois < 0 || mois > 11) {
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
		}
		if (montant < 0) {
			throw new IllegalArgumentException("Le montant doit être positif ou null");
		}
                CA.put(mois, montant);
	}

	/**
	 * Calcule le salaire mensuel de ce répresentant pour un mois donné
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param pourcentage le pourcentage (>= 0 ) à appliquer sur le CA réalisé pour ce mois
	 * @return le salaire pour ce mois, tenant compte du salaire fixe, de l'indemnité repas, et du pourcentage sur CA
	 */
	public float salaireMensuel(int mois, float pourcentage) {
                if (mois < 0 || mois > 11) {
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
                }
                if (pourcentage < 0) {
			throw new IllegalArgumentException("Le pourcentage doit être positif ou null");
		}
                caMensuel = CA.getOrDefault(mois, 0f);
                indemniteRepas = secteur.getIndemniteRepas();
                salaireMensuel = salaireFixe + indemniteRepas + pourcentage * caMensuel;
		return salaireMensuel;
	}

	@Override
	public String toString() {
		return "Representant{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + '}';
	}

}
