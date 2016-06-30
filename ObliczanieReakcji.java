
import javax.swing.JTextArea;




public class ObliczanieReakcji {
    
    private int pomoc2, pomoc3; 
    private double dOdlegloscOdRa =0;
    public int pomoc;
    public String sObliczanie;
    
    
    public ObliczanieReakcji(JTextArea taObliczanie, Sila P, Moment M, ObciazenieCiagle q, Podpory R, int iloscsuma){
        
        pomoc = 0;
        pomoc2 = 0;
        pomoc3 = 0;        
        taObliczanie.append("\nIlość sił skupionych: " + P.licznikp + "\n" + "Ilość momentów skupionych: " + M.licznikm + "\n" + "Ilość obciążeń ciągłych: " + q.licznikq + "\n");
        
        taObliczanie.append("\nObliczenie pierwszej niewiadomej reakcji w podporach: \n");
        taObliczanie.append("ΣM=0 => 0= ");
        
        if(R.irodzajpodpory == 1){
            
            taObliczanie.append("Ma ");
            while(pomoc <iloscsuma){
                
                if(pomoc < P.licznikp){
                    if(P.Wielkosc.get(pomoc)<0){
                        taObliczanie.append(P.Wielkosc.get(pomoc) + " *" + P.Odleglosc.get(pomoc) + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * P.Odleglosc.get(pomoc) * -1);
                        pomoc ++;
                    }
                    else if(P.Wielkosc.get(pomoc)>0){
                        taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " *" + P.Odleglosc.get(pomoc) + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * P.Odleglosc.get(pomoc) * -1);
                        pomoc ++;
                    }
                }
                else if(pomoc2 < M.licznikm){
                    if(M.Wartosc.get(pomoc2)<0){
                        taObliczanie.append(M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                }
                else if(pomoc3 < q.licznikq){
                        
                    if(q.Wartosc.get(pomoc3)<0){
                        taObliczanie.append(q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + q.Odleglosc.get(pomoc3) + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (q.Odleglosc.get(pomoc3) + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + q.Odleglosc.get(pomoc3) + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (q.Odleglosc.get(pomoc3) + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                        }
                }
                else if(pomoc3 == q.licznikq){
                    break;
                }    
                
            }
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkość momentu w utwierdzeniu: Mu = " + R.iRb + ". \n");
            
        }
        else if(R.irodzajpodpory ==2){
            dOdlegloscOdRa = R.dOdleglosc2 - R.dOdleglosc1;
            taObliczanie.append("Rb *" + dOdlegloscOdRa);
            
            while(pomoc<iloscsuma){
                
                if(pomoc < P.licznikp){
                    dOdlegloscOdRa = P.Odleglosc.get(pomoc)-R.dOdleglosc1;
                    if(P.Wielkosc.get(pomoc)<0){
                        taObliczanie.append(P.Wielkosc.get(pomoc) + " *" + dOdlegloscOdRa + " ");
                        R.iRb = R.iRb +((P.Wielkosc.get(pomoc) * dOdlegloscOdRa)* -1);
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " *" + dOdlegloscOdRa + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * dOdlegloscOdRa * -1);
                        pomoc++;
                    }
                }
                else if(pomoc2 < M.licznikm){
                    dOdlegloscOdRa = M.Odleglosc.get(pomoc2) -R.dOdleglosc1;
                    if(M.Wartosc.get(pomoc2)<0){
                        taObliczanie.append(M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                            
                    }
                    else{
                        taObliczanie.append("+" + M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                }
                else if(pomoc3 < q.licznikq){
                    dOdlegloscOdRa = q.Odleglosc.get(pomoc3) -R.dOdleglosc1;
                    if(q.Wartosc.get(pomoc3) <0){
                        taObliczanie.append(q.Wartosc.get(pomoc3)+ " *" + q.Dlugosc.get(pomoc3) + " *(" + dOdlegloscOdRa + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (dOdlegloscOdRa + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + dOdlegloscOdRa + " +" + q.Dlugosc.get(pomoc3) + "/2 )");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (dOdlegloscOdRa + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                }
                else if(pomoc3 == q.licznikq){
                    break;
                }
                
            }
        
        R.iRb = R.iRb / (R.dOdleglosc2-R.dOdleglosc1);
        taObliczanie.append(". \n");
        taObliczanie.append("Wielkość pierwszej reakcji: Rb= " + R.iRb + ". \n");
        }
        
        
        // Obliczanie drugiej reakcji
        pomoc = 0;
        pomoc2 = 0;
        iloscsuma = P.licznikp + q.licznikq;
        R.iRa = 0;
        
        taObliczanie.append("\nObliczenie drugiej reakcji: \n");
        taObliczanie.append("ΣY=0 => 0= ");
        taObliczanie.append("Ra ");
        
        for(pomoc=0;pomoc<iloscsuma;pomoc++){
            if(pomoc<P.licznikp){
                if(P.Wielkosc.get(pomoc)<0){
                    taObliczanie.append(P.Wielkosc.get(pomoc) + " ");
                    R.iRa = R.iRa + (P.Wielkosc.get(pomoc)*-1);
                }
                else{
                    taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " ");
                    R.iRa = R.iRa + (P.Wielkosc.get(pomoc)*-1);
                }
              
            }
            else if(pomoc2<q.licznikq){
                if(q.Wartosc.get(pomoc2)<0){
                    taObliczanie.append(q.Wartosc.get(pomoc2) + " *" + q.Dlugosc.get(pomoc2)+ " ");
                    R.iRa = R.iRa + (q.Wartosc.get(pomoc2)*q.Dlugosc.get(pomoc2)*-1);
                    pomoc2++;
                }
                else{
                    taObliczanie.append("+" + q.Wartosc.get(pomoc2) + " *" + q.Dlugosc.get(pomoc2) + " ");
                    R.iRa = R.iRa + (q.Wartosc.get(pomoc2)*q.Dlugosc.get(pomoc2)*-1);
                    pomoc2++;
                }
                
            }
            
        }
        
        if(R.irodzajpodpory == 1)
        {
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkość siły skupionej w utwierdzeniu: Ra= " + R.iRa);
            
        }
        else if(R.irodzajpodpory == 2){
            R.iRa = R.iRa - R.iRb;
            if(R.iRb < 0){
            taObliczanie.append(""+ R.iRb);
            }
            else{
                taObliczanie.append("+" + R.iRb);
            }
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkość drugiej reakcji Ra = " + R.iRa);
        }
        
        
    }
 
    public ObliczanieReakcji(JTextArea taObliczanie, Sila P, Moment M, Podpory R, int iloscsuma){
        
        pomoc = 0;
        pomoc2 = 0;
        taObliczanie.append("\nIlość sił: " + P.licznikp + "\n" + "Ilość Momentów: " + M.licznikm + "\n");
        
        taObliczanie.append("\nObliczanie pierwszej niewiadomej reakcji w podporach: \n");
        taObliczanie.append("ΣM(Pi): 0 = ");
        
        if(R.irodzajpodpory == 1){
            
            taObliczanie.append("Ma ");
            while(pomoc <iloscsuma){
                
                if(pomoc < P.licznikp){
                    if(P.Wielkosc.get(pomoc)<0){
                        taObliczanie.append(P.Wielkosc.get(pomoc) + " *" + P.Odleglosc.get(pomoc) + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * P.Odleglosc.get(pomoc) * -1);
                        pomoc ++;
                    }
                    else if(P.Wielkosc.get(pomoc)>0){
                        taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " *" + P.Odleglosc.get(pomoc) + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * P.Odleglosc.get(pomoc) * -1);
                        pomoc ++;
                    }
                }
                else if(pomoc2 < M.licznikm){
                    if(M.Wartosc.get(pomoc2)<0){
                        taObliczanie.append(M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                }
                
                else if(pomoc2 == M.licznikm){
                    break;
                }    
                
            }
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc Momentu w utwierdzeniu: " + R.iRb + ". \n");
            
        }
        else if(R.irodzajpodpory ==2){
            dOdlegloscOdRa = R.dOdleglosc2 - R.dOdleglosc1;
            taObliczanie.append("Rb *" + dOdlegloscOdRa);
            
            while(pomoc<iloscsuma){
                
                if(pomoc < P.licznikp){
                    dOdlegloscOdRa = P.Odleglosc.get(pomoc)-R.dOdleglosc1;
                    if(P.Wielkosc.get(pomoc)<0){
                        taObliczanie.append(P.Wielkosc.get(pomoc) + " *" + dOdlegloscOdRa + " ");
                        R.iRb = R.iRb +((P.Wielkosc.get(pomoc) * dOdlegloscOdRa)* -1);
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " *" + dOdlegloscOdRa + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * dOdlegloscOdRa * -1);
                        pomoc++;
                    }
                }
                else if(pomoc2 < M.licznikm){
                    dOdlegloscOdRa = M.Odleglosc.get(pomoc2) -R.dOdleglosc1;
                    if(M.Wartosc.get(pomoc2)<0){
                        taObliczanie.append(M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                            
                    }
                    else{
                        taObliczanie.append("+" + M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                }
                
                else if(pomoc3 == M.licznikm){
                    break;
                }
                
            }
        
        R.iRb = R.iRb / (R.dOdleglosc2-R.dOdleglosc1);
        taObliczanie.append(". \n");
        taObliczanie.append("Wielkosc pierwszej reakcji Rb: " + R.iRb);
        }
        
        
        // Obliczanie drugiej reakcji
        pomoc = 0;
        iloscsuma = P.licznikp;
        
        taObliczanie.append("\nObliczanie drugiej reakcji: \n");
        taObliczanie.append("ΣPi: 0= ");
        taObliczanie.append("Ra ");
        
        for(pomoc=0;pomoc<iloscsuma;pomoc++){
            if(pomoc<P.licznikp){
                if(P.Wielkosc.get(pomoc)<0){
                    taObliczanie.append(P.Wielkosc.get(pomoc) + " ");
                    R.iRa = R.iRa + (P.Wielkosc.get(pomoc)*-1);
                }
                else{
                    taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " ");
                    R.iRa = R.iRa + (P.Wielkosc.get(pomoc)*-1);
                }
              
            }    
        }
        
        if(R.irodzajpodpory == 1)
        {
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc reakcji Ra = " + R.iRa);
            
        }
        else if(R.irodzajpodpory == 2){
            R.iRa = R.iRa - R.iRb;
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc reakcji Ra = " + R.iRa);
        }
        
    }

    public ObliczanieReakcji(JTextArea taObliczanie, Sila P, ObciazenieCiagle q, Podpory R, int iloscsuma){
        pomoc = 0;
        pomoc2 = 0;
        pomoc3 = 0;        
        taObliczanie.append("Ilość sił: " + P.licznikp + "\n" + "Ilość Obciążeń Ciągłych: " + q.licznikq + "\n");
        
        taObliczanie.append("Obliczanie pierwszej niewiadomej reakcji w podporach: \n");
        taObliczanie.append("Suma Momentów: 0 = ");
        
        if(R.irodzajpodpory == 1){
            
            taObliczanie.append("Ma ");
            while(pomoc <iloscsuma){
                
                if(pomoc < P.licznikp){
                    if(P.Wielkosc.get(pomoc)<0){
                        taObliczanie.append(P.Wielkosc.get(pomoc) + " *" + P.Odleglosc.get(pomoc) + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * P.Odleglosc.get(pomoc) * -1);
                        pomoc ++;
                    }
                    else if(P.Wielkosc.get(pomoc)>0){
                        taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " *" + P.Odleglosc.get(pomoc) + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * P.Odleglosc.get(pomoc) * -1);
                        pomoc ++;
                    }
                }
                else if(pomoc3 < q.licznikq){
                        
                    if(q.Wartosc.get(pomoc3)<0){
                        taObliczanie.append(q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + q.Odleglosc.get(pomoc3) + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (q.Odleglosc.get(pomoc3) + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + q.Odleglosc.get(pomoc3) + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (q.Odleglosc.get(pomoc3) + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                        }
                }
                else if(pomoc3 == q.licznikq){
                    break;
                }    
                
            }
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc Momentu w utwierdzeniu: " + R.iRb + ". \n");
            
        }
        else if(R.irodzajpodpory ==2){
            dOdlegloscOdRa = R.dOdleglosc2 - R.dOdleglosc1;
            taObliczanie.append("Rb *" + dOdlegloscOdRa);
            
            while(pomoc<iloscsuma){
                
                if(pomoc < P.licznikp){
                    dOdlegloscOdRa = P.Odleglosc.get(pomoc)-R.dOdleglosc1;
                    if(P.Wielkosc.get(pomoc)<0){
                        taObliczanie.append(P.Wielkosc.get(pomoc) + " *" + dOdlegloscOdRa + " ");
                        R.iRb = R.iRb +((P.Wielkosc.get(pomoc) * dOdlegloscOdRa)* -1);
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " *" + dOdlegloscOdRa + " ");
                        R.iRb = R.iRb + (P.Wielkosc.get(pomoc) * dOdlegloscOdRa * -1);
                        pomoc++;
                    }
                }
                else if(pomoc3 < q.licznikq){
                    dOdlegloscOdRa = q.Odleglosc.get(pomoc3) -R.dOdleglosc1;
                    if(q.Wartosc.get(pomoc3) <0){
                        taObliczanie.append(q.Wartosc.get(pomoc3)+ " *" + q.Dlugosc.get(pomoc3) + " *(" + dOdlegloscOdRa + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (dOdlegloscOdRa + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + dOdlegloscOdRa + " +" + q.Dlugosc.get(pomoc3) + "/2 )");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (dOdlegloscOdRa + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                }
                else if(pomoc3 == q.licznikq){
                    break;
                }
                
            }
        
        R.iRb = R.iRb / (R.dOdleglosc2-R.dOdleglosc1);
        taObliczanie.append(". \n");
        taObliczanie.append("Wielkosc pierwszej reakcji Rb: " + R.iRb);
        }
        
        
        
        //  \/ Obliczanie drugiej reakcji
        pomoc = 0;
        iloscsuma = P.licznikp + q.licznikq;
        
        taObliczanie.append("Obliczanie drugiej reakcji: \n");
        taObliczanie.append("Suma reakcji: 0= ");
        taObliczanie.append("Ra ");
        
        for(pomoc=0;pomoc<iloscsuma;pomoc++){
            if(pomoc<P.licznikp){
                if(P.Wielkosc.get(pomoc)<0){
                    taObliczanie.append(P.Wielkosc.get(pomoc) + " ");
                    R.iRa = R.iRa + (P.Wielkosc.get(pomoc)*-1);
                }
                else{
                    taObliczanie.append("+" + P.Wielkosc.get(pomoc) + " ");
                    R.iRa = R.iRa + (P.Wielkosc.get(pomoc)*-1);
                }
              
            }
            else if(pomoc2<q.licznikq){
                if(q.Wartosc.get(pomoc2)<0){
                    taObliczanie.append(q.Wartosc.get(pomoc2) + " *" + q.Dlugosc.get(pomoc2)+ " ");
                    R.iRa = R.iRa + (q.Wartosc.get(pomoc2)*q.Dlugosc.get(pomoc2)*-1);
                    pomoc2++;
                }
                else{
                    taObliczanie.append("+" + q.Wartosc.get(pomoc2) + " *" + q.Dlugosc.get(pomoc2) + " ");
                    R.iRa = R.iRa + (q.Wartosc.get(pomoc2)*q.Dlugosc.get(pomoc2)*-1);
                    pomoc2++;
                }
                
            }
            
        }
        
        if(R.irodzajpodpory == 1)
        {
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc reakcji Ra = " + R.iRa);
            
        }
        else if(R.irodzajpodpory == 2){
            R.iRa = R.iRa - R.iRb;
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc reakcji Ra = " + R.iRa);
        }
    }

    public ObliczanieReakcji(JTextArea taObliczanie, Moment M, ObciazenieCiagle q, Podpory R, int iloscsuma){
        
        pomoc = 0;
        pomoc2 = 0;
        pomoc3 = 0;        
        taObliczanie.append("Ilość Momentów: " + M.licznikm + "\n" + "Ilość Obciążeń Ciągłych: " + q.licznikq + "\n");
        
        taObliczanie.append("Obliczanie pierwszej niewiadomej reakcji w podporach: \n");
        taObliczanie.append("Suma Momentów: 0 = ");
        
        if(R.irodzajpodpory == 1){
            
            taObliczanie.append("Ma ");
            while(pomoc <iloscsuma){
                
                if(pomoc2 < M.licznikm){
                    if(M.Wartosc.get(pomoc2)<0){
                        taObliczanie.append(M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                }
                else if(pomoc3 < q.licznikq){
                        
                    if(q.Wartosc.get(pomoc3)<0){
                        taObliczanie.append(q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + q.Odleglosc.get(pomoc3) + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (q.Odleglosc.get(pomoc3) + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + q.Odleglosc.get(pomoc3) + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (q.Odleglosc.get(pomoc3) + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                        }
                }
                else if(pomoc3 == q.licznikq){
                    break;
                }    
                
            }
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc Momentu w utwierdzeniu: " + R.iRb + ". \n");
            
        }
        else if(R.irodzajpodpory ==2){
            dOdlegloscOdRa = R.dOdleglosc2 - R.dOdleglosc1;
            taObliczanie.append("Rb *" + dOdlegloscOdRa);
            
            while(pomoc<iloscsuma){
                
                if(pomoc2 < M.licznikm){
                    dOdlegloscOdRa = M.Odleglosc.get(pomoc2) -R.dOdleglosc1;
                    if(M.Wartosc.get(pomoc2)<0){
                        taObliczanie.append(M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                            
                    }
                    else{
                        taObliczanie.append("+" + M.Wartosc.get(pomoc2) + " ");
                        R.iRb = R.iRb + (M.Wartosc.get(pomoc2) * -1);
                        pomoc2++;
                        pomoc++;
                    }
                }
                else if(pomoc3 < q.licznikq){
                    dOdlegloscOdRa = q.Odleglosc.get(pomoc3) -R.dOdleglosc1;
                    if(q.Wartosc.get(pomoc3) <0){
                        taObliczanie.append(q.Wartosc.get(pomoc3)+ " *" + q.Dlugosc.get(pomoc3) + " *(" + dOdlegloscOdRa + " +" + q.Dlugosc.get(pomoc3) + "/2) ");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (dOdlegloscOdRa + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                    else{
                        taObliczanie.append("+" + q.Wartosc.get(pomoc3) + " *" + q.Dlugosc.get(pomoc3) + " *(" + dOdlegloscOdRa + " +" + q.Dlugosc.get(pomoc3) + "/2 )");
                        R.iRb = R.iRb + (q.Wartosc.get(pomoc3) * q.Dlugosc.get(pomoc3) * (dOdlegloscOdRa + q.Dlugosc.get(pomoc3) /2) * -1);
                        pomoc3++;
                        pomoc++;
                    }
                }
                else if(pomoc3 == q.licznikq){
                    break;
                }
                
            }
        
        R.iRb = R.iRb / (R.dOdleglosc2-R.dOdleglosc1);
        taObliczanie.append(". \n");
        taObliczanie.append("Wielkosc pierwszej reakcji Rb: " + R.iRb);
        }
        
        
        // Obliczanie drugiej reakcji
        pomoc = 0;
        iloscsuma = q.licznikq;
        
        taObliczanie.append("Obliczanie drugiej reakcji: \n");
        taObliczanie.append("Suma reakcji: 0= ");
        taObliczanie.append("Ra ");
        
        for(pomoc=0;pomoc<iloscsuma;pomoc++){            
            if(pomoc2<q.licznikq){
                if(q.Wartosc.get(pomoc2)<0){
                    taObliczanie.append(q.Wartosc.get(pomoc2) + " *" + q.Dlugosc.get(pomoc2)+ " ");
                    R.iRa = R.iRa + (q.Wartosc.get(pomoc2)*q.Dlugosc.get(pomoc2)*-1);
                    pomoc2++;
                }
                else{
                    taObliczanie.append("+" + q.Wartosc.get(pomoc2) + " *" + q.Dlugosc.get(pomoc2) + " ");
                    R.iRa = R.iRa + (q.Wartosc.get(pomoc2)*q.Dlugosc.get(pomoc2)*-1);
                    pomoc2++;
                }
                
            }
            
        }
        
        if(R.irodzajpodpory == 1)
        {
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc reakcji Ra = " + R.iRa);
            
        }
        else if(R.irodzajpodpory == 2){
            R.iRa = R.iRa - R.iRb;
            taObliczanie.append(". \n");
            taObliczanie.append("Wielkosc reakcji Ra = " + R.iRa);
        }
        
    }

}
