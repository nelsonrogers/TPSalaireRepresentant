package representation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestRepresentant {
	// Quelques constantes
	private static final float FIXE_BASTIDE = 1000f;
	private static final float INDEMNITE_OCCITANIE = 200f;
	
	private Representant r; // L'objet à tester
	private ZoneGeographique occitanie;
        private ZoneGeographique rhoneAlpes;
	
	@BeforeEach
	public void setUp() {
		// Initialiser les objets utilisés dans les tests
		occitanie = new ZoneGeographique(1, "Occitanie");
		occitanie.setIndemniteRepas(INDEMNITE_OCCITANIE);

		r = new Representant(36, "Bastide", "Rémi", occitanie, "8 Rue Fuzies Castres");	
		r.setSalaireFixe(FIXE_BASTIDE);				
	}
	
	@Test
	public void testSalaireMensuel() {
		float CA = 50000f;
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		// On enregistre un CA pour le mois 0 (janvier)
		r.enregistrerCA(0, CA);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		
		assertEquals(// Comparaison de "float"
			// valeur attendue
			FIXE_BASTIDE + INDEMNITE_OCCITANIE + CA * POURCENTAGE,
			// Valeur calculée
			salaire,
			// Marge d'erreur tolérée
			0.001,
			// Message si erreur
			"Le salaire mensuel est incorrect"
		); 
	}

	@Test
	public void testCAParDefaut() {
		
                float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA

		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		// Le CA du mois doit avoir été initialisé à 0

		assertEquals(
			FIXE_BASTIDE + INDEMNITE_OCCITANIE, 
			salaire, 
			0.001,
			"Le CA n'est pas correctement initialisé"
		);
	}

	@Test
	public void testCANegatifImpossible() {
		
		try {
			// On enregistre un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(0, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un CA négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

        }
        
        
        @Test 
        // Le représentant ne travaille que dans une région à la fois
        public void setSecteurRemetAJourRegion() {
                // On donne un nouveau secteur au representant
                rhoneAlpes = new ZoneGeographique(2, "Rhône-Alpes");
                r.setSecteur(rhoneAlpes);
                // On vérifie que le secteur a bien été mis à jour
                assertEquals(r.getSecteur(), rhoneAlpes, "La région n'a pas été mise à jour");
        }
        
        @Test
        public void testMoisNegatifImpossibleV1() {
                
                try { 
                    // On enregistre une CA pour un mois inexistant
                    // On s'attend à une exception
                    r.enregistrerCA(-1, FIXE_BASTIDE);
                    fail("Le mois ne peut pas être négatif");
                    // On ne doit pas arriver ici
                } 
                catch (IllegalArgumentException e) {
                    // On doit arriver ici
            }
        }
        
        @Test
        public void testMoisNegatifImpossibleV2() {
            
                float POURCENTAGE = 0.1f;
                
                try { 
                    // On utilise un numéro de mois négatif, on s'attend à une exception
                    r.salaireMensuel(-1, POURCENTAGE);
                    fail("Le mois ne peut pas être négatif");
                    // On ne doit pas arriver ici
                } 
                catch (IllegalArgumentException e) {
                    // On doit arriver ici
            }
        }
        
        
        @Test
        public void testMoisSupOnzeImpossibleV1() {
                try {
                    r.enregistrerCA(12, FIXE_BASTIDE);
                    fail("Le mois doit être inférieur à 12");
                    // On ne doit pas arriver ici
                }
                catch (IllegalArgumentException e) {
                    // Si on arrive ici c'est normal
                }
        }
        
            
        @Test
        public void testMoisSupOnzeImpossibleV2() {
            
                float POURCENTAGE = 0.1f;
                
                try { 
                    // On utilise un numéro de mois négatif, on s'attend à une exception
                    r.salaireMensuel(12, POURCENTAGE);
                    fail("Le mois ne peut pas être négatif");
                    // On ne doit pas arriver ici
                } 
                catch (IllegalArgumentException e) {
                    // On doit arriver ici
            }
        }
        
        
        @Test
        public void testPourcentageNegatifImpossible() {
                
                float POURCENTAGE = -0.1f;
                
                try { 
                    // Le pourcentage est négatif, on s'attend à une exception
                    r.salaireMensuel(0, POURCENTAGE);
                    fail("Le mois ne peut pas être négatif");
                    // On ne doit pas arriver ici
                } 
                catch (IllegalArgumentException e) {
                    // On doit arriver ici
                }
        }
        
        @Test 
        public void testGetNumero() {
                assertEquals(r.getNumero(), 36, "Le getter Numero ne fonctionne pas");
        }
        
        
        @Test
        public void testGetNom() {
                assertEquals(r.getNom(), "Bastide", "Le getteur Nom ne fonctionne pas");
        }
        
        @Test
        public void testGetPrenom() {
                assertEquals(r.getPrenom(), "Rémi", "Le getteur Nom ne fonctionne pas");
        }
        
        
        @Test 
        public void testSetAdresse() {
                String adresse = "42 Rue Génissieu Castres";
                r.setAdresse(adresse);
                assertEquals(r.getAdresse(), "42 Rue Génissieu Castres", "Le setteur d'adresse ne fonctionne pas");
        }
        
        @Test 
        public void testGetAdresse() {
                String adresse = "3 Rue Lamark";
                r.setAdresse(adresse);
                assertEquals(r.getAdresse(), "3 Rue Lamark", "Le getteur ne fonctionne pas");
        }
        
        
        @Test 
        public void testSetSalaireFixe() {   
                r.setSalaireFixe(FIXE_BASTIDE); // Déjà mis dans le @BeforEach, mais je remets pour la clareté
                assertEquals(r.getSalaireFixe(), FIXE_BASTIDE, "Le setteur de salaire fixe ne fonctionne pas");
                
        }
        
        @Test 
        public void testGetSalaireFixe() {
                assertEquals(r.getSalaireFixe(), FIXE_BASTIDE);
        }
        
        
        @Test 
        public void testGetSecteur() {
                assertEquals(r.getSecteur(), occitanie);
        }
        
        
        @Test
        public void testGetCA() {
                r.enregistrerCA(0,10000f);
                r.enregistrerCA(0,5000f);
                
                assertEquals(r.getCA(0), 5000f);
        }
        
        
        @Test 
        public void testToString() {
                Representant n = new Representant(2, "Rogers", "Nelson", occitanie, "42 Rue Génissieu");
                assertEquals("Representant{" + "numero=" + n.getNumero() + ", nom=" + n.getNom() + ", prenom=" + n.getPrenom() + '}',
                        n.toString()
                );
        }
        
}
