import BDD.BDD;
import user.User;
import user.Task;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static BDD BDD = null;
    private static User user = new User("","");
    private static Task task =new Task(0);

    static {
        try {
            BDD = new BDD();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("/!\\Base de donnée Non Installé./!\\");
            System.exit(404);
        }
    }

    public static void main(String[] args) throws SQLException {
        boolean running = true;
        while (running){
            while (user.getId_compte() == 0){
                int rep;
                System.out.println("\n--------App Todo-List--------");
                System.out.println("1.Inscription");
                System.out.println("2.Connexion");
                System.out.println("3.Quitter");
                System.out.print(">");
                rep = getInt();
                sc.nextLine();
                switch (rep) {
                    case 1 -> Inscription();
                    case 2 -> Connexion();
                    case 3 -> {
                        user.setId_compte(-1);
                        running = false;
                    }
                    default -> System.out.println("Veuillez enter un nombre parmis 1, 2 ou 3.");
                }
            }
            if (user.getId_compte()!=-1){
                App();
            }
        }
        System.out.println("\nFin du programme.");
    }

    public static void Inscription() throws SQLException {
        System.out.print("Entrer votre nom : ");
        String nom = sc.nextLine();
        System.out.print("Entrer votre prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Entrer votre email : ");
        String email = sc.nextLine();
        System.out.print("Entrer votre mot de passe : ");
        String mdp = sc.nextLine();
        user = new User(nom,prenom,email,mdp,BDD);
        System.out.println("Insctiption Effectué.");
    }

    public static void Connexion() throws SQLException {
        System.out.print("Entrer votre email : ");
        String email = sc.nextLine();
        System.out.print("Entrer votre mot de passe : ");
        String mdp = sc.nextLine();
        user = new User(email,mdp);
        if (user.Connexion(BDD)){
            System.out.println("Connexion Réussi.");
        } else {
            System.out.println("Echec de la Connexion.");
        }
    }

    public static void App() throws SQLException {
        boolean running = true;
        while (running){
            int rep;
            System.out.println("\n--------App Todo-List--------");
            System.out.println("Profil : "+user.getNom()+" "+user.getPrenom());
            System.out.println("1.Modifier mon compte");
            System.out.println("2.Supprimer mon compte");
            System.out.println("3.Déconnection");
            System.out.println("4.Affichage de mes tache");
            System.out.println("5.Ajout de tache");
            System.out.println("6.Assigner une tache");
            System.out.print(">");
            rep = getInt();
            sc.nextLine();
            switch (rep) {
                case 1 -> Modifier();
                case 2 -> Supprimer();
                case 3 -> {
                    user = new User("","");
                    user.setId_compte(0);
                }
                case 4 -> Affichage();
                case 5 -> Ajout_Tache();
                case 6 -> Assigner_tache();
                default -> System.out.println("Veuillez enter un nombre parmis 1, 2 ou 3.");
            }
            running = user.getId_compte() != 0;
        }
    }

    public static void Supprimer() throws SQLException {
        boolean running = true;
        while (running){
            int rep;
            System.out.println("\n--------App Todo-List--------");
            System.out.println("Voulez vous supprimer votre compte?");
            System.out.println("1.Oui");
            System.out.println("2.Non");
            System.out.print(">");
            rep = getInt();
            sc.nextLine();
            switch (rep) {
                case 1 -> {
                    user.Suppression(BDD);
                    user = new User("","");
                    user.setId_compte(0);
                    running = false;
                    System.out.println("Suppression Effectué.");
                }
                case 2 -> running = false;
                default -> System.out.println("Veuillez enter un nombre parmis 1 ou 2.");
            }
        }
    }

    public static void Modifier() throws SQLException {
        boolean running = true;
        while (running){
            int rep;
            System.out.println("\n--------App Todo-List--------");
            System.out.println("1.Nom : "+user.getNom());
            System.out.println("2.Prénom : "+user.getPrenom());
            System.out.println("3.Email : "+user.getEmail());
            System.out.println("4.Mot de passe : "+user.getMdp());
            System.out.println("5.Valider");
            System.out.print(">");
            rep = getInt();
            sc.nextLine();
            switch (rep) {
                case 1 -> {
                    System.out.print("Entrer votre nom : ");
                    user.setNom(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Entrer votre prénom : ");
                    user.setPrenom(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Entrer votre email : ");
                    user.setEmail(sc.nextLine());
                }
                case 4 -> {
                    System.out.print("Entrer votre mot de passe : ");
                    user.setMdp(sc.nextLine());
                }
                case 5 -> {
                    System.out.println("Modification Effectué.");
                    user.Modification(BDD);
                    running = false;
                }

                default -> System.out.println("Veuillez enter un nombre parmis 1, 2, 3, 4 ou 5.");
            }
        }
    }

    public static void Ajout_Tache() throws SQLException{
        boolean running = true;
        System.out.print("Entrer le libelle : ");
        String libelle = sc.nextLine();
        System.out.print("Entrer la description : ");
        String description = sc.nextLine();
        System.out.print("Entrer la difficulte : ");
        int difficulte = sc.nextInt();
        sc.nextLine();
        System.out.print("Entrer votre date_debut : ");
        String date_debut = sc.nextLine();
        System.out.print("Entrer votre date_fin : ");
        String date_fin = sc.nextLine();
        System.out.print("Entrer votre date_butoir : ");
        String date_butoir = sc.nextLine();
        task = new Task(libelle, description, difficulte, date_debut, date_fin, date_butoir, BDD);
        System.out.println("Ajout de tache Effectué.");
    }

    public static void Affichage() throws SQLException{
        task = new Task(user.getId_compte());
        task.Affiche(BDD);


    }

    public static void Assigner_tache() throws SQLException{
        task.Affiche_All_Tache(BDD);
        System.out.print("Entrer le numero de la tache a assigner : ");
        int ref_tache= sc.nextInt();
        System.out.print("Entrer le numero de l'utilisateur qui devra la realiser : ");
        int ref_compte = sc.nextInt();
        task = new Task(ref_tache,ref_compte);
        task.Assigner_tache(BDD);
    }
    public static int getInt(){
        while (!sc.hasNextInt()){
            sc.next();
            System.out.println("Veuillez entrer un nombre entier.");
            System.out.print(">");
        }
        return sc.nextInt();
    }


}