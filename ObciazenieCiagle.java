// Obciazenie Ciagle
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ObciazenieCiagle extends JDialog implements ActionListener {

    private JButton bOk, bCancel, bDalej, bWstecz;
    private JLabel lWartosc, lOdleglosc, lDlugosc, lindex, lWyswietl;
    private JTextField tWartosc, tOdleglosc, tDlugosc;
    private boolean okData;
    private String sWartosc, sOdleglosc, sDlugosc;
    private ArrayList<String> stOdleglosc, stWartosc, stDlugosc;
    private int ilicznikprzekaz = 0;
    
    public ArrayList<Double> Wartosc;
    public ArrayList<Double> Odleglosc, Dlugosc;
    public double iWartosc = 0;
    public double dOdleglosc = 0, dDlugosc;
    public int ilicznik = 0, licznikq = 0;
    
    public ObciazenieCiagle(){
        licznikq = 0;
    }
    
    public ObciazenieCiagle(JFrame owner){
        
        super(owner, "Dodawanie obciążenia ciągłego", true);
        setLayout(null);
        setBounds(500, 400, 300, 250); 

        // \/ tworzenie Labeli
        lWartosc = new JLabel("Wartość obciążenia [kN/m]: ", JLabel.RIGHT);
        lWartosc.setBounds(10, 50, 160, 20);
        add(lWartosc);        
        
        lOdleglosc = new JLabel("Odleglość obciążenia [m]: ", JLabel.RIGHT);
        lOdleglosc.setBounds(10, 80, 160, 20);
        add(lOdleglosc);
        
        lDlugosc = new JLabel("Długość obciążenia [m]: ", JLabel.RIGHT);
        lDlugosc.setBounds(10, 110, 160, 20);
        add(lDlugosc);
        
        
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        tWartosc = new JTextField();
        tWartosc.setBounds(180, 50, 100, 25);
        add(tWartosc);
        tWartosc.setToolTipText("Podaj wielkość Obciążenia");
        
        tOdleglosc = new JTextField();
        tOdleglosc.setBounds(180, 80, 100, 25);
        add(tOdleglosc);
        tOdleglosc.setToolTipText("Podaj odleglość obciążenia od początku belki");
        
        tDlugosc = new JTextField();
        tDlugosc.setBounds(180, 110, 100, 25);
        add(tDlugosc);
        tDlugosc.setToolTipText("Podaj długość obciążenia ciągłego");
        
        // /\ tworzenie TextField
        
        
        // \/ tworzenie JButton
        bOk = new JButton("OK");
        bOk.setBounds(50, 150, 100, 20);
        add(bOk);
        bOk.addActionListener(this);
        
        bCancel = new JButton("Cancel");
        bCancel.setBounds(160, 150, 100, 20);
        add(bCancel);
        bCancel.addActionListener(this);
        
        // /\ tworzenie JButton        
        
        
    }
    
    public ObciazenieCiagle(JFrame owner, int licznikp, ArrayList<Double> uWielkosc, ArrayList<Double> uOdleglosc, ArrayList<Double> uDlugosc){
        
        super(owner, "Usuwanie obciążenia ciągłego", true);
        setLayout(null);
        setBounds(500, 400, 400, 220);
        
        
        // \/ tworzenie Labeli
        lWyswietl = new JLabel("Wartość obciażenia [kN/m]: ", JLabel.RIGHT);
        lWyswietl.setBounds(0, 50, 160, 25);
        add(lWyswietl);         
        
        lindex = new JLabel("Odleglość obciążenia [m]: ", JLabel.RIGHT);
        lindex.setBounds(0, 80, 150, 25);
        add(lindex);
        
        lDlugosc = new JLabel("Długość obciążenia [m]: ", JLabel.RIGHT);
        lDlugosc.setBounds(0, 110, 150, 25);
        add(lDlugosc);
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        sWartosc = String.valueOf(uWielkosc.get(0));
        tWartosc = new JTextField(sWartosc);
        tWartosc.setBounds(160, 50, 50, 25);
        add(tWartosc);
        tWartosc.setEditable(false);
        
        sOdleglosc = String.valueOf(uOdleglosc.get(0));
        tOdleglosc = new JTextField(sOdleglosc);
        tOdleglosc.setBounds(160, 80, 50, 25);
        add(tOdleglosc);
        tOdleglosc.setEditable(false);
        
        sDlugosc = String.valueOf(uDlugosc.get(0));
        tDlugosc = new JTextField(sDlugosc);
        tDlugosc.setBounds(160, 110, 50, 25);
        add(tDlugosc);
        tDlugosc.setEditable(false);
        
        // /\ tworzenie TextField
        
        
        // \/ tworzenie JButtonow
        bOk = new JButton("OK");
        bOk.setBounds(50, 140, 100, 25);
        add(bOk);
        bOk.addActionListener(this);
        
        bCancel = new JButton("Cancel");
        bCancel.setBounds(160, 140, 100, 25);
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
        stDlugosc = new ArrayList<>();
        while(ilicznik < licznikp){
            sWartosc = String.valueOf(uWielkosc.get(ilicznik));
            stWartosc.add(sWartosc);
            sOdleglosc = String.valueOf(uOdleglosc.get(ilicznik));
            stOdleglosc.add(sOdleglosc);
            sDlugosc = String.valueOf(uDlugosc.get(ilicznik));
            stDlugosc.add(sDlugosc);
            ilicznik ++;
        }
        ilicznik = 0;
        ilicznikprzekaz = licznikp -1;
        
        // /\ przekazywanie zmiennych
    }
    
    public void setFocus(){
        
        tWartosc.requestFocusInWindow();
    }
    
    public boolean isOk(){
        return okData;
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object zrodlo = e.getSource();
        if(zrodlo == bOk){
            okData = true;
            iWartosc = Double.parseDouble(tWartosc.getText());
            dOdleglosc = Double.parseDouble(tOdleglosc.getText());
            dDlugosc = Double.parseDouble(tDlugosc.getText());
            setVisible(false);
        }
        else if(zrodlo == bCancel){
            okData = false;
            setVisible(false);
        }
        if(zrodlo == bDalej){
            if(ilicznik < ilicznikprzekaz){
                ilicznik++;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
                tDlugosc.setText(stDlugosc.get(ilicznik));
            }
            else if(ilicznik == ilicznikprzekaz){
                ilicznik = 0;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
                tDlugosc.setText(stDlugosc.get(ilicznik));
            }
        }
        else if(zrodlo == bWstecz){
            if(ilicznik > 0){
                ilicznik--;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
                tDlugosc.setText(stDlugosc.get(ilicznik));
            }
            else if(ilicznik == 0){
                ilicznik = ilicznikprzekaz;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
                tDlugosc.setText(stDlugosc.get(ilicznik));
            }
        }
    
    }     
    
    
}
