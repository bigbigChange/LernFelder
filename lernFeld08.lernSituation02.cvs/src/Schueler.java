/**
  *
  * Datenklasse für Schüler
  *
  * @version 1.0 vom 21.09.2012
  * @author Tenbusch
  */

public class Schueler {

  // Anfang Attribute
  private String name;
  private int joker;
  private int blamiert;
  private int fragen;
  // Ende Attribute

  //Konstruktor
  public Schueler(String name, int joker, int blamiert, int fragen){
    this.name = name;
    this.joker = joker;
    this.blamiert = blamiert;
    this.fragen = fragen;
  }
  //Zugriffsmethoden
  // Anfang Methoden
  public String getName() {
    return name;
  }

  public int getJoker() {
    return joker;
  }

  public int getBlamiert() {
    return blamiert;
  }

  public int getFragen() {
    return fragen;
  }

  public void blamiert() {
    this.blamiert++;
  }

  public void gefragt() {
    this.fragen++;
  }

  public void nichtDa(){
    this.fragen--;
  }

  public void joker_eingesetzt() {
    this.joker++;
  }

  public String toString(){
    return this.name + "(Joker: " + this.joker + ", Fragen: " + this.fragen + ")";
  }
  // Ende Methoden
}
