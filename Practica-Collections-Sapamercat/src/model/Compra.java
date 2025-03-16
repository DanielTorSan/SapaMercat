package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Compra {
    public static final String NOM_SUPERMERCAT = "SAPAMERCAT";
    private static Scanner sc = new Scanner(System.in);

    private List<Alimentacio> llista_ali;
    private List<Electronica> llista_elec;
    private List<Textil> llista_textil;

    // Colors del text
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    static File exemples = new File("src/exemples/Excepcions.dat");
    static File model = new File("src/model/UpdateTextilPrices.dat");

    public Compra() {
        llista_ali = new ArrayList<>();
        llista_elec = new ArrayList<>();
        llista_textil = new ArrayList<>();
    }

    public static void main(String... args) throws FileNotFoundException {
        int op, opP;
        Compra compra = new Compra();

        System.out.println(ANSI_PURPLE + "BENVINGUT AL " + Compra.NOM_SUPERMERCAT + ANSI_RESET);
        do {
            op = compra.menuPrincipal();
            switch (op) {
                case 1:
                    do {
                        opP = compra.menuProducte();
                        switch (opP) {
                            case 1:
                                System.out.println(ANSI_PURPLE + "AFEGIR ALIMENT" + ANSI_RESET);
                                try {
                                    compra.afegirAliment();
                                } catch (Exception e) {
                                    System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                    printarExcepcio(e, exemples);
                                }
                                break;
                            case 2:
                                System.out.println(ANSI_PURPLE + "AFEGIR TÈXTIL" + ANSI_RESET);
                                try {
                                    compra.afegirTextil();
                                } catch (Exception e) {
                                    System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                    printarExcepcio(e, exemples);
                                }
                                break;
                            case 3:
                                System.out.println(ANSI_PURPLE + "AFEGIR ELECTRÒNICA" + ANSI_RESET);
                                try {
                                    compra.afegirElectronica();
                                } catch (Exception e) {
                                    System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                    printarExcepcio(e, exemples);
                                }
                                break;
                            default:
                                break;
                        }
                    } while (opP != 0);
                    break;
                case 2:
                    compra.passarPerCaixa();
                    break;
                case 3:
                    System.out.println(ANSI_PURPLE + "Carret" + ANSI_RESET);
                    compra.mostrarCarret();
                    break;
                case 0:
                    System.out.println(ANSI_YELLOW + "Gràcies per la seva compra" + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_RED + "Opció no vàlida" + ANSI_RESET);
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

    public void afegirAliment() throws Exception {
        System.out.print("Nom producte:\t");
        String nom = sc.nextLine();
        if (nom.length() > 14) {
            throw new LimitCaracteresException("El nom del producte no pot tenir més de 14 caràcters.");
        }
        System.out.print("Preu:\t");
        float preu = Float.parseFloat(sc.nextLine());
        if (preu < 0) {
            throw new NegatiuException("El preu no pot ser negatiu.");
        }
        System.out.print("Codi de barres (13 dígits):\t");
        String codiBarres = sc.nextLine();
        if (codiBarres.length() != 13) {
            throw new LimitCaracteresException("El codi de barres ha de tenir exactament 13 dígits.");
        }
        System.out.print("Data de caducitat (dd/MM/yyyy):\t");
        LocalDate dataCaducitat = llegirData();

        llista_ali.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
        System.out.println(ANSI_GREEN + "Aliment afegit correctament!" + ANSI_RESET);
    }

    public void afegirTextil() throws Exception {
        System.out.print("Nom producte:\t");
        String nom = sc.nextLine();
        if (nom.length() > 14) {
            throw new LimitCaracteresException("El nom del producte no pot tenir més de 14 caràcters.");
        }
        System.out.print("Preu:\t");
        float preu = Float.parseFloat(sc.nextLine());
        if (preu < 0) {
            throw new NegatiuException("El preu no pot ser negatiu.");
        }
        System.out.print("Codi de barres (13 dígits):\t");
        String codiBarres = sc.nextLine();
        if (codiBarres.length() != 13) {
            throw new LimitCaracteresException("El codi de barres ha de tenir exactament 13 dígits.");
        }
        System.out.print("Composició:\t");
        String composicio = sc.nextLine();

        llista_textil.add(new Textil(preu, nom, codiBarres, composicio));
        System.out.println(ANSI_GREEN + "Tèxtil afegit correctament!" + ANSI_RESET);
    }

    public void afegirElectronica() throws Exception {
        System.out.print("Nom producte:\t");
        String nom = sc.nextLine();
        if (nom.length() > 14) {
            throw new LimitCaracteresException("El nom del producte no pot tenir més de 14 caràcters.");
        }
        System.out.print("Preu:\t");
        float preu = Float.parseFloat(sc.nextLine());
        if (preu < 0) {
            throw new NegatiuException("El preu no pot ser negatiu.");
        }
        System.out.print("Codi de barres (13 dígits):\t");
        String codiBarres = sc.nextLine();
        if (codiBarres.length() != 13) {
            throw new LimitCaracteresException("El codi de barres ha de tenir exactament 13 dígits.");
        }
        System.out.print("Garantia (dies):\t");
        int garantia = Integer.parseInt(sc.nextLine());

        llista_elec.add(new Electronica(preu, nom, codiBarres, garantia));
        System.out.println(ANSI_GREEN + "Electrònica afegida correctament!" + ANSI_RESET);
    }

    public void passarPerCaixa() {
        double total = 0;
        System.out.println(ANSI_PURPLE + "-----------------------------");
        System.out.println(Compra.NOM_SUPERMERCAT);
        System.out.println("-----------------------------");
        System.out.println("Data: " + LocalDate.now());
        System.out.println("-----------------------------" + ANSI_RESET);

        for (Alimentacio a : llista_ali) {
            System.out.println(a.getNom() + "\t\t1 | " + a.getPreu() + "€\t" + a.getPreu() + "€");
            total += a.getPreu();
        }

        for (Textil t : llista_textil) {
            System.out.println(t.getNom() + "\t\t1 | " + t.getPreu() + "€\t" + t.getPreu() + "€");
            total += t.getPreu();
        }

        for (Electronica e : llista_elec) {
            System.out.println(e.getNom() + "\t\t1 | " + e.getPreu() + "€\t" + e.getPreu() + "€");
            total += e.getPreu();
        }

        System.out.println(ANSI_PURPLE + "-----------------------------");
        System.out.format("Total: %.2f€%n%n", total);
        System.out.println("-----------------------------" + ANSI_RESET);

        llista_ali.clear();
        llista_textil.clear();
        llista_elec.clear();
    }

    public void mostrarCarret() {
        System.out.println(ANSI_PURPLE + "-----------------------------");
        System.out.println("Carret de Compra");
        System.out.println("-----------------------------" + ANSI_RESET);

        for (Alimentacio a : llista_ali) {
            System.out.println(a.getNom() + " (" + a.getCodibarres() + ")");
        }

        for (Textil t : llista_textil) {
            System.out.println(t.getNom() + " (" + t.getCodibarres() + ")");
        }

        for (Electronica e : llista_elec) {
            System.out.println(e.getNom() + " (" + e.getCodibarres() + ")");
        }

        System.out.println(ANSI_PURPLE + "-----------------------------" + ANSI_RESET);
    }

    private LocalDate llegirData() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                return LocalDate.parse(sc.nextLine(), format);
            } catch (Exception e) {
                System.out.print(ANSI_RED + "Format de data incorrecte. Torna a introduir (dd/MM/yyyy): " + ANSI_RESET);
            }
        }
    }

    public static void printarExcepcio(Throwable e, File ficher) throws FileNotFoundException {
        PrintStream writer = new PrintStream(new FileOutputStream(ficher, true));
        writer.println(e.getMessage());
        writer.close();
    }
}