
import java.util.*;

public class Oblig3Test
{
  public static void main(String[] args)
  {
    int antallFeil = 0;

    antallFeil += oppgave1();
    antallFeil += oppgave2();
    antallFeil += oppgave3();
    antallFeil += oppgave4();
    antallFeil += oppgave5();
//    antallFeil += oppgave6();
//    antallFeil += oppgave7();
//    antallFeil += oppgave8();
//    antallFeil += oppgave9();
//    antallFeil += oppgave10();

    if (antallFeil == 0)
    {
      System.out.println("Gratulerer!! Du passerte testen!");
    }
    else
    {
      System.out.println
        ("\nDette må forbedres. Du har minst " + antallFeil + " feil!");
    }
  }


  // OPPGAVE 1 ////////////////////////////////////////////////

  public static int oppgave1()
  {
    int antallFeil = 0;
    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    try
    {
      if (tre.antall() != 0)
      {
        antallFeil++;
        System.out.println("Oppgave 1a: Feil antall i et tomt tre!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 1b: Skal ikke kaste unntak for et tomt tre");
    }

    tre.leggInn(1); tre.leggInn(2); tre.leggInn(3);

    if (tre.antall() != 3)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 1c: Antall blir ikke oppdatert!");
    }

    return antallFeil;

  }  // slutt på Oppgave 1


  // OPPGAVE 2 ////////////////////////////////////////////////

  public static int oppgave2()
  {
    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    int antallFeil = 0;

    tre.leggInn(1);

    try
    {
      if (tre.antall(1) != 1)
      {
        antallFeil++;
        System.out.println("Oppgave 2a: Feil antall(T)-metoden!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2b: Skal ikke kaste unntak her!");
    }

    int[] a = {7,1,6,1,5,1,4,1,1,1,3};
    for (int verdi : a) tre.leggInn(verdi);

    if (tre.antall(1) != 7)
    {
      antallFeil++;
      System.out.println("Oppgave 2c: Feil antall(T)-metoden!");
    }

    if (tre.antall(7) != 1 || tre.antall(6) != 1
      || tre.antall(5) != 1 || tre.antall(4) != 1
      || tre.antall(3) != 1 || tre.antall(2) != 0
      || tre.antall(0) != 0)
    {
      antallFeil++;
      System.out.println("Oppgave 2d: Feil antall(T)-metoden!");
    }

    return antallFeil;

  }  // slutt på Oppgave 2


  // OPPGAVE 3 ////////////////////////////////////////////////

  public static int oppgave3()
  {
    int antallFeil = 0;
    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    String s;

    try
    {
      s = tre.toString();
      if (!s.equals("[]"))
      {
        antallFeil++;
        System.out.println("Oppgave 3a: Feil i toString() for et tomt tre!!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3b: Skal ikke kaste unntak for et tomt tre!");
    }

    tre.leggInn(10);

    s = tre.toString();
    if (!s.equals("[10]"))
    {
      antallFeil++;
      System.out.println("Oppgave 3c: Feil i toString() for et tomt tre!!");
    }

    int[] a = {6,14,1,8,12,3,7,9,11,13,2,5,4};
    for (int verdi : a) tre.leggInn(verdi);

    try
    {
      s = tre.toString();
      if (!s.equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]"))
      {
        antallFeil++;
        System.out.println("Oppgave 3d: Feil i toString()!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3e: Her kastes et unntak! Noe er feil her!");
    }

    tre.nullstill();

    tre.leggInn(10); tre.leggInn(10); tre.leggInn(10); tre.leggInn(10);

    s = tre.toString();
    if (!s.equals("[10, 10, 10, 10]"))
    {
      antallFeil++;
      System.out.println("Oppgave 3f: Feil i toString()!");
    }

    return antallFeil;

  }  // slutt på Oppgave 3


////// hjelpemetoder //////////////////////////  

  public static void bytt(int[] a, int i, int j)
  {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static boolean nestePermutasjon(int[] a)
  {
    int n = a.length, i = n - 2;  // i starter nest bakerst i a
    while (i >= 0 && a[i] > a[i+1]) i--;  // går forover i a

    // hvis i nå er lik -1, må a = {n,n-1, . . . , 2,1}
    if (i < 0) return false;  // a er den siste

    int j = n - 1;  // j starter bakerst i a
    for (int x = a[i]; a[j] < x; j--);     // den første større enn x
    bytt(a,i,j);    // bytter om

    for (j = n; ++i < --j; ) bytt(a,i,j);  // snur det til høyre for i
    return true;    // a inneholder en ny permutasjon
  }

// OPPGAVE 4 ////////////////////////////////////////////////    

  public static int oppgave4()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    String s;

    s = tre.omvendtString();
    if (!s.equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 4a: Feil i omvendtString()!");
    }

    int[] a = {1,2,3,4,5};

    boolean flere = true;
    while (flere)
    {
      for (int k : a) tre.leggInn(k);
      s = tre.omvendtString();
      if (!s.equals("[5, 4, 3, 2, 1]"))
      {
        antallFeil++;
        System.out.println("Oppgave 4b: Feil i omvendtString()!");
        break;
      }

      tre.nullstill();
      flere = nestePermutasjon(a);
    }

    return antallFeil;

  }  // slutt på Oppgave 4


  // OPPGAVE 5 ////////////////////////////////////////////////

  public static int oppgave5()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    String s;

    tre.leggInn(6);
    tre.fjern(6);

    s = tre.toString();
    if (!s.equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5a: Feil i fjern(T)!");
    }

    int[] a = {6,3,9,1,5,7,10,2,4,8,11};
    for (int verdi : a) tre.leggInn(verdi);

    tre.fjern(2);
    s = tre.toString();

    if (!s.equals("[1, 3, 4, 5, 6, 7, 8, 9, 10, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5b: Feil i fjern(T)!");
    }

    tre.fjern(4);
    s = tre.toString();

    if (!s.equals("[1, 3, 5, 6, 7, 8, 9, 10, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5c: Feil i fjern(T)!");
    }

    tre.fjern(6);
    s = tre.toString();
    
    if (!s.equals("[1, 3, 5, 7, 8, 9, 10, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5d: Feil i fjern(T)!");
    }
    
    tre.fjern(9);
    System.out.println("Etter 9: "+s);
    s = tre.toString();

    if (!s.equals("[1, 3, 5, 7, 8, 10, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5e: Feil i fjern(T)!");
    }

    tre.fjern(10); tre.fjern(11); tre.fjern(8); tre.fjern(7);
    s = tre.toString();

    if (!s.equals("[1, 3, 5]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5f: Feil i fjern(T)!");
    }

    tre.nullstill();

    try
    {
      if (tre.fjernAlle(0) != 0)
      {
        antallFeil++;
        System.out.println("Oppgave 5g: Feil i fjernAlle(T) for tomt tre!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 5h: Kaster unntak i fjernAlle(T) for tomt tre!");
    }

    tre.leggInn(0);

    try
    {
      if (tre.fjernAlle(0) != 1)
      {
        antallFeil++;
        System.out.println
          ("Oppgave 5i: Feil i fjernAlle(T) for tre med en verdi!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 5j: Kaster unntak i fjernAlle(T) for tre med en verdi!");
    }

    int[] b = {1,4,1,3,1,2,1,1};
    for (int verdi : b) tre.leggInn(verdi);

    if (tre.fjernAlle(1) != 5)
    {
      antallFeil++;
      System.out.println("Oppgave 5k: Feil i fjernAlle(T)!");
    }

    s = tre.toString();
    if (!s.equals("[2, 3, 4]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5l: Feil i fjernAlle(T)!");
    }

    return antallFeil;

  }  // slutt på Oppgave 5


  // OPPGAVE 6 ////////////////////////////////////////////////

  public static int oppgave6()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    String s = tre.høyreGren();

    if (!s.equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6a: Feil for tomt tre!");
    }

    tre.leggInn(3);
    s = tre.høyreGren();

    if (!s.equals("[3]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6b: Feil for tre med en verdi!");
    }

    int[] a = {1,8,2,4,7,5,6,6};
    for (int verdi : a) tre.leggInn(verdi);

    s = tre.høyreGren();
    if (!s.equals("[3, 8, 4, 7, 5, 6, 6]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6c: Feil høyre gren!");
    }

    tre.nullstill();
    tre.leggInn(4); tre.leggInn(3); tre.leggInn(2); tre.leggInn(1);

    s = tre.høyreGren();
    if (!s.equals("[4, 3, 2, 1]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6d: Feil høyre gren!");
    }

    return antallFeil;

  }  // slutt på Oppgave 6   


  // OPPGAVE 7 ////////////////////////////////////////////////

  public static int oppgave7()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    String[] s = tre.grener();

    try
    {
      if (s.length != 0)
      {
        antallFeil++;
        System.out.println
          ("Oppgave 7a: Feil i grener() for tomt tre!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 7b: Kaster unntak i grener() for tomt tre!");
    }

    tre.leggInn(10);
    s = tre.grener();
    String t = Arrays.toString(s);

    if (!t.equals("[[10]]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 7c: Feil i grener() for et tre med en verdi!");
    }

    tre.leggInn(6); tre.leggInn(9); tre.leggInn(7); tre.leggInn(8);
    t = Arrays.toString(tre.grener());

    if (!t.equals("[[10, 6, 9, 7, 8]]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 7d: Feil grener() for tre med en gren!");
    }

    tre.nullstill();

    int[] a = {4,1,6,3,5,8,2,7,9};
    for (int verdi : a) tre.leggInn(verdi);
    s = tre.grener();

    if (!s[0].equals("[4, 1, 3, 2]") || !s[1].equals("[4, 6, 5]")
      || !s[2].equals("[4, 6, 8, 7]") || !s[3].equals("[4, 6, 8, 9]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 7e: Feil i metoden grener()!");
    }

    return antallFeil;

  }  // slutt på Oppgave 7    


  // OPPGAVE 8 ////////////////////////////////////////////////

  public static int oppgave8()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    String s = tre.bladnodeverdier();

    try
    {
      if (!s.equals("[]"))
      {
        antallFeil++;
        System.out.println
          ("Oppgave 8a: Skal returnere [] for tomt tre!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 8b: Skal ikke kaste unntak for et tomt tre!");
    }

    tre.leggInn(10);
    s = tre.bladnodeverdier();

    if (!s.equals("[10]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 8c: Metoden skal returnere [10] her!");
    }

    tre.leggInn(11);
    s = tre.bladnodeverdier();

    if (!s.equals("[11]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 8d: Metoden skal returnere [11] her!");
    }

    int[] a = {7,5,8,15,6,9,13,16,12,14};
    for (int verdi : a) tre.leggInn(verdi);
    s = tre.bladnodeverdier();

    if (!s.equals("[6, 9, 12, 14, 16]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 8e: Metoden skal returnere [6, 9, 12, 14, 16] her!");
    }

    tre.nullstill();
    for (int i = 1; i <= 5; i++) tre.leggInn(i);
    s = tre.bladnodeverdier();

    if (!s.equals("[5]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 8f: Metoden skal returnere [5] her!");
    }

    return antallFeil;

  }  // slutt på Oppgave 8


  // OPPGAVE 9 ////////////////////////////////////////////////

  public static int oppgave9()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    Iterator<Integer> i = tre.iterator();

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9a: Skal kaste unntak når treet er tomt!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        antallFeil++;
        System.out.println
          ("Oppgave 9b: Skal kaste NoSuchElementException her!");
      }
    }

    tre.leggInn(10);
    i = tre.iterator();

    if (i.next().compareTo(10) != 0)
    {
      antallFeil++;
      System.out.println("Oppgave 9c: Her skal next() returnere 10!");
    }

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 9d: Skal kaste unntak når det ikke er flere igjen!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        antallFeil++;
        System.out.println
          ("Oppgave 9e: Skal kaste NoSuchElementException her!");
      }
    }

    tre.nullstill();

    int[] a = {5,2,8,1,4,6,9,3,7};
    for (int k : a) tre.leggInn(k);

    List<Integer> liste = new ArrayList<>();
    tre.forEach(verdi -> liste.add(verdi));
    String s = liste.toString();

    if (!s.equals("[1, 3, 7, 9]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 9f: Treet har 1, 3, 7 og 9 som bladnodeverdier!");
    }

    tre.nullstill();
    tre.leggInn(1); tre.leggInn(2); tre.leggInn(3); tre.leggInn(4);

    int verdi = tre.iterator().next();
    if (verdi != 4)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 9g: Treet har kun 4 som bladnodeverdi!");
    }

    return antallFeil;

  }  // slutt på Oppgave 9  


// OPPGAVE 10 ////////////////////////////////////////////////

  public static int oppgave10()
  {
    int antallFeil = 0;

    ObligSBinTre<Integer> tre =
      new ObligSBinTre<>(Comparator.naturalOrder());

    Iterator<Integer> i = tre.iterator();

    try
    {
      i.remove();
      antallFeil++;
      System.out.println("Oppgave 10a: Skal kaste unntak når treet er tomt!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IllegalStateException))
      {
        antallFeil++;
        System.out.println
          ("Oppgave 10b: Skal kaste IllegalStateException her!");
      }
    }

    tre.leggInn(2); tre.leggInn(1); tre.leggInn(3);
    i = tre.iterator();
    i.next(); i.remove();

    try
    {
      i.remove();
      antallFeil++;
      System.out.println("Oppgave 10c: Ikke tillatt med remove() her!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IllegalStateException))
      {
        antallFeil++;
        System.out.println
          ("Oppgave 10d: Skal kaste IllegalStateException her!");
      }
    }

    tre.nullstill();

    int[] a = {7,1,11,3,8,12,2,5,10,4,6,9};
    for (int verdi : a) tre.leggInn(verdi);

    try
    {
      tre.fjernHvis(x -> x % 2 == 0);  // fjerner bladnoder med partall

      if (!tre.toString().equals("[1, 3, 5, 7, 8, 9, 10, 11]"))
      {
        antallFeil++;
        System.out.println("Oppgave 10e: Feil i metoden remove()!");
      }
    }
    catch (Exception e)
    {
      antallFeil++;
      System.out.println
          ("Oppgave 10f: Skal ikke kaste unntak her!");
    }

    tre.fjernHvis(x -> x % 2 != 0);  // fjerner bladnoder med oddetall

    if (!tre.toString().equals("[1, 3, 7, 8, 10, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10g: Feil i metoden remove()!");
    }

    tre.fjernHvis(x -> true);

    if (!tre.toString().equals("[1, 7, 8, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10h: Feil i metoden remove()!");
    }

    tre.fjernHvis(x -> true);

    if (!tre.toString().equals("[7, 11]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10i: Feil i metoden remove()!");
    }

    tre.fjernHvis(x -> true);

    if (!tre.toString().equals("[7]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10j: Feil i metoden remove()!");
    }

    tre.fjernHvis(x -> true);

    if (!tre.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10k: Feil i metoden remove()!");
    }

    return antallFeil;

  }  // slutt på Oppgave 10

} // Oblig3Test
