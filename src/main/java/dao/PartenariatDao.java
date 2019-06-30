package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import models.Partenariat;

public class PartenariatDao {
	
	public void insererPartenariats(List<Partenariat> listeDePartenariats) {

		// code pour inserer produit dans la table produits de MySQL

		Connection maConnection = null;
		PreparedStatement stat = null;

		// Récupération des informations dans database.properties
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String user = monFichierConf.getString("database.user");
		String url = monFichierConf.getString("database.url");
		String password = monFichierConf.getString("database.password");

		try {
			maConnection = DriverManager.getConnection(url, user, password);
			maConnection.setAutoCommit(false);

			Statement monStatement = maConnection.createStatement();
			
			String requeteSql="INSERT INTO partenariats (location_id,partner_id,product_id,year,export_value,import_value,sitc_eci,sitc_coi) VALUES ";
			for (Partenariat partenariat : listeDePartenariats) {
				requeteSql = requeteSql + " ,(\"" + partenariat.getLocationId()+"\",\""+partenariat.getPartenaireId()+"\",\""+partenariat.getProductId()+"\",\""+partenariat.getYear()+"\",\""+partenariat.getExportValue()+"\",\""+partenariat.getImportValue()+"\",\""+partenariat.getSitcEci()+"\",\""+partenariat.getSitcCoi()+"\")";
			}
			for (int i = 0; i < listeDePartenariats.size(); i++) {
				if (i == 0){
					requeteSql = requeteSql + " (\"" + listeDePartenariats.get(i).getLocationId()+"\",\""+listeDePartenariats.get(i).getPartenaireId()+"\",\""+listeDePartenariats.get(i).getProductId()+"\",\""+listeDePartenariats.get(i).getYear()+"\",\""+listeDePartenariats.get(i).getExportValue()+"\",\""+listeDePartenariats.get(i).getImportValue()+"\",\""+listeDePartenariats.get(i).getSitcEci()+"\",\""+listeDePartenariats.get(i).getSitcCoi()+"\")";
				}else {
					requeteSql = requeteSql + " ,(\"" + listeDePartenariats.get(i).getLocationId()+"\",\""+listeDePartenariats.get(i).getPartenaireId()+"\",\""+listeDePartenariats.get(i).getProductId()+"\",\""+listeDePartenariats.get(i).getYear()+"\",\""+listeDePartenariats.get(i).getExportValue()+"\",\""+listeDePartenariats.get(i).getImportValue()+"\",\""+listeDePartenariats.get(i).getSitcEci()+"\",\""+listeDePartenariats.get(i).getSitcCoi()+"\")";
				}
			}

			stat = maConnection
					.prepareStatement(requeteSql);
			
			stat.executeUpdate();

			// int nb1 = monStatement.executeUpdate("INSERT INTO produits
			// (nom_produit,id_categorie,id_marque) VALUES('"
			// + nomProduit + "'" + idCategorie + "'" + idMarque + ")");

			maConnection.commit();
		} catch (SQLException e) {
			//throw new TechnicalException("une exception est apparut", e);
		} finally {
			try {
				maConnection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//throw new TechnicalException("boooom", e);
			}
		}

}}
