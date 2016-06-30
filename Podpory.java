// Klasa tworzaca podpory


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Podpory extends JDialog implements ActionListener {
    
    private JButton bOk, bCancel;
    private JLabel lOdleglosc2, lOdleglosc1;
    private JTextField tOdleglosc1, tOdleglosc2;
    private ButtonGroup bgRodzajPodpory;
    private JRadioButton rbUtwierdzenie, rbDwiePodpory;
    private boolean okData;
    
    public int  irodzajpodpory;
    public double dOdleglosc2 = 0, iRa, iRb, dOdleglosc1 = 0, DlugoscBelki = 0;
    
    
    public Podpory(JFrame owner){
        
        super(owner, "Określanie podpór", true);
        setLayout(null);
        setBounds(500, 400, 300, 200); 

        // \/ tworzenie Labeli
        lOdleglosc1 = new JLabel("Odległość podpory 1: ", JLabel.RIGHT);
        lOdleglosc1.setBounds(10, 50, 140, 25);
        add(lOdleglosc1);        
        
        lOdleglosc2 = new JLabel("Odległość podpory 2: ", JLabel.RIGHT);
        lOdleglosc2.setBounds(10, 80, 140, 25);
        add(lOdleglosc2);
        
        
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        tOdleglosc1 = new JTextField();
        tOdleglosc1.setBounds(160, 50, 100, 25);
        add(tOdleglosc1);
        tOdleglosc1.setToolTipText("Podaj odległość podpory 1 od poczatku belki ");
        tOdleglosc1.setEnabled(false);
        
        tOdleglosc2 = new JTextField();
        tOdleglosc2.setBounds(160, 80, 100, 25);
        add(tOdleglosc2);
        tOdleglosc2.setToolTipText("Podaj odleglość podpory 2 od początku belki");
        tOdleglosc2.setEnabled(false);
        
        
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
        
        
        // \/ tworzenie RadioButtonow
        bgRodzajPodpory = new ButtonGroup();
        
        rbUtwierdzenie = new JRadioButton("Utwierdzenie", true);
        rbUtwierdzenie.setBounds(30, 20, 100, 25);
        bgRodzajPodpory.add(rbUtwierdzenie);
        add(rbUtwierdzenie);
        rbUtwierdzenie.addActionListener(this);
        
        rbDwiePodpory = new JRadioButton("Dwie podpory", true);
        rbDwiePodpory.setBounds(140, 20, 150, 25);
        bgRodzajPodpory.add(rbDwiePodpory);
        add(rbDwiePodpory);
        rbDwiePodpory.addActionListener(this);
        
        // /\ tworzenie RadioButtonow
        
        
    }
    
    public void setFocus(){
        
        tOdleglosc1.requestFocusInWindow();
    }
    
    public boolean isOk(){
        return okData;
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        Object zrodlo = e.getSource();
        
        if(zrodlo == rbUtwierdzenie){
            tOdleglosc1.setEnabled(false);
            tOdleglosc2.setEnabled(false);
            
        }
        else if(zrodlo == rbDwiePodpory){
            tOdleglosc1.setEnabled(true);
            tOdleglosc2.setEnabled(true);
        }
        else if(zrodlo == bOk){
            if(rbUtwierdzenie.isSelected()==true){
                okData = true;
                dOdleglosc1 = 0;
                dOdleglosc2 = 0;
                setVisible(false);
            }
            if(rbDwiePodpory.isSelected() == true){
                okData = true;
                dOdleglosc1 = Double.parseDouble(tOdleglosc1.getText());
                dOdleglosc2 = Double.parseDouble(tOdleglosc2.getText());
                setVisible(false);
            }
        }
        else if(zrodlo == bCancel){
            okData = false;
            setVisible(false);
        }
        
        
    }
    
}
