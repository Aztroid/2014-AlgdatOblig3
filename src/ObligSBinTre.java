

/*
* Skrevet av:
* Mads Myrbakken Karlstad, studentnr: 193949, klasse: HINGDATA13H2AA
* Erlend Westbye, studentnr: 193377, klasse: HINGDATA13H2AA
* William Benjamin Wold, studentnr: 183670, klasse: HINGDATA13H2AA
* Christoffer Baier Jønsberg, studentnr: 193674, klasse: HINGDATA13H2AA
* Vegar Nygård, studentnr: 193362, klasse: HINGDATA13H2AA
*/

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; høyre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
      this(verdi, null, null, forelder);
    }

    @Override
    public String toString(){ return "" + verdi;}

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstruktør
  {
    rot = null;
    antall = 0;
    comp = c;
  }
  
  @Override
  public boolean leggInn(T verdi)    // skal ligge i class SBinTre
  {
    if (verdi == null) throw new NullPointerException("Ulovlig nullverdi!");

    Node<T> p = rot, q = null;               // p starter i roten
    int cmp = 0;                             // hjelpevariabel

    while (p != null)       // fortsetter til p er ute av treet
    {
      q = p;                                 // q er forelder til p
      cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
      p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
    }
    // p er nå null, dvs. ute av treet, q er den siste vi passerte
    
    p = new Node<>(verdi,null,null,q);       // oppretter en ny node

    if (q == null) rot = p;                  // p blir rotnode
    else if (cmp < 0) q.venstre = p;         // venstre barn til q
    else q.høyre = p;                        // høyre barn til q

    antall++;                                // én verdi mer i treet
    return true;                             // vellykket innlegging
  }
  
  @Override
  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;
    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.høyre;
      else return true;
    }

    return false;
  }
  
  @Override
  public boolean fjern(T verdi)
  {
    if (verdi == null) return false;  // treet har ingen nullverdier

    Node<T> p = rot, q = null;               // p starter i roten
    int cmp = 0; 
    while (p != null)            // leter etter verdi
    {
      cmp = comp.compare(verdi,p.verdi);      // sammenligner
      if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
      else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
      else break;    // den søkte verdien ligger i p
    }
    if (p == null) return false;   // finner ikke verdi
    System.out.println("Fjernes: "+verdi+" P.verdi: "+p.verdi);
    if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
    {
      Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
      if (p == rot){
          rot = b;
          //System.out.println("verdien i b: "+b+"verdien i venstre: "+p.venstre+" verdien i høyre: "+p.høyre);
      }
      else if (p == q.venstre) q.venstre = b;
      else q.høyre = b;
    }
    else  // Tilfelle 3)
    {
      Node<T> s = p, r = p.høyre;// finner neste i inorden
      while (r.venstre != null)
      {
        s = r;    // s er forelder til r
        r = r.venstre;
      }
      p.verdi = r.verdi;   // kopierer verdien i r til p
      if (s != p){
          s.venstre = r.høyre;
      }
      else{
          s.høyre = r.høyre;
          System.out.println("s.foreldre: "+s.forelder.høyre.verdi);
//          System.out.println("p: "+p.verdi);
//          System.out.println("s: "+s.verdi);
//          System.out.println("s.høyre: "+s.høyre.verdi);
//          System.out.println("r: "+r.verdi);
//          r.høyre=null;
//          System.out.println("r.høyre: "+r.høyre.verdi);
      }
    }

    antall--;   // det er nå én node mindre i treet
    return true;
  }
  
  public int fjernAlle(T verdi)
  {
      if(tom()){
          return 0;
      }
      Node sjekk = rot;
      int forekomster=0;

      return forekomster;
  }
  
  @Override
  public int antall()
  {
    return antall;
  }
  
  public int antall(T verdi)
  {
    if(!inneholder(verdi)||verdi==null)return 0;

    Node<T> p = rot;
    int teller = 0;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.høyre;
      
      else{
          teller++;
          p = p.høyre;
      }
    }

    return teller;
  }

  @Override
  public boolean tom()
  {
    return antall == 0;
  }

  @Override
  public void nullstill()
  {
    rot = null;
    antall = 0;
  }
  
  private static <T> Node<T> førsteInorden(Node<T> p)
  {
    while (p.venstre != null) p = p.venstre;
    return p;
  }

  private static <T> Node<T> nesteInorden(Node<T> p)
  {
    if(p.høyre != null)  // p har høyre barn
    {
      return førsteInorden(p.høyre);
    }
    else  // må gå oppover i treet
    {
      while (p.forelder != null && p.forelder.høyre == p)
      {
        p = p.forelder;
      }
      return p.forelder;
    }
  }

  private void inOrderHøyre(Node denne,int dybde, StringBuilder sb){
      if(denne==null){
          return;
      }
      inOrderHøyre(denne.høyre,dybde+1,sb);
      sb.append(denne.verdi+", ");
  }
  
  @Override
  public String toString()
  {
    StringBuilder s = new StringBuilder();
    s.append("[");
    if (!tom()) {
        Node<T> p = førsteInorden(rot);
        s.append(førsteInorden(rot).verdi);
        while (nesteInorden(p) != null) {
            s.append(", ");
            s.append(nesteInorden(p).verdi);
            p = nesteInorden(p);
        }
    }
    s.append("]");
    return s.toString();
  }
  
  public String omvendtString(){
    StringBuilder s = new StringBuilder();
    s.append("[");
    if (!tom()) {
        Stack stakken = new Stack();
        Node<T> p = førsteInorden(rot);
        stakken.push(""+førsteInorden(rot).verdi);
        while (nesteInorden(p) != null) {
            stakken.push(""+nesteInorden(p).verdi);
            p = nesteInorden(p);
        }
        for (int i = 0; i < stakken.size();i++) {
            s.append(stakken.pop());
            s.append(", ");
        }
        s.append(stakken.pop());
        s.append(", ");
        s.append(stakken.pop());
    }
    s.append("]");
    return s.toString();
  }
  
  public String høyreGren()
  {
    Node<T> p = rot;
    StringBuilder s = new StringBuilder();
    s.append("[");
    inOrderHøyre(rot,1,s);
    s.setLength(s.length()-2);
    s.append("]");
    return s.toString();
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    
    private BladnodeIterator()  // konstruktør
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public boolean hasNext()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

  } // BladnodeIterator

} // ObligSBinTre
