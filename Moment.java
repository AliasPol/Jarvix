// Moment

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class Moment extends JDialog implements ActionListener {
    
    private JButton bOk, bCancel, bDalej, bWstecz;
    private JLabel lWartosc, lOdleglosc, lindex, lWyswietl;
    private JTextField tWartosc, tOdleglosc;
    private boolean okData;
    private String sWartosc, sOdleglosc;
    private ArrayList<String> stOdleglosc, stWartosc;
    private int ilicznikprzekaz = 0;
    
    public ArrayList<Double> Wartosc;
    public ArrayList<Double> Odleglosc;
    public double iWartosc = 0;
    public double dOdleglosc = 0;
    public int ilicznik = 0, licznikm = 0;
    
    public Moment(){
        licznikm = 0;
    }
    
    public Moment(JFrame owner){
        
        super(owner, "Dodawanie momentu", true);
        setLayout(null);
        setBounds(500, 400, 300, 200); 

        // \/ tworzenie Labeli
        lWartosc = new JLabel("Wartość momentu [kN*m]: ", JLabel.RIGHT);
        lWartosc.setBounds(10, 50, 160, 25);
        add(lWartosc);        
        
        lOdleglosc = new JLabel("Odleglość momentu [m]: ", JLabel.RIGHT);
        lOdleglosc.setBounds(10, 80, 160, 25);
        add(lOdleglosc);
        
        
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        tWartosc = new JTextField();
        tWartosc.setBounds(180, 50, 100, 25);
        add(tWartosc);
        tWartosc.setToolTipText("Podaj wartość momentu");
        
        tOdleglosc = new JTextField();
        tOdleglosc.setBounds(180, 80, 100, 25);
        add(tOdleglosc);
        tOdleglosc.setToolTipText("Podaj odleglość momentu od początku belki");
        
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
    
    public Moment(JFrame owner, int licznikp, ArrayList<Double> uWielkosc, ArrayList<Double> uOdleglosc){
        
        super(owner, "Usuwanie momentu", true);
        setLayout(null);
        setBounds(500, 400, 400, 200);
        
        
        // \/ tworzenie Labeli
        lWyswietl = new JLabel("Wartość momentu [kN*m]: ", JLabel.RIGHT);
        lWyswietl.setBounds(0, 50, 150, 25);
        add(lWyswietl);         
        
        lindex = new JLabel("Odleglość momentu [m]: ", JLabel.RIGHT);
        lindex.setBounds(0, 80, 150, 25);
        add(lindex);
                
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
            }
            else if(ilicznik == ilicznikprzekaz){
                ilicznik = 0;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
        }
        else if(zrodlo == bWstecz){
            if(ilicznik > 0){
                ilicznik--;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
            else if(ilicznik == 0){
                ilicznik = ilicznikprzekaz;
                tWartosc.setText(stWartosc.get(ilicznik));
                tOdleglosc.setText(stOdleglosc.get(ilicznik));
            }
        }
    
    }   

}
