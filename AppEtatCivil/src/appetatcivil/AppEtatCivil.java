/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appetatcivil;
import java.util.Date;
import service.FemmeService;
import service.HommeService;
import entites.Femme;
import entites.Homme;
import entites.Mariage;
import service.MariageService;

/**
 *
 * @author Admin
 */
public class AppEtatCivil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HommeService hs = new HommeService();
        FemmeService fs = new FemmeService();
        MariageService ms = new MariageService();

       hs.create(new Homme("rachid", "mostapha","0687459158" , "425 saada 5 mahamid", new Date("2001/12/05")));
       fs.create(new Femme("Mariya", "elkadi","0787475958" , "425 saada 5 mahamid", new Date("2004/11/05")));
        
        ms.create(new Mariage(hs.getById(1),fs.getById(2),new Date("2020/12/04"),new Date("2023/12/04"),2));
         

        Homme homme1 = new Homme("ELHASSAN", "BENJAMIN", "0612345678", "Casablanca", new Date("12/04/1965"));
        Homme homme2 = new Homme("MOHAMED", "RAHIM", "0609876543", "Rabat", new Date("23/06/1970"));
        hs.create(homme1);
        hs.create(homme2);
        
        Femme f1 = new Femme("LAYLA", "CHAKIR", "0601112233", "Marrakech", new Date("05/03/1972"));
        Femme f2 = new Femme("NADIA", "ELAMI", "0602223344", "Fes", new Date("17/07/1975"));
        Femme f3 = new Femme("SARA", "LOUMI", "0603334455", "Tangier", new Date("22/09/1978"));
        Femme f4 = new Femme("KHAOULA", "BENSAID", "0604445566", "Casablanca", new Date("12/12/1970"));
        Femme f5 = new Femme("IMANE", "RACHID", "0605556677", "Meknes", new Date("30/05/1980"));
        fs.create(f1);
        fs.create(f2);
        fs.create(f3);
        fs.create(f4);
        fs.create(f5);
        
        ms.create(new Mariage(homme1, f1, new Date("10/01/1990"), null, 3));
        ms.create(new Mariage(homme1, f2, new Date("05/05/1995"), null, 2));
        ms.create(new Mariage(homme1, f3, new Date("12/11/2000"), null, 1));
        ms.create(new Mariage(homme1, f4, new Date("20/03/1988"), new Date("15/07/1989"), 0));

        // Create mariages for homme2
        ms.create(new Mariage(homme2, f5, new Date("01/06/1998"), null, 2));
        ms.create(new Mariage(homme2, f2, new Date("15/09/1989"), new Date("10/10/1991"), 1));

        System.out.println("Initial data created successfully!");
    }
}
    
    

