# SAPAMERCAT - Sistema de Gestió de Compres

Aquest projecte és una aplicació Java que simula un sistema de gestió de compres per a un supermercat. Permet afegir productes de diferents categories (alimentació, tèxtil i electrònica), mostrar el carret de compra i generar un tiquet de compra.

## Requisits

- **Java 8 o superior**
- **Git** per a la gestió de versions

## Funcionalitats

1. **Afegir productes:**
   - Alimentació: Nom, preu, codi de barres (13 dígits) i data de caducitat.
   - Tèxtil: Nom, preu, codi de barres (13 dígits) i composició.
   - Electrònica: Nom, preu, codi de barres (13 dígits) i dies de garantia.

2. **Mostrar carret de compra:**
   - Llista tots els productes afegits al carret.

3. **Passar per caixa:**
   - Genera un tiquet de compra amb el detall dels productes i el total a pagar.

4. **Gestió d'excepcions:**
   - Es gestionen errors com valors negatius, límits de caràcters i formats incorrectes.

## Estructura del Projecte

- **`src/model/`**: Conté les classes principals del projecte.
  - `Compra.java`: Classe principal amb el menú i la lògica de l'aplicació.
  - `Producte.java`: Classe base per als productes.
  - `Alimentacio.java`, `Textil.java`, `Electronica.java`: Classes que hereten de `Producte`.
  - `ExcepcionsPersonalitzades.java`: Conté les excepcions personalitzades.

- **`src/exemples/`**: Conté fitxers de logs per a les excepcions.

## Com Executar

1. Clona el repositori:
   ```bash
   git clone https://github.com/usuari/SAPAMERCAT.git
