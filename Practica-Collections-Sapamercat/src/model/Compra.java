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
        int op;
        Compra compra = new Compra();

        System.out.println(ANSI_PURPLE + "BENVINGUT AL " + Compra.NOM_SUPERMERCAT + ANSI_RESET);
        do {
            op = compra.menuPrincipal();
            switch (op) {
                case 1:
                    compra.menuAfegirProducte();
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
        int op = -1;
        boolean opcioValida = false;

        while (!opcioValida) {
            System.out.println("------------");
            System.out.println("--" + ANSI_PURPLE + "INICI" + ANSI_RESET + "---");
            System.out.println("------------");
            System.out.println(ANSI_BLUE + "1) " + ANSI_YELLOW + " Introduir producte");
            System.out.println(ANSI_BLUE + "2) " + ANSI_YELLOW + " Passar per caixa");
            System.out.println(ANSI_BLUE + "3) " + ANSI_YELLOW + " Mostrar carret de compra");
            System.out.println(ANSI_BLUE + "0) " + ANSI_YELLOW + " Acabar" + ANSI_RESET);

            try {
                op = Integer.parseInt(sc.nextLine());
                if (op >= 0 && op <= 3) {
                    opcioValida = true;
                } else {
                    System.out.println(ANSI_RED + "Selecciona una opció vàlida (0-3)." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Entrada no vàlida. Introdueix un número." + ANSI_RESET);
            }
        }
        return op;
    }

    public void menuAfegirProducte() throws FileNotFoundException {
        int opP = -1;
        boolean opcioValida = false;

        while (!opcioValida) {
            System.out.println("---------------");
            System.out.println("--" + ANSI_PURPLE + "PRODUCTE" + ANSI_RESET + "---");
            System.out.println("---------------");
            System.out.println(ANSI_BLUE + "1)" + ANSI_YELLOW + " Alimentació");
            System.out.println(ANSI_BLUE + "2)" + ANSI_YELLOW + " Tèxtil");
            System.out.println(ANSI_BLUE + "3)" + ANSI_YELLOW + " Electrònica");
            System.out.println(ANSI_BLUE + "0)" + ANSI_YELLOW + " Tornar" + ANSI_RESET);

            try {
                opP = Integer.parseInt(sc.nextLine());
                if (opP >= 0 && opP <= 3) {
                    opcioValida = true;
                    switch (opP) {
                        case 1:
                            System.out.println(ANSI_PURPLE + "AFEGIR ALIMENT" + ANSI_RESET);
                            try {
                                afegirAliment();
                                System.out.println(ANSI_GREEN + "Aliment afegit correctament!" + ANSI_RESET);
                            } catch (Exception e) {
                                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                printarExcepcio(e, exemples);
                            }
                            break;
                        case 2:
                            System.out.println(ANSI_PURPLE + "AFEGIR TÈXTIL" + ANSI_RESET);
                            try {
                                afegirTextil();
                                System.out.println(ANSI_GREEN + "Tèxtil afegit correctament!" + ANSI_RESET);
                            } catch (Exception e) {
                                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                printarExcepcio(e, exemples);
                            }
                            break;
                        case 3:
                            System.out.println(ANSI_PURPLE + "AFEGIR ELECTRÒNICA" + ANSI_RESET);
                            try {
                                afegirElectronica();
                                System.out.println(ANSI_GREEN + "Electrònica afegida correctament!" + ANSI_RESET);
                            } catch (Exception e) {
                                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                printarExcepcio(e, exemples);
                            }
                            break;
                        case 0:
                            break;
                    }
                } else {
                    System.out.println(ANSI_RED + "Selecciona una opció vàlida (0-3)." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Entrada no vàlida. Introdueix un número." + ANSI_RESET);
            }
        }
    }

    public void afegirAliment() throws Exception {
        System.out.print("Nom producte:\t");
        String nom = sc.nextLine();
        if (nom.length() > 14) {
            throw new LimitCaracteresException("El nom del producte no pot tenir més de 14 caràcters.");
        }
        System.out.print("Preu:\t");
        float preu = llegirPreu();
        System.out.print("Codi de barres (13 dígits):\t");
        String codiBarres = llegirCodiBarres();
        System.out.print("Quantitat:\t");
        int quantitat = llegirQuantitat();
        System.out.print("Data de caducitat (dd/MM/yyyy):\t");
        LocalDate dataCaducitat = llegirData();

        for (int i = 0; i < quantitat; i++) {
            llista_ali.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
        }
    }

    public void afegirTextil() throws Exception {
        System.out.print("Nom producte:\t");
        String nom = sc.nextLine();
        if (nom.length() > 14) {
            throw new LimitCaracteresException("El nom del producte no pot tenir més de 14 caràcters.");
        }
        System.out.print("Preu:\t");
        float preu = llegirPreu();
        System.out.print("Codi de barres (13 dígits):\t");
        String codiBarres = llegirCodiBarres();
        System.out.print("Quantitat:\t");
        int quantitat = llegirQuantitat();
        System.out.print("Composició:\t");
        String composicio = sc.nextLine();

        for (int i = 0; i < quantitat; i++) {
            llista_textil.add(new Textil(preu, nom, codiBarres, composicio));
        }
    }

    public void afegirElectronica() throws Exception {
        System.out.print("Nom producte:\t");
        String nom = sc.nextLine();
        if (nom.length() > 14) {
            throw new LimitCaracteresException("El nom del producte no pot tenir més de 14 caràcters.");
        }
        System.out.print("Preu:\t");
        float preu = llegirPreu();
        System.out.print("Codi de barres (13 dígits):\t");
        String codiBarres = llegirCodiBarres();
        System.out.print("Quantitat:\t");
        int quantitat = llegirQuantitat();
        System.out.print("Garantia (dies):\t");
        int garantia = llegirGarantia();

        for (int i = 0; i < quantitat; i++) {
            llista_elec.add(new Electronica(preu, nom, codiBarres, garantia));
        }
    }

    public void passarPerCaixa() {
        double total = 0;
        System.out.println(ANSI_PURPLE + "-----------------------------");
        System.out.println(Compra.NOM_SUPERMERCAT);
        System.out.println("-----------------------------");
        System.out.println("Data: " + LocalDate.now());
        System.out.println("-----------------------------" + ANSI_RESET);

        // Map per emmagatzemar la quantitat de cada producte
        Map<String, Integer> quantitatProductes = new HashMap<>();

        // Comptar productes d'alimentació
        for (Alimentacio a : llista_ali) {
            String codiBarres = a.getCodibarres();
            quantitatProductes.put(codiBarres, quantitatProductes.getOrDefault(codiBarres, 0) + 1);
        }

        // Comptar productes tèxtils
        for (Textil t : llista_textil) {
            String codiBarres = t.getCodibarres();
            quantitatProductes.put(codiBarres, quantitatProductes.getOrDefault(codiBarres, 0) + 1);
        }

        // Comptar productes d'electrònica
        for (Electronica e : llista_elec) {
            String codiBarres = e.getCodibarres();
            quantitatProductes.put(codiBarres, quantitatProductes.getOrDefault(codiBarres, 0) + 1);
        }

        // Mostrar el tiquet de compra amb les quantitats
        for (Map.Entry<String, Integer> entry : quantitatProductes.entrySet()) {
            String codiBarres = entry.getKey();
            int quantitat = entry.getValue();
            String nomProducte = getNomProducte(codiBarres);
            float preu = getPreuProducte(codiBarres);
            System.out.printf("%s\t\t%d | %.1f€\t%.1f€%n", nomProducte, quantitat, preu, (preu * quantitat));
            total += preu * quantitat;
        }

        System.out.println(ANSI_PURPLE + "-----------------------------");
        System.out.printf("Total: %.1f€%n%n", total);
        System.out.println("-----------------------------" + ANSI_RESET);

        llista_ali.clear();
        llista_textil.clear();
        llista_elec.clear();
    }

    public void mostrarCarret() {
        System.out.println(ANSI_PURPLE + "-----------------------------");
        System.out.println("Carret de Compra");
        System.out.println("-----------------------------" + ANSI_RESET);

        // Map per emmagatzemar la quantitat de cada producte
        Map<String, Integer> quantitatProductes = new HashMap<>();

        // Comptar productes d'alimentació
        for (Alimentacio a : llista_ali) {
            String codiBarres = a.getCodibarres();
            quantitatProductes.put(codiBarres, quantitatProductes.getOrDefault(codiBarres, 0) + 1);
        }

        // Comptar productes tèxtils
        for (Textil t : llista_textil) {
            String codiBarres = t.getCodibarres();
            quantitatProductes.put(codiBarres, quantitatProductes.getOrDefault(codiBarres, 0) + 1);
        }

        // Comptar productes d'electrònica
        for (Electronica e : llista_elec) {
            String codiBarres = e.getCodibarres();
            quantitatProductes.put(codiBarres, quantitatProductes.getOrDefault(codiBarres, 0) + 1);
        }

        // Mostrar el carret amb les quantitats
        for (Map.Entry<String, Integer> entry : quantitatProductes.entrySet()) {
            String codiBarres = entry.getKey();
            int quantitat = entry.getValue();
            String nomProducte = getNomProducte(codiBarres);
            System.out.println(nomProducte + " -> " + quantitat);
        }

        System.out.println(ANSI_PURPLE + "-----------------------------" + ANSI_RESET);
    }

    private String getNomProducte(String codib) {
        // Busquem el producte a les tres llistes
        for (Alimentacio a : llista_ali) {
            if (a.getCodibarres().equals(codib)) {
                return a.getNom();
            }
        }
        for (Textil t : llista_textil) {
            if (t.getCodibarres().equals(codib)) {
                return t.getNom();
            }
        }
        for (Electronica e : llista_elec) {
            if (e.getCodibarres().equals(codib)) {
                return e.getNom();
            }
        }
        return "Producte desconegut"; // Si no es troba el producte
    }

    private float getPreuProducte(String codib) {
        // Busquem el producte a les tres llistes
        for (Alimentacio a : llista_ali) {
            if (a.getCodibarres().equals(codib)) {
                return a.getPreu();
            }
        }
        for (Textil t : llista_textil) {
            if (t.getCodibarres().equals(codib)) {
                return t.getPreu();
            }
        }
        for (Electronica e : llista_elec) {
            if (e.getCodibarres().equals(codib)) {
                return e.getPreu();
            }
        }
        return 0; // Si no es troba el producte
    }

    private LocalDate llegirData() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                LocalDate data = LocalDate.parse(sc.nextLine(), format);
                if (data.isBefore(LocalDate.now())) {
                    System.out.print(ANSI_RED + "La data no pot ser anterior a avui. Torna a introduir (dd/MM/yyyy): " + ANSI_RESET);
                } else {
                    return data;
                }
            } catch (Exception e) {
                System.out.print(ANSI_RED + "Format de data incorrecte. Torna a introduir (dd/MM/yyyy): " + ANSI_RESET);
            }
        }
    }

    private float llegirPreu() {
        while (true) {
            try {
                float preu = Float.parseFloat(sc.nextLine());
                if (preu < 0) {
                    System.out.print(ANSI_RED + "El preu no pot ser negatiu. Torna a introduir: " + ANSI_RESET);
                } else {
                    return preu;
                }
            } catch (NumberFormatException e) {
                System.out.print(ANSI_RED + "Entrada no vàlida. Introdueix un número: " + ANSI_RESET);
            }
        }
    }

    private String llegirCodiBarres() {
        while (true) {
            String codiBarres = sc.nextLine();
            if (codiBarres.length() != 13) {
                System.out.print(ANSI_RED + "El codi de barres ha de tenir exactament 13 dígits. Torna a introduir: " + ANSI_RESET);
            } else {
                return codiBarres;
            }
        }
    }

    private int llegirQuantitat() {
        while (true) {
            try {
                int quantitat = Integer.parseInt(sc.nextLine());
                if (quantitat < 1) {
                    System.out.print(ANSI_RED + "La quantitat ha de ser com a mínim 1. Torna a introduir: " + ANSI_RESET);
                } else {
                    return quantitat;
                }
            } catch (NumberFormatException e) {
                System.out.print(ANSI_RED + "Entrada no vàlida. Introdueix un número: " + ANSI_RESET);
            }
        }
    }

    private int llegirGarantia() {
        while (true) {
            try {
                int garantia = Integer.parseInt(sc.nextLine());
                if (garantia < 0) {
                    System.out.print(ANSI_RED + "La garantia no pot ser negativa. Torna a introduir: " + ANSI_RESET);
                } else {
                    return garantia;
                }
            } catch (NumberFormatException e) {
                System.out.print(ANSI_RED + "Entrada no vàlida. Introdueix un número: " + ANSI_RESET);
            }
        }
    }

    public static void printarExcepcio(Throwable e, File ficher) throws FileNotFoundException {
        PrintStream writer = new PrintStream(new FileOutputStream(ficher, true));
        writer.println(e.getMessage());
        writer.close();
    }
}