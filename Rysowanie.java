
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Szynal L
 */
class Rysowanie extends JPanel {
        private int ix1 = -1, iy1, ix2 = -1, iy2;
        private int iPomoc=0, iDlugosc = 0, iPomoc2 = 0, odliczanie=0;
        private double dPomoc = 0;
        private Graphics g;
        
        public Rysowanie(){
            
        }
        
        public void belka(double dBelka){
            iDlugosc = (int) dBelka;
            
            ix1 = 10;
            ix2 = 300;
            iy1 = 75;
            iy2 = iy1;
            getGraphics().drawLine(ix1, iy1, ix2, iy2);
            
         
        }
        
        public void sila(Sila P, int licznikp){
            dPomoc = P.Odleglosc.get(licznikp);
            iPomoc = (int)dPomoc;
            iPomoc = procenty(iPomoc);
            ix1 = iPomoc;
            ix2 = iPomoc;
            iy2 = 30;
            iy1 = 75;
            
            
            g = this.getGraphics();
            g.setColor(Color.red);
            if(P.Wielkosc.get(licznikp)<0){
                
                g.drawLine(ix1, iy1, ix2, iy2);
                ix2 = ix1-10;
                iy2 = 65;
                g.drawLine(ix1, iy1, ix2, iy2);
                iPomoc2 = iPomoc +20;
                ix2 = ix1+10;
                g.drawLine(ix1, iy1, ix2, iy2);
            }
            else{
                g.drawLine(ix1, iy1, ix2, iy2);
                ix2 = ix1-10;
                iy1 = 40;
                g.drawLine(ix1, iy2, ix2, iy1);
                iPomoc2 = iPomoc +20;
                ix2 = ix1+10;
                g.drawLine(ix1, iy2, ix2, iy1);
                iy1 = 75;
            }
        }
        
        public void moment(Moment M, int licznikm){
            dPomoc = M.Odleglosc.get(licznikm);
            iPomoc = (int)dPomoc;
            iPomoc = procenty(iPomoc);
            
            
            g = this.getGraphics();
            g.setColor(Color.BLUE);
            if(M.Wartosc.get(licznikm)>0){
                ix1 = iPomoc;
                ix2 = ix1 +15;
                iy2 = 30;
                iy1 = 75;
                g.drawLine(ix1, iy1, ix2, iy2);
                ix1 = ix1-15;
                g.drawLine(ix1, iy2, ix2, iy2);
                ix2 = ix1+10;
                iy1 = 30 + 10;
                g.drawLine(ix1, iy2, ix2, iy1);
                iy1 = 30 -10;
                g.drawLine(ix1, iy2, ix2, iy1);
            
                iy1 = 75;
                iy2 = 30;
            }
            else{
                ix1 = iPomoc;
                ix2 = ix1 -15;
                iy2 = 30;
                iy1 = 75;
                g.drawLine(ix1, iy1, ix2, iy2);
                ix1 = ix1+15;
                g.drawLine(ix1, iy2, ix2, iy2);
                ix2 = ix1-10;
                iy1 = 30 + 10;
                g.drawLine(ix1, iy2, ix2, iy1);
                iy1 = 30 -10;
                g.drawLine(ix1, iy2, ix2, iy1);
            
                iy1 = 75;
                iy2 = 30;
            }
        }
        
        public void obciazenieciagle(ObciazenieCiagle q, int licznikq){
            dPomoc = q.Odleglosc.get(licznikq);
            iPomoc = (int)dPomoc;
            iPomoc = procenty(iPomoc);
            
            dPomoc = q.Dlugosc.get(licznikq)+ q.Odleglosc.get(licznikq);
            iPomoc2 = (int)dPomoc;
            iPomoc2 = procenty(iPomoc2);
            
            g = this.getGraphics();
            g.setColor(Color.GREEN);
            if(q.Wartosc.get(licznikq)>0){
                // rysowanie kwadratu
                ix1 = iPomoc;
                ix2 = iPomoc2 - iPomoc;
                iy2 = 15;
                iy1 = 76;
                g.drawRect(ix1, iy1, ix2, iy2);
                
                //Rysowanie szczalki na poczatku
                ix2 = iPomoc - 10;
                iy2 = 75+15;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                ix2 = iPomoc + 10;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                // rysowanie prostych
                for(int i= 10;ix1 < iPomoc2; i=i+20){
                    ix2 = ix1;
                    iy2 = 75+15;
                    g.drawLine(ix1, iy1, ix2, iy2);
                    
                    ix1 = ix1+20;
                }
                
                //ryswoanie szczalki na koncu kwadratu
                if(ix1 > iPomoc2){
                    ix1 = iPomoc2;
                    ix2 = iPomoc2 - 10;
                    iy2 = 75+15;
                    g.drawLine(ix1, iy1, ix2, iy2);
                
                    ix2 = iPomoc2 + 10;
                    g.drawLine(ix1, iy1, ix2, iy2);
                }
                
                
                iy1 = 75;
                iy2 = 30;
            }
            else{
                ix1 = iPomoc;
                ix2 = iPomoc2- iPomoc;
                iy1 = 60;
                iy2 = 14;
                g.drawRect(ix1, iy1, ix2, iy2);
                
                //Rysowanie szczalki na poczatku
                ix2 = iPomoc - 10;
                iy1 = 74;
                iy2 = 65;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                ix2 = iPomoc + 10;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                // rysowanie prostych
                for(int i= 10;ix1 < iPomoc2; i=i+20){
                    ix2 = ix1;
                    iy2 = 60;
                    g.drawLine(ix1, iy1, ix2, iy2);
                    
                    ix1 = ix1+20;
                    
                }
                
                if(ix1 > iPomoc2){
                    ix1 = iPomoc2;
                    ix2 = iPomoc2 - 10;
                    iy2 = 65;
                    g.drawLine(ix1, iy1, ix2, iy2);
                
                    ix2 = iPomoc2 + 10;
                    g.drawLine(ix1, iy1, ix2, iy2);
                }
                iy1 = 75;
                iy2 = 30;
            }
        }
        
        public void podpory(Podpory R){
            g = this.getGraphics();
            g.setColor(Color.ORANGE);
            if(R.irodzajpodpory == 1){
                
                //rysowanie Mu
                ix1 = 10;
                ix2 = ix1 + 10;
                iy1 = 75;
                iy2 = 30;
                g.drawLine(ix1, iy1, ix2, iy2);
                ix1 = ix1-10;
                g.drawLine(ix1, iy2, ix2, iy2);
                ix2 = ix1+10;
                iy1 = 30 + 10;
                g.drawLine(ix1, iy2, ix2, iy1);
                iy1 = 30 -10;
                g.drawLine(ix1, iy2, ix2, iy1);
                
                //rysowanie Ra
                ix1 = 10;
                ix2 = ix1 +10;
                iy1 = 45;
                iy2 = 60;
                g.drawLine(ix1, iy1, ix2, iy2);
                ix2 = ix1-10;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                
                //rysowanie utwierdzenia
                ix1 = 10;
                ix2 = 10;
                iy1 = 45;
                iy2 = 110;
                g.drawLine(ix1, iy1, ix2, iy2);
                iy1 = 65;
                iy2 = 75;
                for(int i=0;i<4;i++){
                    
                    ix2 = 0;
                    g.drawLine(ix1, iy1, ix2, iy2);
                    iy1 = iy1+10;
                    iy2 = iy2+10;
                    
                }
                
            }
            else if(R.irodzajpodpory == 2){
                dPomoc = R.dOdleglosc1;
                iPomoc = (int)dPomoc;
                iPomoc = procenty(iPomoc);
                
                
                // rysowanie Ra
                ix1 = iPomoc;
                ix2 = iPomoc -10;
                iy1 = 75;
                iy2 = 110;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                ix2 = iPomoc + 10;
                g.drawLine(ix1, iy1, ix2, iy2);
                ix1 = iPomoc - 10;
                g.drawLine(ix1, iy2, ix2, iy2);
                
                dPomoc = R.dOdleglosc2;
                iPomoc = (int)dPomoc;
                iPomoc = procenty(iPomoc);
                
                // ryswanie Rb
                ix1 = iPomoc;
                ix2 = iPomoc -10;
                iy1 = 75;
                iy2 = 110;
                g.drawLine(ix1, iy1, ix2, iy2);
                
                ix2 = iPomoc + 10;
                g.drawLine(ix1, iy1, ix2, iy2);
                ix1 = iPomoc - 10;
                g.drawLine(ix1, iy2, ix2, iy2);
                iy1 = iy2 +10;
                g.drawLine(ix1, iy1, ix2, iy1);
            }
        }
        
        public void odswiez(){
            repaint();
        }
        
        public int procenty(int i){
            dPomoc = (i *100)/iDlugosc;
            dPomoc = (300*dPomoc)/100;
            i = (int) dPomoc;
            if(i==0){
                i=10;
            }
            return i;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawLine(ix1, iy1, ix2, iy2);
        }
}

