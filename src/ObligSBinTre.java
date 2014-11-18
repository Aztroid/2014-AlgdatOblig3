

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

    if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
    {
      Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
      if (p == rot){
          if(p.venstre!=null){
              b.forelder=null;
          }
          rot = b;
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
      p.verdi = r.verdi;   // kopierer verdien i r til pbirthday
      
      if (s != p){
          r.høyre.forelder=s;
          s.venstre = r.høyre;
      }
      else{
          if(r.høyre!=null){
              r.høyre.forelder=s;
          }
          s.høyre = r.høyre;
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
      int forekomster=0;
      while(fjern(verdi)){
          forekomster++;
      }
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
    StringBuilder s = new StringBuilder();
    s.append("[");
    if (!tom()) {
        Node<T> p = rot;
        s.append(rot.verdi);
        while(p.høyre!=null){
            p=p.høyre;
            s.append(", ");
            s.append(p.verdi);
            while(p.venstre!=null){
                p=p.venstre;
                s.append(", ");
                s.append(p.verdi);
            }
        }
        if(rot.venstre!=null&&rot.høyre==null){
            while(p.venstre!=null){
            p=p.venstre;
            s.append(", ");
            s.append(p.verdi);
            }
        }
    }
    s.append("]");
    return s.toString();
  }
  
  public String[] grener()
  {
      Deque<Node<T>> stakk = new ArrayDeque<>();
      String[] tabell;
      if(tom()){
          tabell = new String[0];
          return tabell;
      }
      Node p=førsteInorden(rot);
      while (p != null) 
      {
        if(p.venstre==null&&p.høyre==null){
            stakk.add(p);
        }  
        p = nesteInorden(p); 
      }
      int antall = stakk.size();
      tabell = new String[antall];
      if(tom())return tabell;
      Node<T> q=rot;
      stakk.clear();
      for(int i=0;i<antall;i++){
          stakk.add(q);
          while (q != null) {
              if(q.venstre!=null&&q.høyre != null){
                  p=q;
              }
              if (q.venstre == null && q.høyre == null) {
                  break;
              } else if (q.venstre != null) {
                  q = q.venstre;
              } else if (q.høyre != null) {
                  q = q.høyre;
              }
              stakk.add(q);
          }
          tabell[i]=stakk.toString();
          stakk.clear(); 
          if(p!=null){
              q=p.høyre;
          }
          while(p!=null){
              stakk.addFirst(p);
              p=p.forelder;
          }
      }
      return tabell;
  }

  public void blader(Node denne,int dybde,StringBuilder sb){
      if(denne == null){
          return;
      }
      blader(denne.venstre,dybde+1,sb);
      if(denne.høyre==null && denne.venstre==null){
          if(nesteInorden(denne)==null){
              sb.append(denne.verdi);
          }
          else{
              sb.append(denne.verdi+", ");
          }
      }
      blader(denne.høyre,dybde+1,sb);
  }
  
  public String bladnodeverdier()
  {
      StringBuilder s = new StringBuilder();
      s.append("[");
      
      Node p = rot;
      blader(rot,1,s);
      
      s.append("]");
      return s.toString();
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private Deque<Node<T>> stakk = new ArrayDeque<>();
    private boolean removeOK = false;
    
    private BladnodeIterator()// konstruktør
    {
        if (rot == null) return;
        alleblader(førsteInorden(p));
    }
    
    @Override
    public boolean hasNext()
    {
      return !stakk.isEmpty();
    }
    
    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
        q=stakk.getFirst();
        T verdi = stakk.pollFirst().verdi;
        removeOK=true;
        return verdi; 
    }
    
    @Override
    public void remove()
    {
      if(!removeOK)throw new IllegalStateException();
      if(q!=null){
          fjern(q.verdi);
          removeOK=false;
      }
    }
    
    private void alleblader(Node<T> p)   // en hjelpemetode
  {
    while (p != null) 
    {
      if(p.venstre==null&&p.høyre==null){
          stakk.add(p);  
      }
      p = nesteInorden(p); 
    }
  }

  } // BladnodeIterator

} // ObligSBinTre
