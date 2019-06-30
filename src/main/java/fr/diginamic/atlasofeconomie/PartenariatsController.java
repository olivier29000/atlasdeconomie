package fr.diginamic.atlasofeconomie;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PartenariatDao;
import models.Partenariat;

public class PartenariatsController {
	
	public void insererLesPartenariats(){
		
		// INSERTION DES INGREDIENTS
				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				} catch (SQLException e) {
					throw new TechnicalException("l'appel du driver a foiré mec!", e);
				}
				List<Partenariat> listeDePartenariats = new ArrayList<Partenariat>();
				List<String> lines = new ArrayList<String>();
				File file = new File("C:/patates/Java/2019.04.16/16.04.2019/open.......Facts.csv");
				List<String> lines = FileUtils.readLines(file, "utf8");
				lines.remove(0);
				for (String line : lines) {
					String[] attributsDuPartenariat = line.split(",");
					Partenariat partenariat=new Partenariat(Integer.parseInt(attributsDuPartenariat[0]), Integer.parseInt(attributsDuPartenariat[1]), Integer.parseInt(attributsDuPartenariat[2]), Integer.parseInt( attributsDuPartenariat[3]), Integer.parseInt( attributsDuPartenariat[4]), Integer.parseInt( attributsDuPartenariat[5]), Integer.parseInt( attributsDuPartenariat[6]), Integer.parseInt(attributsDuPartenariat[7]));
					listeDePartenariats.add(partenariat);
				}
				
				PartenariatDao partenariatDao = new PartenariatDao();
				partenariatDao.insererPartenariats(listeDePartenariats);
				
				
		
	}

}
