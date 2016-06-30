
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import sun.swing.SwingAccessor;






public class Inzynierka extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu mPlik, mWyglad, mOprogramie, mOpcje, mWykres, mZapisz;
    private JMenuItem miWyjscie, miMetal, miNumbis, miWindows, miOprogramie, miRozpocznijProjekt, miPokazWykres, miZapiszMgx;
    private JMenuItem miPokazWykres2, miZapiszWx;
    private JButton bSila, bMoment, bObciazenieCiagle, bPodpory, bBelka, bObliczClebsh, bModulYanga, bJ;
    private JButton bUsunSila, bUsunMomen, bUsunObciazenieCiagle, bObliczReakcje, bObliczPrzedzialy;
    private JTextArea notatnik, obliczenia;
    private Sila p, pUsun;
    private Moment M, MUsun;
    private ObciazenieCiagle q, qUsun;
    private Podpory r;
    private double dBelka = 0;
    private int sumalicznik = 0, licz=0;
    private ObliczanieReakcji OblReakcji;
    private PrzedzialyObliczenia PrzedzialyObl;
    private MetodaClebsha OblClebsha;
    private Rysowanie jPanel;
    private Graphics g;
    private Wykresy wMgx, wWx;
    private ModulYanga E,J;
    JTextPane notatniktext;
    private String Oprogramie = "Jarvix - prgram do obliczania ugięcia \nstatycznie wyznaczalnych belek \nprostych metodą Clebscha.\n\n";
    

    
    
    public Inzynierka(){
      
        //setSize(600, 600);
        setTitle("Jarvix");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setBounds(200, 200, 600, 600);
        setLocationRelativeTo(null);                        //centrowanie okna na pulpicie
        Wyglad();

        
        
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);                            //dodanie do ramki menu bar

        
        // \/ dodawanie kolejnych zakladek do menuBaru
        mPlik = new JMenu("Plik");
        menuBar.add(mPlik);                             // dodawanie przyciskow do manu bar
        
        mOpcje = new JMenu("Opcje");
        menuBar.add(mOpcje);
        
        menuBar.add(Box.createHorizontalGlue());         // nastepna zakladka zostanie dodana po prawej stronie
        
        mWyglad = new JMenu("Wyglad");
        menuBar.add(mWyglad);
        
        mOprogramie = new JMenu("O programie");
        menuBar.add(mOprogramie);        
        
        
        // /\ dodadwanie kolejnych zakladek do menuBaru
        
        
        // \/ dodawanie przyciskow do zakladki Plik
        miRozpocznijProjekt = new JMenuItem("Rozpocznij projekt", 'R');
        mPlik.add(miRozpocznijProjekt);
        miRozpocznijProjekt.addActionListener(this);
        miRozpocznijProjekt.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        mPlik.addSeparator();
        
        miWyjscie = new JMenuItem("Wyjście", 'W');                                  // druga opcja w nawiasie to mozliwosc wybierania przez wcisniecie klawisza W
        mPlik.add(miWyjscie);                                                       // oddawanie pod klawisz plik kolejnosc ma znaczenie
        miWyjscie.addActionListener(this);                                           // dodanie action listner pod klawisz
        miWyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));                 // skrot klawiszowy
        
        // /\ dodawanie przyciskow do zakladki Plik
        
        
        // \/ dodawanie przyciskow do zakladki Opcje
        mWykres = new JMenu("Wykresy");
        mOpcje.add(mWykres);
        
            miPokazWykres = new JMenuItem("Wykres Mg(x)", 'M');
            mWykres.add(miPokazWykres);
            miPokazWykres.addActionListener(this);
            miPokazWykres.setAccelerator(KeyStroke.getKeyStroke("G M"));
            
            miPokazWykres2 = new JMenuItem("Wykres w(x)", 'W');
            mWykres.add(miPokazWykres2);
            miPokazWykres2.addActionListener(this);
            miPokazWykres2.setAccelerator(KeyStroke.getKeyStroke("W X"));
                
            
        mZapisz = new JMenu("Zapisz");
        mOpcje.add(mZapisz);
            
            miZapiszMgx = new JMenuItem("Zapisz Mg(x)");
            mZapisz.add(miZapiszMgx);
            miZapiszMgx.addActionListener(this);
            miZapiszMgx.setAccelerator(KeyStroke.getKeyStroke("Z M"));
            
            miZapiszWx = new JMenuItem("Zapisz w(x)");
            mZapisz.add(miZapiszWx);
            miZapiszWx.addActionListener(this);
            miZapiszWx.setAccelerator(KeyStroke.getKeyStroke("Z W"));
        
        // /\ dodawanie przyciskow do zakladki Opcje
        
        
        // \/ dodawanie przyciskow do zakladki Wyglad            
        miMetal = new JMenuItem("Metal", 'M');
        mWyglad.add(miMetal);
        miMetal.addActionListener(this);
        mWyglad.addSeparator();
            
        miNumbis = new JMenuItem("Nimbus", 'N');
        mWyglad.add(miNumbis);
        miNumbis.addActionListener(this);
        mWyglad.addSeparator();
            
        miWindows = new JMenuItem("Windows", 'W');
        mWyglad.add(miWindows);
        miWindows.addActionListener(this);
        // /\ dodawanie przyciskow do zakladki Wyglad  
            
            
        // \/ dodwanie przyciskow do zakladki OProgramie
        miOprogramie = new JMenuItem("O programie", 'O');
        mOprogramie.add(miOprogramie);
        miOprogramie.addActionListener(this);
        
        // /\ dodawnaie przyciskow do zakladki OProgramie
        
        
        // \/ tworzenie przyciskow
        bSila = new JButton("P");
        add(bSila);
        bSila.setBounds(0, 10, 50, 20);
        bSila.setToolTipText("Dodawanie nowej sily");
        bSila.addActionListener(this);
        bSila.setEnabled(false);
        
        bMoment = new JButton("M");
        add(bMoment);
        bMoment.setBounds(0, 30, 50, 20);
        bMoment.setToolTipText("Dodawanie nowego momentu");
        bMoment.addActionListener(this);
        bMoment.setEnabled(false);
        
        bObciazenieCiagle = new JButton("q");
        add(bObciazenieCiagle);
        bObciazenieCiagle.setBounds(0, 50, 50, 20);
        bObciazenieCiagle.setToolTipText("Dodawanie nowego obciążenia ciągłego");
        bObciazenieCiagle.addActionListener(this);
        bObciazenieCiagle.setEnabled(false);
        
        bPodpory = new JButton("||");
        add(bPodpory);
        bPodpory.setBounds(0, 70, 50, 20);
        bPodpory.setToolTipText("Definiowanie podpór");
        bPodpory.addActionListener(this);
        bPodpory.setEnabled(false);
        
        bUsunSila = new JButton("X");
        add(bUsunSila);
        bUsunSila.setBounds(50, 10, 50, 20);
        bUsunSila.setToolTipText("Usuwanie jednej z stworzonych sił skupionych");
        bUsunSila.setEnabled(false);
        bUsunSila.addActionListener(this);
        
        bUsunMomen = new JButton("X");
        add(bUsunMomen);
        bUsunMomen.setBounds(50, 30, 50, 20);
        bUsunMomen.setToolTipText("Usuwanie jednej z stworzonych momentów skupionych");
        bUsunMomen.setEnabled(false);
        bUsunMomen.addActionListener(this);
        
        bUsunObciazenieCiagle = new JButton("X");
        add(bUsunObciazenieCiagle);
        bUsunObciazenieCiagle.setBounds(50, 50, 50, 20);
        bUsunObciazenieCiagle.setToolTipText("Usuwanie jednego z stworzonych obciążeń ciągłych");
        bUsunObciazenieCiagle.setEnabled(false);
        bUsunObciazenieCiagle.addActionListener(this);
        
        bObliczReakcje = new JButton("Oblicz reakcje");
        add(bObliczReakcje);
        bObliczReakcje.setBounds(10, 360, 130, 35);
        bObliczReakcje.addActionListener(this);
        bObliczReakcje.setToolTipText("Oblicza reakcje zachodzące w podporach");
        
        bObliczPrzedzialy = new JButton("Oblicz przedziały");
        add(bObliczPrzedzialy);
        bObliczPrzedzialy.setBounds(150, 360, 130, 35);
        bObliczPrzedzialy.addActionListener(this);
        bObliczPrzedzialy.setToolTipText("Oblicz wszystkie przedziały");
        bObliczPrzedzialy.setEnabled(false);
        
        bObliczClebsh = new JButton("Oblicz ugięcie");
        add(bObliczClebsh);
        bObliczClebsh.setBounds(290, 360, 130, 35);
        bObliczClebsh.addActionListener(this);
        bObliczClebsh.setToolTipText("Oblicz metodą Clebsha");
        bObliczClebsh.setEnabled(false);
        
        bModulYanga = new JButton("E");
        add(bModulYanga);
        bModulYanga.setBounds(0, 90, 50, 20);
        bModulYanga.addActionListener(this);
        bModulYanga.setToolTipText("Moduł Younga");
        
        bJ = new JButton("J");
        add(bJ);
        bJ.setBounds(0, 110, 50, 20);
        bJ.addActionListener(this);
        bJ.setToolTipText("Moment bezwładności względem osi obojętnej przy zginaniu prostopadłej do płaszczyzny rysunku");
        bJ.setEnabled(false);
        // /\ tworzenie przyciskow
        
        
        //\/ dodanie pola tekstowego
        notatnik = new JTextArea();                             // wporownaniu do innych okienek tu chcemy miec suwaki dlatego tworzymy obiekt JScroolPane
        JScrollPane scrollPane = new JScrollPane(notatnik);
        scrollPane.setBounds(0 , 400, 585, 135);
        notatnik.setEditable(false);
        notatnik.setForeground(Color.red);
        add(scrollPane);
        
        obliczenia = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(obliczenia);
        scrollPane2.setBounds(10, 160, 565, 190);
        obliczenia.setEditable(false);
        add(scrollPane2);
                
/*
        notatniktext = new JTextPane();
        JScrollPane scrollPane3 = new JScrollPane(notatniktext);
        scrollPane3.setBounds(50, 300, 500, 100);
        add(scrollPane3);
  */             
        // /\ dodawanie pola teksotwego
     
        
        
        // \/ dodwanaie JPanel
        
        jPanel = new Rysowanie();
        jPanel.setBounds(110, 10, 360, 130);        
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);
        add(jPanel);
        // /\ dodawanie JPanel
        
       
                    
    }  

    public void Wyglad(){
        try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            SwingUtilities.updateComponentTreeUI(this);
    }
    
    public static void main(String[] args) {

        Inzynierka aplikacja = new Inzynierka();
        aplikacja.setVisible(true);
        while(true){
            aplikacja.refresh();

        }
        
    }

    
    void refresh() {
        try {
            Thread.sleep(1000);
            //licz++;
            //System.out.println("odswiezone" + licz);

            //if(licz==10){
                //jPanel.odswiez();
                //licz=0;
            //}
            
            if(dBelka>0){
                jPanel.belka(dBelka);
            }
            if(p!=null){
                for(int i=0;i<p.licznikp;i++){
                    jPanel.sila(p, i);
                }
            }
            if(M!=null){
                for(int i=0;i<M.licznikm;i++){
                    jPanel.moment(M, i);
                }
            }
            if(q!=null){
                for(int i=0;i<q.licznikq;i++){
                    jPanel.obciazenieciagle(q, i);
                }
            }
            if(r!=null){
                if(r.irodzajpodpory == 1 || r.irodzajpodpory == 2){
                    jPanel.podpory(r);
                }
            }
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {


        Object zrodlo = e.getSource();
        
        if(zrodlo == miRozpocznijProjekt){
           String sBelka = JOptionPane.showInputDialog("Podaj długość belki w metrach");
           dBelka = Double.parseDouble(sBelka);
           bMoment.setEnabled(true);
           bObciazenieCiagle.setEnabled(true);
           bSila.setEnabled(true);
           bPodpory.setEnabled(true);
           notatnik.append("Utworzono belkę o długości: " + dBelka + "m. \n");
           miRozpocznijProjekt.setEnabled(false);
           
           jPanel.belka(dBelka);
        
           
        }
        
        else if(zrodlo == miWyjscie){
            
            int odpowiedz = JOptionPane.showConfirmDialog(this, "Czy na pewno wyjść?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
            if(odpowiedz == JOptionPane.YES_OPTION){
                dispose();                                  //wylaczenie programu                
            }        
        }
        else if(zrodlo == miPokazWykres){
            
            wMgx = new Wykresy(this, PrzedzialyObl);
        }
        else if(zrodlo == miPokazWykres2){
            wWx = new Wykresy(this, p, M, q, r, PrzedzialyObl, OblClebsha, E);
        }
        else if(zrodlo == miZapiszWx){
            wWx.dPunktx = 0;
            wWx.dWzrost = 0.01;
            wWx.dobliczenia = 0;
            wWx.pomoc = 0;
            wWx.licznik = 0;
            
            if(wWx == null){
                JOptionPane.showMessageDialog(null, "Nie wygenerowałeś żadnego wykresu!","Warning", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JFileChooser FC = new JFileChooser();
                if(FC.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                File plik = FC.getSelectedFile();
                
                    try {
                        PrintWriter pw = new PrintWriter(plik);
                        
                            
            
                        for(int i=0;i<PrzedzialyObl.IloscPrzedzialow;i++){
                
                            wWx.dWzrost = PrzedzialyObl.GranicePrzedzialow[i+1]-PrzedzialyObl.GranicePrzedzialow[i];
                            wWx.dWzrost = wWx.dWzrost / wWx.dIloscProcentowa;
                            while(wWx.dPunktx <= PrzedzialyObl.GranicePrzedzialow[i+1]){
                
                                wWx.Zapis(p, M, q, r, PrzedzialyObl, OblClebsha, E, i);
                                double dwyl = new BigDecimal(wWx.dWyliczenia).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
                                pw.println(wWx.dPunktx + "   " + dwyl + "\n");
                                //pw.println("x= " + wWx.dPunktx +"  P " + wWx.pom1 + "  M " + wWx.pom2 + "   q " + wWx.pom3 + "   Ra " + wWx.pom4 + "   Rb "+ wWx.pom5 + "   q'= "  + wWx.pom6 + "   C= " + wWx.C + "   D= " + wWx.D);
                                wWx.dPunktx = wWx.dPunktx + wWx.dWzrost;
                                wWx.dobliczenia = 0;
                                wWx.dPunktx = new BigDecimal(wWx.dPunktx).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            }
                            if(wWx.licznik>0){
                                wWx.pomoc = 1;
                            }
                            else if(wWx.licznik == 0){
                                wWx.wskaznik1 = 0;
                                wWx.pomoc = 0;
                                wWx.wskaznik2 = 0;
                            }
                        }
                        pw.close();
                    
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        }
        else if(zrodlo == miZapiszMgx){
            wMgx.dPunktx = 0;
            wMgx.dWzrost = 0.01;
            if(wMgx == null){
                JOptionPane.showMessageDialog(null, "Nie wygenerowałeś żadnego wykresu!","Warning", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JFileChooser FC = new JFileChooser();
                if(FC.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                File plik = FC.getSelectedFile();
                
                    try {
                        PrintWriter pw = new PrintWriter(plik);
                        
                            
            
                        for(int i=0;i<PrzedzialyObl.IloscPrzedzialow;i++){
                
                            wMgx.dWzrost = PrzedzialyObl.GranicePrzedzialow[i+1]-PrzedzialyObl.GranicePrzedzialow[i];
                            wMgx.dWzrost = wMgx.dWzrost / wMgx.dIloscProcentowa;
                            while(wMgx.dPunktx < PrzedzialyObl.GranicePrzedzialow[i+1]){
                
                                wMgx.dWyliczenia = PrzedzialyObl.Mg.get(i) + (PrzedzialyObl.Mgx.get(i) * wMgx.dPunktx) + (PrzedzialyObl.Mgx2.get(i)*wMgx.dPunktx*wMgx.dPunktx);                                                   
                                wMgx.dWyliczenia = new BigDecimal(wMgx.dWyliczenia).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
                                pw.println(wMgx.dPunktx + "   " + wMgx.dWyliczenia + "\n");
                                wMgx.dPunktx = wMgx.dPunktx + wMgx.dWzrost;
                                wMgx.dPunktx = new BigDecimal(wMgx.dPunktx).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                
                            }
                        }
                        pw.close();
                    
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        }
        
        else if(zrodlo == miOprogramie){
            JOptionPane.showMessageDialog(null, Oprogramie + "\nAutor programu: Łukasz Szynal", "Informacje", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(zrodlo == bModulYanga){
            if(E == null){
                E = new ModulYanga(this);
            }
            E.setVisible(true);
            E.setFocus();
            if(E.isOk() == true){
                bModulYanga.setEnabled(false);
                E.E = E.dWielkosc;
                E.E = E.E * 1000000000;
                notatnik.append("Zdefiniowano wartość modułu Younga E: " + E.E + " [GPa]\n");
                bJ.setEnabled(true);
                
            }
        }
        else if(zrodlo == bJ){
            if(J == null){
                J = new ModulYanga(this, E);
            }
            J.setVisible(true);
            J.setFocus();
            if(J.isOk()==true){
                bJ.setEnabled(false);
                E.J = J.dWielkosc;
                notatnik.append("Zdefiniowano wartość momentu bezwładności J: " + E.J + " [m^4]\n");
            }
        }
        
        else if(zrodlo == bSila){
            if(p == null){
                p = new Sila(this);
                p.Wielkosc = new ArrayList<>();
                p.Odleglosc = new ArrayList<>();
            }
            p.setVisible(true);
            p.setFocus();
            
            if(p.isOk()== true){
                
                if(p.dOdleglosc > dBelka || p.dOdleglosc < 0){
                    JOptionPane.showMessageDialog(this, "Odległość siły jest błędna! ");
                }
                else {
                    p.Wielkosc.add(p.iWielkosc);
                    p.Odleglosc.add(p.dOdleglosc);
                    notatnik.append("Utworzono siłę o wielkości: " + p.Wielkosc.get(p.licznikp) + " [kN]. Odległość od początku belki wynosi: " + p.Odleglosc.get(p.licznikp) + "m \n");
                    p.licznikp ++;
                    bUsunSila.setEnabled(true);
                    
                    jPanel.sila(p, p.licznikp-1);
                   
                }
            }else {
                if(p.Wielkosc.isEmpty())
                    p=null;
            }
            
        }
        else if(zrodlo == bMoment){
            if(M == null){
                M = new Moment(this);
                M.Wartosc = new ArrayList<>();
                M.Odleglosc = new ArrayList<>();
            }
            M.setVisible(true);
            M.setFocus();
            if(M.isOk() == true){
                
                if(M.dOdleglosc > dBelka || M.dOdleglosc < 0){
                    JOptionPane.showMessageDialog(this, "Odległość momentu jest błędna! ");
                }
                else {
                    M.Wartosc.add(M.iWartosc);
                    M.Odleglosc.add(M.dOdleglosc);
                    notatnik.append("Utworzono moment o wielkości: " + M.Wartosc.get(M.licznikm) + " [kN*m]. Odległość od początku belki wynosi: " + M.Odleglosc.get(M.licznikm) + "m \n");
                    M.licznikm ++;
                    bUsunMomen.setEnabled(true);
                    
                    jPanel.moment(M, M.licznikm-1);
                }
            }
        }
        else if(zrodlo == bObciazenieCiagle){
            if(q == null){
                q = new ObciazenieCiagle(this);
                q.Wartosc = new ArrayList<>();
                q.Odleglosc = new ArrayList<>();
                q.Dlugosc = new ArrayList<>();
            }
            q.setVisible(true);
            q.setFocus();
            
            if(q.isOk()== true){
                
                if(q.dOdleglosc > dBelka || q.dOdleglosc < 0){
                    JOptionPane.showMessageDialog(this, "Odległość obciążenia ciągłego jest błędna! ");
                }
                else if(q.dDlugosc+q.dOdleglosc > dBelka || q.dDlugosc+q.dOdleglosc < 0){
                    JOptionPane.showMessageDialog(this, "Długość obciążenia ciągłego jest błędna!");
                }
                else {
                    q.Wartosc.add(q.iWartosc);
                    q.Odleglosc.add(q.dOdleglosc);
                    q.Dlugosc.add(q.dDlugosc);
                    notatnik.append("Utworzono obciążenie ciągłe o wielkości: " + q.Wartosc.get(q.licznikq) + " [kN/m]. Odległość od początku belki wynosi: " + q.Odleglosc.get(q.licznikq) + "m."); 
                    notatnik.append(" Długość obciążenia ciągłego: " + q.Dlugosc.get(q.licznikq) +  "m. \n");
                    q.licznikq ++;
                    bUsunObciazenieCiagle.setEnabled(true);
                    bObciazenieCiagle.setEnabled(false);
                    
                    jPanel.obciazenieciagle(q, q.licznikq-1);
                }
            }
        }
        
        else if(zrodlo == bPodpory){
            if(r== null){
                r = new Podpory(this);                
            }
            r.setVisible(true);
            r.setFocus();
            
            if(r.isOk() == true){
                if(r.dOdleglosc1 > dBelka || r.dOdleglosc1 < 0){
                    JOptionPane.showMessageDialog(this, "Odległość pierwszej podpory jest błędna! ");
                }
                else if(r.dOdleglosc2 > dBelka || r.dOdleglosc2 < 0){
                    JOptionPane.showMessageDialog(this, "Odległość drugiej podpory jest błędna! ");
                }
                else if(r.dOdleglosc1 == 0 && r.dOdleglosc2 == 0){
                    notatnik.append("Zdefiniowano utwierdzenie \n");
                    r.irodzajpodpory = 1;
                    
                    jPanel.podpory(r);
                    jPanel.odswiez();
                }
                else if(r.dOdleglosc1 == r.dOdleglosc2 && r.dOdleglosc1 != 0){
                    JOptionPane.showMessageDialog(this, "Podpory leżą w tej samej odległości od początku belki");
                }
                else if(r.dOdleglosc1 > 0 || r.dOdleglosc2 > 0){
                    notatnik.append("Zdefiniowano dwie podpory. Pierwsza podpora w odległości " + r.dOdleglosc1 + "m od początku belki.");
                    notatnik.append(" Druga podpora w odległości " + r.dOdleglosc2 + "m od początku belki. \n");
                    r.irodzajpodpory = 2;
                    
                    jPanel.odswiez();
                    jPanel.podpory(r);
                }
                r.DlugoscBelki = dBelka;
            }
        }
        
        else if(zrodlo == bUsunSila){
            if(pUsun == null){
                pUsun = new Sila(this, p.licznikp, p.Wielkosc, p.Odleglosc);
            }
            pUsun.setVisible(true);
            
            if(pUsun.isOk()== true){
                
                    p.Wielkosc.remove(pUsun.ilicznik);
                    p.Odleglosc.remove(pUsun.ilicznik);
                    notatnik.append("Usunięto siłę o wielkości: " + pUsun.iWielkosc + " [kN]. \n");
                    p.licznikp --;
                    
                    pUsun = null;
                    if(p.licznikp == 0){
                        bUsunSila.setEnabled(false);                    
                    }
                    jPanel.odswiez();

                }
            else if(pUsun.isOk() == false){
                pUsun = null;
            }
        } 
        else if(zrodlo == bUsunMomen){
            if(MUsun == null){
                MUsun = new Moment(this, M.licznikm, M.Wartosc, M.Odleglosc);
            }
            MUsun.setVisible(true);
            
            if(MUsun.isOk()== true){
                
                    M.Wartosc.remove(MUsun.ilicznik);
                    M.Odleglosc.remove(MUsun.ilicznik);
                    notatnik.append("Usunięto moment o wielkości: " + MUsun.iWartosc + " [kN*m]. \n");
                    M.licznikm --;
                    
                    MUsun = null;
                    if(M.licznikm == 0){
                        bUsunMomen.setEnabled(false);
                    }
                    jPanel.odswiez();
                }
            else if(MUsun.isOk() == false){
                MUsun = null;
            }
        }
        else if(zrodlo == bUsunObciazenieCiagle){
            if(qUsun == null){
                qUsun = new ObciazenieCiagle(this, q.licznikq, q.Wartosc, q.Odleglosc, q.Dlugosc);
            }
            qUsun.setVisible(true);
            
            if(qUsun.isOk()== true){
                
                    q.Wartosc.remove(qUsun.ilicznik);
                    q.Odleglosc.remove(qUsun.ilicznik);
                    q.Dlugosc.remove(qUsun.ilicznik);
                    notatnik.append("Usunięto obciążenie ciągłe o wielkości: " + qUsun.iWartosc + " [kN/m]. \n");
                    q.licznikq --;
                    //notatnik.append("Wartosc licznikaq: " + q.licznikq + ".\n");
                    bObciazenieCiagle.setEnabled(true);

                    if(q.licznikq == 0){
                        bUsunObciazenieCiagle.setEnabled(false);
                        bObciazenieCiagle.setEnabled(true);
                    }
                    qUsun = null;
                    jPanel.odswiez();
                    

                }
            else if(qUsun.isOk() == false){
                qUsun = null;
            }
        }
        
        else if(zrodlo == bObliczReakcje){
            
            Sprawdz();
            sumalicznik = p.licznikp + M.licznikm + q.licznikq + 1;
            OblReakcji = new ObliczanieReakcji(obliczenia, p, M, q, r, sumalicznik);
   
            bObliczReakcje.setEnabled(false);
            bObliczPrzedzialy.setEnabled(true);
                                    
        }
        
        else if(zrodlo == bObliczPrzedzialy){
            
            PrzedzialyObl = new PrzedzialyObliczenia(obliczenia, p, M, q, r, sumalicznik);
            bObliczPrzedzialy.setEnabled(false);
            bObliczClebsh.setEnabled(true);
            
        }
        else if(zrodlo == bObliczClebsh){
            
            OblClebsha = new MetodaClebsha(obliczenia, p , M, q, r, PrzedzialyObl);
            
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        // wybor tla
        else if(zrodlo == miMetal){
            
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            SwingUtilities.updateComponentTreeUI(this);
            
        }
        else if(zrodlo == miNumbis){
            
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            SwingUtilities.updateComponentTreeUI(this);
            
        }
        else if(zrodlo == miWindows){
            
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Inzynierka.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            SwingUtilities.updateComponentTreeUI(this);            
        }            
    }

    private void Sprawdz() {
        
        if(p==null){
            p = new Sila();
        }
        if(M==null){
            M = new Moment();
        }
        if(q==null){
            q = new ObciazenieCiagle();
        }
    }
    
}
