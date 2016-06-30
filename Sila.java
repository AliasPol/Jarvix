
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// tworzenie sily 


public class Sila extends JDialog implements ActionListener {
    
    private JButton bOk, bCancel, bDalej, bWstecz;
    private JLabel lWielkosc, lOdleglosc, lindex, lWyswietl;
    private JTextField tWielkosc, tOdleglosc;
    private boolean okData;
    private String sWartosc, sOdleglosc;
    private ArrayList<String> stOdleglosc, stWartosc;
    private int ilicznikprzekaz = 0;
    
    public ArrayList<Double> Wielkosc; 
    public ArrayList<Double> Odleglosc;
    public double iWielkosc = 0;
    public double dOdleglosc = 0;
    public int ilicznik = 0, licznikp=0;

    public Sila(){
        licznikp = 0;
    }
    
    public Sila(JFrame owner){
        
        super(owner, "Dodawanie siły", true);
        setLayout(null);
        setBounds(500, 400, 300, 200);
        
        
        // \/ tworzenie Labeli
        lWielkosc = new JLabel("Wartość siły [kN]: ", JLabel.RIGHT);
        lWielkosc.setBounds(50, 50, 100, 25);
        add(lWielkosc);        
        
        lOdleglosc = new JLabel("Odleglość siły [m]: ", JLabel.RIGHT);
        lOdleglosc.setBounds(30, 80, 120, 25);
        add(lOdleglosc);
        
        
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        tWielkosc = new JTextField();
        tWielkosc.setBounds(160, 50, 100, 25);
        add(tWielkosc);
        tWielkosc.setToolTipText("Podaj wielkość siły");
        
        tOdleglosc = new JTextField();
        tOdleglosc.setBounds(160, 80, 100, 25);
        add(tOdleglosc);
        tOdleglosc.setToolTipText("Podaj odleglość siły od początku belki");
        
        // /\ tworzenie TextField
        
        
        // \/ tworzenie JButton
        bOk = new JButton("OK");
        bOk.setBounds(50, 120, 100, 20);
        add(bOk);
        bOk.addActionListener(this);
        
        bCancel = new JButton("Cancel");
        bCancel.setBounds(160, 120, 100, 20);
        add(bCancel);
        bCancel.addActionListener(this);
        
        // /\ tworzenie JButton
        

    }
    
    public Sila(JFrame owner, int licznikp, ArrayList<Double> uWielkosc, ArrayList<Double> uOdleglosc){
        
        super(owner, "Usuwanie siły", true);
        setLayout(null);
        setBounds(500, 400, 400, 200);
        
        
        // \/ tworzenie Labeli
        lWyswietl = new JLabel("Wartość siły [kN]: ", JLabel.RIGHT);
        lWyswietl.setBounds(30, 50, 120, 25);
        add(lWyswietl);         
        
        lindex = new JLabel("Odleglość siły [m]: ", JLabel.RIGHT);
        lindex.setBounds(30, 80, 120, 25);
        add(lindex);
                
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        sWartosc = String.valueOf(uWielkosc.get(0));
        tWielkosc = new JTextField(sWartosc);
        tWielkosc.setBounds(160, 50, 50, 25);
        add(tWielkosc);
        tWielkosc.setEditable(false);
        
        sOdleglosc = String.valueOf(uOdleglosc.get(0));
        tOdleglosc = new JTextField(sOdleglosc);
        tOdleglosc.setBounds(160, 80, 50, 25);
        add(tOdleglosc);
        tOdleglosc.setEditable(false);
        
        // /\ tworzenie TextField
        
        
        // \/ tworzenie JButtonow
        bOk = new JButton("OK");
        bOk.setBounds(50, 120, 100, 25);
        add(bOk);
        bOk.addActionListener(this);
        
        bCancel = new JButton("Cancel");
        bCancel.setBounds(160, 120, 100, 25);
        add(bCancel);
        bCancel.addActionListener(this);
        
        bDalej = new JButton("Następna");
        bDalej.setBounds(220, 50, 100, 25);
        add(bDalej);
        bDalej.addActionListener(this);
        
        bWstecz = new JButton("Poprzednia");
        bWstecz.setBounds(220, 80, 100, 25);
        add(bWstecz);
        bWstecz.addActionListener(this);
        
        // /\ tworzeenie JButtonow
        
        
        // \/ przekazywanie zmiennych
        stWartosc = new ArrayList<>();
        stOdleglosc = new ArrayList<>();
        while(ilicznik < licznikp){
            sWartosc = String.valueOf(uWielkosc.get(ilicznik));
            stWartosc.add(sWartosc);
            sOdleglosc = String.valueOf(uOdleglosc.get(ilicznik));
            stOdleglosc.add(sOdleglosc);
            ilicznik ++;
        }
        ilicznik = 0;
        ilicznikprzekaz = licznikp -1;
        
        // /\ przekazywanie zmiennych
    }
    
    public void setFocus(){
        
        tWielkosc.requestFocusInWindow();
    }
    
    public boolean isOk(){
        return okData;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object zrodlo = e.getSource();
        
        if(zrodlo == bOk){
            okData = true;
            iWielkosc = Double.parseDouble(tWielkosc.getText());
            dOdleglosc = Double.parseDouble(tOdleglosc.getText());
            setVisible(false);
        }
        else if(zrodlo == bCancel){
            okData = false;
            setVisible(false);
        }
        if(zrodlo == bDalej){
            if(ilicznik < ilicznikprzekaz){
                ilicznik++;
                tWielkosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
            else if(ilicznik == ilicznikprzekaz){
                ilicznik = 0;
                tWielkosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
        }
        else if(zrodlo == bWstecz){
            if(ilicznik > 0){
                ilicznik--;
                tWielkosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
            else if(ilicznik == 0){
                ilicznik = ilicznikprzekaz;
                tWielkosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
        }

    
    }
  
}
