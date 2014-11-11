/*
* Skrevet av:
* Mads Myrbakken Karlstad, studentnr: 193949, klasse: HINGDATA13H2AA
* Erlend Westbye, studentnr: 193377, klasse: HINGDATA13H2AA
* William Benjamin Wold, studentnr: 183670, klasse: HINGDATA13H2AA
* Christoffer Baier Jønsberg, studentnr: 193674, klasse: HINGDATA13H2AA
* Vegar Nygård, studentnr: 193362, klasse: HINGDATA13H2AA
*/


import java.util.*;
  import java.util.function.Predicate;

  public interface Beholder<T> extends Iterable<T>  // ny versjon
  {
    public boolean leggInn(T t);       // legger inn t i beholderen
    public boolean inneholder(T t);    // sjekker om den inneholder t
    public boolean fjern(T t);         // fjerner t fra beholderen
    public int antall();               // returnerer antallet i beholderen
    public boolean tom();              // sjekker om beholderen er tom
    public void nullstill();           // tømmer beholderen
    public Iterator<T> iterator();     // returnerer en iterator

    default boolean fjernHvis(Predicate<? super T> p)  // betingelsesfjerning
    {
      Objects.requireNonNull(p);                       // kaster unntak

      boolean fjernet = false;
      for (Iterator<T> i = iterator(); i.hasNext(); )  // løkke
      {
        if (p.test(i.next()))                          // betingelsen
        {
          i.remove(); fjernet = true;                  // fjerner
        }
      }
      return fjernet;
    }

  } // interface Beholder
