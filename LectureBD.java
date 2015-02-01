import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LectureBD {   
	
	private Connection conn;
	private PreparedStatement psInsertPerson;
	private PreparedStatement psInsertAddress;
	private PreparedStatement psInsertClient;
	private PreparedStatement psInsertPersonCinema;
	private PreparedStatement psInsertFilm;
	private PreparedStatement psInsertPays;
	
	private PreparedStatement psInsertGenre;
	private PreparedStatement psInsertRealisateur;
	private PreparedStatement psInsertScenariste;
	private PreparedStatement psInsertActeur;
	private PreparedStatement psInsertRoles;
	private PreparedStatement psInsertAnnonce;
	private PreparedStatement psInsertPersonneCinemaFilm;
	
	private int countClient = 0;
	private int countPersonneCinema = 0;
	private int countFilm = 0;
	
   public class Role {
      public Role(int i, String n, String p) {
         id = i;
         nom = n;
         personnage = p;
      }
      protected int id;
      protected String nom;
      protected String personnage;
   }
   
   public LectureBD() {
      connectionBD();                     
   }
   
   public void closeConnection(){
	   
	   try {
		conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
   }
   
   public void lecturePersonnes(String nomFichier){      
      try {
    	  psInsertPersonCinema = conn.prepareStatement(
  				"INSERT INTO PERSONNE (IDPERSONNECINEMA, NOM, DATENAISSANCE, LIEUNAISSANCE, PHOTO, BIOGRAPHIE) " + 
  			     "VALUES (?, ?, ?, ?, ?, ?)");
    	  
    	  
         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = factory.newPullParser();

         InputStream is = new FileInputStream(nomFichier);
         parser.setInput(is, null);

         int eventType = parser.getEventType();

         String tag = null, 
                nom = null,
                anniversaire = null,
                lieu = null,
                photo = null,
                bio = null;
         
         int id = -1;
         
         while (eventType != XmlPullParser.END_DOCUMENT) 
         {
            if(eventType == XmlPullParser.START_TAG) 
            {
               tag = parser.getName();
               
               if (tag.equals("personne") && parser.getAttributeCount() == 1)
                  id = Integer.parseInt(parser.getAttributeValue(0));
            } 
            else if (eventType == XmlPullParser.END_TAG) 
            {                              
               tag = null;
               
               if (parser.getName().equals("personne") && id >= 0)
               {
                  insertionPersonne(id,nom,anniversaire,lieu,photo,bio);
                                    
                  id = -1;
                  nom = null;
                  anniversaire = null;
                  lieu = null;
                  photo = null;
                  bio = null;
               }
            }
            else if (eventType == XmlPullParser.TEXT && id >= 0) 
            {
               if (tag != null)
               {                                    
                  if (tag.equals("nom"))
                     nom = parser.getText();
                  else if (tag.equals("anniversaire"))
                     anniversaire = parser.getText();
                  else if (tag.equals("lieu"))
                     lieu = parser.getText();
                  else if (tag.equals("photo"))
                     photo = parser.getText();
                  else if (tag.equals("bio"))
                     bio = parser.getText();
               }              
            }
            
            eventType = parser.next();            
         }
         
         System.out.println("Executing batches..");
         psInsertPersonCinema.executeBatch();
         psInsertPersonCinema.close();
		 System.out.println(countPersonneCinema + " movies has been inserted.");

      }
      catch (XmlPullParserException e) {
          System.out.println(e);   
       }
       catch (IOException e) {
         System.out.println("IOException while parsing " + nomFichier); 
       }
      catch (SQLException e) {
          System.out.println(e);   
      } 
   }   
   
   public void lectureFilms(String nomFichier){
      try {
    	  psInsertPays = conn.prepareStatement(
  				"INSERT INTO FILMPAYS (IDFILM, NOMPAYS) " + 
  			     "VALUES (?, ?)");
  	   
	  	   psInsertGenre = conn.prepareStatement(
	  				"INSERT INTO GENREFILM (IDFILM, NOMGENRE) " + 
	  			     "VALUES (?, ?)");
	  	   
	  	   psInsertRealisateur = conn.prepareStatement(
	  				"INSERT INTO REALISATEUR (IDPERSONNECINEMA) " + 
	  			     "VALUES (?)");
	  	   
	  	   psInsertScenariste = conn.prepareStatement(
	  				"INSERT INTO SCENARISTE (IDFILM, NOMSCENARISTE) " + 
	  			     "VALUES (?, ?)");
	  	   
	  	   psInsertActeur = conn.prepareStatement(
	  				"INSERT INTO ACTEUR (IDPERSONNECINEMA) " + 
	  			     "VALUES (?)");
	  	   
	  	   psInsertRoles = conn.prepareStatement(
	  				"INSERT INTO PERSONNAGEFILM (IDFILM, IDPERSONNECINEMA, NOMPERSONNAGE) " + 
	  			     "VALUES (?, ?, ?)");
	  	   
	  	   psInsertAnnonce = conn.prepareStatement(
	  				"INSERT INTO ANNONCE (IDFILM, DESCRIPTION) " + 
	  			     "VALUES (?, ?)");
	  	   
	  	   psInsertPersonneCinemaFilm = conn.prepareStatement(
	  				"INSERT INTO PERSONNECINEMAFILM (IDFILM, IDPERSONNECINEMA) " + 
	  			     "VALUES (?, ?)");
	  	   
	  	   psInsertFilm = conn.prepareStatement(
	  				"INSERT INTO FILM (IDFILM, TITRE, ANNEE, LANGUE, DUREE, RESUME, POSTER) " + 
	  			     "VALUES (?, ?, ?, ?, ?, ?, ?)");
    	  
    	  
         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = factory.newPullParser();

         InputStream is = new FileInputStream(nomFichier);
         parser.setInput(is, null);

         int eventType = parser.getEventType();
         countFilm = 0;

         String tag = null, 
                titre = null,
                langue = null,
                poster = null,
                roleNom = null,
                rolePersonnage = null,
                realisateurNom = null,
                resume = null;
         
         ArrayList<String> pays = new ArrayList<String>();
         ArrayList<String> genres = new ArrayList<String>();
         ArrayList<String> scenaristes = new ArrayList<String>();
         ArrayList<Role> roles = new ArrayList<Role>();         
         ArrayList<String> annonces = new ArrayList<String>();
         
         int id = -1,
             annee = -1,
             duree = -1,
             roleId = -1,
             realisateurId = -1;
         
         while (eventType != XmlPullParser.END_DOCUMENT) 
         {
            if(eventType == XmlPullParser.START_TAG) 
            {
               tag = parser.getName();
               
               if (tag.equals("film") && parser.getAttributeCount() == 1)
                  id = Integer.parseInt(parser.getAttributeValue(0));
               else if (tag.equals("realisateur") && parser.getAttributeCount() == 1)
                  realisateurId = Integer.parseInt(parser.getAttributeValue(0));
               else if (tag.equals("acteur") && parser.getAttributeCount() == 1)
                  roleId = Integer.parseInt(parser.getAttributeValue(0));
            } 
            else if (eventType == XmlPullParser.END_TAG) 
            {                              
               tag = null;
               
               if (parser.getName().equals("film") && id >= 0)
               {
                  insertionFilm(id,titre,annee,pays,langue,
                             duree,resume,genres,realisateurNom,
                             realisateurId, scenaristes,
                             roles,poster,annonces);
                                    
                  id = -1;
                  annee = -1;
                  duree = -1;
                  titre = null;                                 
                  langue = null;                  
                  poster = null;
                  resume = null;
                  realisateurNom = null;
                  roleNom = null;
                  rolePersonnage = null;
                  realisateurId = -1;
                  roleId = -1;
                  
                  genres.clear();
                  scenaristes.clear();
                  roles.clear();
                  annonces.clear();  
                  pays.clear();
               }
               if (parser.getName().equals("role") && roleId >= 0) 
               {              
                  roles.add(new Role(roleId, roleNom, rolePersonnage));
                  roleId = -1;
                  roleNom = null;
                  rolePersonnage = null;
               }
            }
            else if (eventType == XmlPullParser.TEXT && id >= 0) 
            {
               if (tag != null)
               {                                    
                  if (tag.equals("titre"))
                     titre = parser.getText();
                  else if (tag.equals("annee"))
                     annee = Integer.parseInt(parser.getText());
                  else if (tag.equals("pays"))
                     pays.add(parser.getText());
                  else if (tag.equals("langue"))
                     langue = parser.getText();
                  else if (tag.equals("duree"))                 
                     duree = Integer.parseInt(parser.getText());
                  else if (tag.equals("resume"))                 
                     resume = parser.getText();
                  else if (tag.equals("genre"))
                     genres.add(parser.getText());
                  else if (tag.equals("realisateur"))
                     realisateurNom = parser.getText();
                  else if (tag.equals("scenariste"))
                     scenaristes.add(parser.getText());
                  else if (tag.equals("acteur"))
                     roleNom = parser.getText();
                  else if (tag.equals("personnage"))
                     rolePersonnage = parser.getText();
                  else if (tag.equals("poster"))
                     poster = parser.getText();
                  else if (tag.equals("annonce"))
                     annonces.add(parser.getText());                  
               }              
            }
            
            eventType = parser.next();            
         }
         System.out.println("Executing batches..");
		   psInsertFilm.executeBatch();
		   psInsertFilm.close();
		   psInsertPays.executeBatch();
		   psInsertPays.close();
		   psInsertGenre.executeBatch();
		   psInsertGenre.close();
		   psInsertScenariste.executeBatch();
		   psInsertScenariste.close();
		   psInsertAnnonce.executeBatch();
		   psInsertAnnonce.close();		   
		   psInsertActeur.executeBatch();
		   psInsertActeur.close();
		   psInsertRealisateur.executeBatch();
		   psInsertRealisateur.close();
		   psInsertPersonneCinemaFilm.executeBatch();
		   psInsertPersonneCinemaFilm.close();
		   psInsertRoles.executeBatch();
		   psInsertRoles.close();	   
		   System.out.println(countFilm + " movies has been inserted.");
      }
      catch (XmlPullParserException e) {
          System.out.println(e);   
      }
      catch (IOException e) {
         System.out.println("IOException while parsing " + nomFichier); 
      }
      catch (SQLException e) {
          System.out.println(e);   
      }
   }
   
   public void lectureClients(String nomFichier){
      try {
    	  
    	  psInsertPerson = conn.prepareStatement(
  				"INSERT INTO PERSONNE (IDPERSONNE, NOM, PRENOM, COURRIEL, TELEPHONE, DATENAISSANCE, MOTDEPASSE) " + 
  			     "VALUES (?, ?, ?, ?, ?, ?, ?)");
  	 
	  	  psInsertAddress = conn.prepareStatement(
						"INSERT INTO ADRESSE (IDPERSONNE, NUMEROCIVIQUE, RUE, VILLE, PROVINCE, CODEPOSTAL) " + 
					     "VALUES (?, ?, ?, ?, ?, ?)");
	  	  
	  	  psInsertClient = conn.prepareStatement(
						"INSERT INTO CLIENT (IDPERSONNE, IDCLIENT, TYPECARTECREDIT, NUMEROCARTE, MOISEXPIRATION, ANNEEEXPIRATION, CVV, IDFORFAIT)" + 
					     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    	 
	  	 
	  	//psInsertPerson.
	  	  
         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = factory.newPullParser();

         InputStream is = new FileInputStream(nomFichier);
         parser.setInput(is, null);

         int eventType = parser.getEventType();               

         String tag = null, 
                nomFamille = null,
                prenom = null,
                courriel = null,
                tel = null,
                anniv = null,
                adresse = null,
                ville = null,
                province = null,
                codePostal = null,
                carte = null,
                noCarte = null,
                motDePasse = null,
                forfait = null;                                 
         
         int id = -1,
             expMois = -1,
             expAnnee = -1;
         
         while (eventType != XmlPullParser.END_DOCUMENT) 
         {
            if(eventType == XmlPullParser.START_TAG) 
            {
               tag = parser.getName();
               
               if (tag.equals("client") && parser.getAttributeCount() == 1)
                  id = Integer.parseInt(parser.getAttributeValue(0));
            } 
            else if (eventType == XmlPullParser.END_TAG) 
            {                              
               tag = null;
               
               if (parser.getName().equals("client") && id >= 0)
               {
                  insertionClient(id,nomFamille,prenom,courriel,tel,
                             anniv,adresse,ville,province,
                             codePostal,carte,noCarte, 
                             expMois,expAnnee,motDePasse,forfait);               
                                    
                  nomFamille = null;
                  prenom = null;
                  courriel = null;               
                  tel = null;
                  anniv = null;
                  adresse = null;
                  ville = null;
                  province = null;
                  codePostal = null;
                  carte = null;
                  noCarte = null;
                  motDePasse = null; 
                  forfait = null;
                  
                  id = -1;
                  expMois = -1;
                  expAnnee = -1;
               }
            }
            else if (eventType == XmlPullParser.TEXT && id >= 0) 
            {         
               if (tag != null)
               {                                    
                  if (tag.equals("nom-famille"))
                     nomFamille = parser.getText();
                  else if (tag.equals("prenom"))
                     prenom = parser.getText();
                  else if (tag.equals("courriel"))
                     courriel = parser.getText();
                  else if (tag.equals("tel"))
                     tel = parser.getText();
                  else if (tag.equals("anniversaire"))
                     anniv = parser.getText();
                  else if (tag.equals("adresse"))
                     adresse = parser.getText();
                  else if (tag.equals("ville"))
                     ville = parser.getText();
                  else if (tag.equals("province"))
                     province = parser.getText();
                  else if (tag.equals("code-postal"))
                     codePostal = parser.getText();
                  else if (tag.equals("carte"))
                     carte = parser.getText();
                  else if (tag.equals("no"))
                     noCarte = parser.getText();
                  else if (tag.equals("exp-mois"))                 
                     expMois = Integer.parseInt(parser.getText());
                  else if (tag.equals("exp-annee"))                 
                     expAnnee = Integer.parseInt(parser.getText());
                  else if (tag.equals("mot-de-passe"))                 
                     motDePasse = parser.getText();  
                  else if (tag.equals("forfait"))                 
                     forfait = parser.getText(); 
               }              
            }
            
            eventType = parser.next();            
         }
         
         System.out.println("Executing batches..");
         psInsertPerson.executeBatch();
         psInsertPerson.close();
         psInsertAddress.executeBatch();
         psInsertAddress.close();
         psInsertClient.executeBatch();
         psInsertClient.close();
		 System.out.println(countClient + " movies has been inserted.");
         
      }
      catch (XmlPullParserException e) {
          System.out.println(e);   
      }
      catch (IOException e) {
         System.out.println("IOException while parsing " + nomFichier); 
      }
      catch (SQLException e) {
          System.out.println(e);   
      }  
   }   
   
   private void insertionPersonne(int id, String nom, String anniv, String lieu, String photo, String bio) {      

	   System.out.println("insertion personCinema no " + countPersonneCinema + ": id: " + id + ", name: " + nom);
	   
	   try {
		   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		   java.util.Date parsedDate;
		   parsedDate = df.parse(anniv);
		   java.sql.Date dateAnniversaire = new java.sql.Date(parsedDate.getTime());
		   
		   psInsertPersonCinema.setString(1, String.valueOf(id));
		   psInsertPersonCinema.setString(2, nom);
		   psInsertPersonCinema.setDate(3, dateAnniversaire);
		   psInsertPersonCinema.setString(4, lieu);
		   psInsertPersonCinema.setString(5, photo);
		   psInsertPersonCinema.setString(6, bio);
		   psInsertPersonCinema.addBatch();
		   
		   countPersonneCinema++;
		   
		   if((countPersonneCinema % 300 == 0)){
			   System.out.println("Executing batches..");
			   psInsertPersonCinema.executeBatch();
			   System.out.println(countPersonneCinema + " movies has been inserted.");
		   }
		   
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    catch (ParseException e1) {
			
			e1.printStackTrace();
		}
   }
   
   private void insertionFilm(int id, String titre, int annee,
                           ArrayList<String> pays, String langue, int duree, String resume,
                           ArrayList<String> genres, String realisateurNom, int realisateurId,
                           ArrayList<String> scenaristes,
                           ArrayList<Role> roles, String poster,
                           ArrayList<String> annonces) {         
      // On le film dans la BD
	   
	   System.out.println("insertion Film no " + countFilm + ": id: " + id + ", titre: " + titre);
	   
	   try {
		   
		   HashSet<String> setActeurs = new HashSet<String>();
		   String acteurId;
		   
		   
		   psInsertFilm.setString(1, String.valueOf(id));
		   psInsertFilm.setString(2, titre);
		   psInsertFilm.setInt(3, annee);
		   psInsertFilm.setString(4, langue);
		   psInsertFilm.setInt(5, duree);
		   psInsertFilm.setString(6, resume);
		   psInsertFilm.setString(7, poster);
		   psInsertFilm.addBatch();
		   
		   for(int i = 0; i < pays.size(); i++){
			   psInsertPays.setString(1, String.valueOf(id));
			   psInsertPays.setString(2, pays.get(0));
			   psInsertPays.addBatch();
		   }
		   
		   for(int i = 0; i < genres.size(); i++){
			   psInsertGenre.setString(1, String.valueOf(id));
			   psInsertGenre.setString(2, genres.get(0));
			   psInsertGenre.addBatch();
		   }
		   
		   for(int i = 0; i < scenaristes.size(); i++){
			   psInsertScenariste.setString(1, String.valueOf(id));
			   psInsertScenariste.setString(2, scenaristes.get(0));
			   psInsertScenariste.addBatch();
		   }
		   
		   for(int i = 0; i < annonces.size(); i++){
			   psInsertAnnonce.setString(1, String.valueOf(id));
			   psInsertAnnonce.setString(2, annonces.get(0));
			   psInsertAnnonce.addBatch();
		   }
		   
		   for(int i = 0; i < roles.size(); i++){
			   psInsertRoles.setString(1, String.valueOf(id));
			   psInsertRoles.setString(2, String.valueOf(roles.get(i).id));
			   psInsertRoles.setString(3, roles.get(i).personnage);
			   psInsertRoles.addBatch();
			   
			   setActeurs.add(String.valueOf(roles.get(i).id));
		   }
		   Iterator<String> iter = setActeurs.iterator();
		   while (iter.hasNext()) {
		       acteurId = iter.next();
		       
		       psInsertPersonneCinemaFilm.setString(1, String.valueOf(id));
			   psInsertPersonneCinemaFilm.setString(2, acteurId);
			   psInsertPersonneCinemaFilm.addBatch();
			   
			   psInsertActeur.setString(1, acteurId);
			   psInsertActeur.addBatch();
		   }
		   
		   psInsertPersonneCinemaFilm.setString(1, String.valueOf(id));
		   psInsertPersonneCinemaFilm.setString(2, String.valueOf(realisateurId));
		   psInsertPersonneCinemaFilm.addBatch();
		   
		   psInsertRealisateur.setString(1, String.valueOf(realisateurId));
		   psInsertRealisateur.addBatch();
		   	   
		   countFilm++;
		   
		   if((countFilm % 50 == 0)){
			   System.out.println("Executing batches..");
			   psInsertFilm.executeBatch();
			   psInsertPays.executeBatch();
			   psInsertGenre.executeBatch();
			   psInsertScenariste.executeBatch();
			   psInsertAnnonce.executeBatch();
			   psInsertActeur.executeBatch();
			   psInsertRealisateur.executeBatch();
			   psInsertPersonneCinemaFilm.executeBatch();
			   psInsertRoles.executeBatch();
			   
			   System.out.println(countFilm + " movies has been inserted.");
		   }
		   
		   //System.out.println("count: " + countFilm);
		   
		} catch (SQLException e) {
			
			e.printStackTrace();
		}   
   }
   
   private void insertionClient(int id, String nomFamille, String prenom,
                             String courriel, String tel, String anniv,
                             String adresse, String ville, String province,
                             String codePostal, String carte, String noCarte,
                             int expMois, int expAnnee, String motDePasse,
                             String forfait) {
      // On le client dans la BD
	   
	   System.out.println("insertion client no " + countClient + ": id: " + id + ", nomFamille: " + nomFamille + ", prenom: " + prenom + ", anniveraire: " + anniv);
	   
	   try {
		   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		   java.util.Date parsedDate =  df.parse(anniv);
		   java.sql.Date dateAnniversaire = new java.sql.Date(parsedDate.getTime());
		   
		   psInsertPerson.setString(1, String.valueOf(id));
		   psInsertPerson.setString(2, nomFamille);
		   psInsertPerson.setString(3, prenom);
		   psInsertPerson.setString(4, courriel);
		   psInsertPerson.setString(5, tel);
		   psInsertPerson.setDate(6, dateAnniversaire);
		   psInsertPerson.setString(7, motDePasse);
		   psInsertPerson.addBatch();
		   
		   String[] arrAddress = adresse.split(" ");
		   String rue = "";
		   for(int i = 1; i < arrAddress.length; i++){
			   rue += arrAddress[i];
		   }
		   
		   psInsertAddress.setString(1, String.valueOf(id));
		   psInsertAddress.setInt(2, Integer.parseInt(arrAddress[0]));
		   psInsertAddress.setString(3, rue);
		   psInsertAddress.setString(4, ville);
		   psInsertAddress.setString(5, province);
		   psInsertAddress.setString(6, codePostal);
		   psInsertAddress.addBatch();
		   
		   int cvv = 100 + (int)(Math.random()*9900); 
		   psInsertClient.setString(1, String.valueOf(id));
		   psInsertClient.setString(2, String.valueOf(id));
		   psInsertClient.setString(3, carte);
		   psInsertClient.setString(4, noCarte);
		   psInsertClient.setInt(5, expMois);
		   psInsertClient.setInt(6, expAnnee);
		   psInsertClient.setInt(7, cvv);
		   psInsertClient.setString(8, forfait);
		   psInsertClient.addBatch();
		   
		   countClient++;
		   
		   if((countClient % 300 == 0)){
			   System.out.println("Executing batches..");
			   psInsertPerson.executeBatch();
			   psInsertAddress.executeBatch();
			   psInsertClient.executeBatch();
			   System.out.println(countClient + " movies has been inserted.");
		   }
		   
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
   }
   
   private void connectionBD() {
      // On se connecte a la BD
	   try {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@big-data-3.logti.etsmtl.ca:1521:LOG660", "equipe29", "JqL8zRFa");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	   catch (SQLException e) {
			e.printStackTrace();
		}
	   
   }

   public static void main(String[] args) throws ClassNotFoundException, SQLException {
	   
      LectureBD lecture = new LectureBD();
      
      lecture.lecturePersonnes(args[0]);
      //lecture.lectureFilms(args[1]);
      //lecture.lectureClients(args[2]);
      //lecture.closeConnection();
   }
}
