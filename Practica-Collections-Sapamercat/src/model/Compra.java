package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Compra {
    public static final String NOM_SUPERMERCAT = "SAPAMERCAT";
    private static Scanner sc = new Scanner(System.in);

    private List<Alimentacio> llista_ali;
    private List<Electronica> llista_elec;
    private List<Textil> llista_textil;

    //Colors del text
    // Reset de colors
    public static final String ANSI_RESET = "\u001B[0m";
    // Colors
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    static File exemples = new File("src/exemples/Excepcions.dat");
    static File model = new File("src/model/UpdateTextilPrices.dat ");

    public Compra() {
        llista_ali = new ArrayList<Alimentacio>();
        llista_elec = new ArrayList<Electronica>();
        llista_textil = new ArrayList<Textil>();
    }

    public static void main(String... args) throws FileNotFoundException {
        int op, opP;
        Compra compra = new Compra();

        System.out.println(ANSI_PURPLE + "BENVINNGUT AL " + Compra.NOM_SUPERMERCAT + ANSI_RESET);
        do {
            op = compra.menuPrincipal();
            switch (op) {
                case 1:
                    do {opP = compra.menuProducte();
                        switch (opP) {
                            case 1:
                                System.out.println("Afegir aliment");
                                try {
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("Afegir tèxtil");
                                try {
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.println("Afegir electrònica");
                                try {
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            default:
                                break;
                        }
                    } while (opP != 0);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println(ANSI_PURPLE + "Carret" + ANSI_RESET);
                    break;
                case 0:
                    System.out.println(ANSI_YELLOW + "Gràcies per la seva compra" + ANSI_RESET);
                    break;
                default:
                    break;
            }

        } while (op != 0);

    }

    public int menuPrincipal() {
        int op;
        System.out.println("------------");
        System.out.println("--" + ANSI_PURPLE + "INICI" + ANSI_RESET + "---");
        System.out.println("------------");
        System.out.println(ANSI_BLUE + "1) " + ANSI_YELLOW + " Introduir producte");
        System.out.println(ANSI_BLUE + "2) " + ANSI_YELLOW + " Passar per caixa");
        System.out.println(ANSI_BLUE + "3) " + ANSI_YELLOW + " Mostrar carret de compra");
        System.out.println(ANSI_BLUE + "0) " + ANSI_YELLOW + " Acabar" + ANSI_RESET);

        op = Integer.parseInt(sc.nextLine());
        return op;
    }

    public int menuProducte() {
        int op;
        System.out.println("---------------");
        System.out.println("--" + ANSI_PURPLE + "PRODUCTE" + ANSI_RESET + "---");
        System.out.println("---------------");
        System.out.println(ANSI_BLUE + "1)" + ANSI_YELLOW + " Alimentació");
        System.out.println(ANSI_BLUE + "2)" + ANSI_YELLOW + " Tèxtil");
        System.out.println(ANSI_BLUE + "3)" + ANSI_YELLOW + " Electrònica");
        System.out.println(ANSI_BLUE + "0)" + ANSI_YELLOW + " Tornar" + ANSI_RESET);

        op = Integer.parseInt(sc.nextLine());
        return op;
    }
}