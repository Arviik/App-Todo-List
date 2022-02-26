import BDD.BDD;
import user.User;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static BDD BDD = null;
    private static User user = new User("","");

    static {
        try {
            BDD = new BDD();
        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println("1.Modifier mon compte");
            System.out.println("2.Supprimer mon compte");
            System.out.println("3.Déconnection");
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

    public static int getInt(){
        while (!sc.hasNextInt()){
            sc.next();
            System.out.println("Veuillez entrer un nombre entier.");
            System.out.print(">");
        }
        return sc.nextInt();
    }
}