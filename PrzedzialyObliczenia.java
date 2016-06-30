
import java.util.ArrayList;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Szynal L
 */
public class PrzedzialyObliczenia {
    
    private int odliczanie;
    private double dObliczenia, dObliczeniax2, dObliczeniax, pomoc3=0;
    
    public int IloscPrzedzialow = 0, IloscSuma;
    public double GranicePrzedzialow[];
    public ArrayList<Double> Mg, Mgx, Mgx2;
    
    public PrzedzialyObliczenia(JTextArea taObliczenia, Sila P, Moment M, ObciazenieCiagle q, Podpory R, int iloscsuma){
        
        odliczanie = 0;
        IloscPrzedzialow = 0;
        IloscSuma = iloscsuma;
        System.out.println("Ilosc suma = " + IloscSuma);
        
        for(int i=0;i<P.licznikp;i++){
            for(int y=0;y<M.licznikm;y++){
                if(P.Odleglosc.get(i).equals(M.Odleglosc.get(y)) && P.Odleglosc.get(i)>0){
                    IloscPrzedzialow++;
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(P.Odleglosc.get(i)== q.Odleglosc.get(y) && P.Odleglosc.get(i) >0){
                    for(int z=0;z<M.licznikm;z++){
                        if(P.Odleglosc.get(i) !=M.Odleglosc.get(z)){
                            IloscPrzedzialow++;
                            odliczanie++;
                        }

                    }
                }
            }
        }
        for(int i=0;i<M.licznikm;i++){
            for(int y=0;y<q.licznikq;y++){
                if(M.Odleglosc.get(i)==q.Odleglosc.get(y) && M.Odleglosc.get(i)>0){
                    IloscPrzedzialow++;
                }
            }            
        }
        
        
        odliczanie = 0;
        for(int i=0;i<P.licznikp;i++){
            if(P.Odleglosc.get(i)==R.DlugoscBelki){
                odliczanie++;
            }
        }
        if(odliczanie == 0){
            for(int i=0;i<M.licznikm;i++){
                if(M.Odleglosc.get(i)==R.DlugoscBelki){
                    odliczanie++;
                }
            }
            
        }/*
        if(odliczanie == 0){
            if(R.dOdleglosc1==R.DlugoscBelki){
               odliczanie++;
            }
            else if(R.dOdleglosc2==R.DlugoscBelki){
                odliczanie++;
            }
        }*/
        if(odliczanie ==0){
            for(int i=0;i<q.licznikq;i++){
                if(q.Odleglosc.get(i)==R.DlugoscBelki){
                    odliczanie++;
                }
            }
        }
        if(odliczanie >0){
            IloscPrzedzialow = IloscPrzedzialow+1;
        }
        odliczanie = 0;
        for(int i=0;i<q.licznikq;i++){
            pomoc3 = q.Odleglosc.get(i)+q.Dlugosc.get(i);
            for(int y=0;y<P.licznikp;y++){
                if(P.Odleglosc.get(y)==pomoc3){
                    odliczanie++;
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(M.Odleglosc.get(y)==pomoc3){
                    odliczanie++;
                }
            }/*
            if(R.dOdleglosc1 == pomoc3){
                odliczanie++;
            }
            else if(R.dOdleglosc2 == pomoc3){
                odliczanie++;
            }*/
            if(R.DlugoscBelki == pomoc3){
                odliczanie++;
            }
            if(R.DlugoscBelki == 0){
                odliczanie++;
            }
        }
        if(odliczanie > 0){
            IloscPrzedzialow = IloscPrzedzialow+1;
            odliczanie =0;
        }
        System.out.println("IloscPrzedzialow = " + IloscPrzedzialow);
        IloscPrzedzialow = iloscsuma - IloscPrzedzialow;
        taObliczenia.append("\n \nIlość przedziałów: " + IloscPrzedzialow + ". \n");
        
        
        
        
        // obliczanie przedzialow:
        
        GranicePrzedzialow = new double[IloscPrzedzialow+1];
        
        GranicePrzedzialow[1] = R.DlugoscBelki;
        GranicePrzedzialow[0] = 0;
        
        for(int z=0;z<IloscPrzedzialow;z++){
            GranicePrzedzialow[z+1] = R.DlugoscBelki;
            for(int i=0;i<P.licznikp;i++){
                if(P.Odleglosc.get(i)<GranicePrzedzialow[z+1] && P.Odleglosc.get(i)>GranicePrzedzialow[z]){
                    GranicePrzedzialow[z+1] = P.Odleglosc.get(i);
                }
            }
            for(int i=0;i<M.licznikm;i++){
                if(M.Odleglosc.get(i)<GranicePrzedzialow[z+1] && M.Odleglosc.get(i)>GranicePrzedzialow[z]){
                    GranicePrzedzialow[z+1] = M.Odleglosc.get(i);
                }
            }
            for(int i=0;i<q.licznikq;i++){
                if(q.Odleglosc.get(i) < GranicePrzedzialow[z+1] && q.Odleglosc.get(i)> GranicePrzedzialow[z]){
                    GranicePrzedzialow[z+1] = q.Odleglosc.get(i);
                }
            }
            for(int i=0;i<q.licznikq;i++){
                if(pomoc3 < GranicePrzedzialow[z+1] && pomoc3 > GranicePrzedzialow[z]){
                    GranicePrzedzialow[z+1] = pomoc3;
                }
            }
           
                if(R.dOdleglosc1 < GranicePrzedzialow[z+1] && R.dOdleglosc1>GranicePrzedzialow[z]){
                    GranicePrzedzialow[z+1] = R.dOdleglosc1;
                }
                else if(R.dOdleglosc2 < GranicePrzedzialow[z+1] && R.dOdleglosc2 > GranicePrzedzialow[z]){
                    GranicePrzedzialow[z+1] = R.dOdleglosc2;
                }
            
                
            taObliczenia.append( z+1 + " przedział: (" + GranicePrzedzialow[z] + " , " + GranicePrzedzialow[z+1] + ")\n"); 
        }
        
        // /\ Obliczenia przedziałów 
        
        
        // \/ Obliczenia Reakcji w przecieciach
        Mg = new ArrayList<>();
        Mgx = new ArrayList<>();
        Mgx2 = new ArrayList<>();
        dObliczenia = 0;
        dObliczeniax = 0;
        dObliczeniax2 = 0;
        
        
      for(int i=0;i<IloscPrzedzialow;i++){ 
        taObliczenia.append("\n\n x: (" + GranicePrzedzialow[i] + ", " + GranicePrzedzialow[i+1] + ") ");
        taObliczenia.append("\n ΣM=0 => 0= Mg" + (i+1) + "(x) ");
        dObliczenia = 0;
        dObliczeniax = 0;
        dObliczeniax2 = 0;
        
        
        for(int pomoc=0;pomoc<P.licznikp;pomoc++){
            
            if(P.Odleglosc.get(pomoc)<GranicePrzedzialow[i+1]){
                if(P.Wielkosc.get(pomoc)<0){
                    taObliczenia.append("+" + P.Wielkosc.get(pomoc)*-1 + " *(x-" + P.Odleglosc.get(pomoc) + ") ");
                    dObliczeniax = dObliczeniax + P.Wielkosc.get(pomoc);
                    dObliczenia = dObliczenia + (P.Wielkosc.get(pomoc)*P.Odleglosc.get(pomoc)*-1);
                    
                }
                else{
                    taObliczenia.append("-" + P.Wielkosc.get(pomoc) + " *(x-" + P.Odleglosc.get(pomoc) + ") ");
                    dObliczeniax = dObliczeniax + P.Wielkosc.get(pomoc);
                    dObliczenia = dObliczenia + (P.Wielkosc.get(pomoc)*P.Odleglosc.get(pomoc)*-1);
                }
                
            }
        }
        for(int pomoc = 0;pomoc<M.licznikm;pomoc++){
            if(M.Odleglosc.get(pomoc)<GranicePrzedzialow[i+1]){
                if(M.Wartosc.get(pomoc)<0){
                    taObliczenia.append(M.Wartosc.get(pomoc) + " " );
                    dObliczenia = dObliczenia + (M.Wartosc.get(pomoc)*-1);
                }
                else{
                    taObliczenia.append("+" + M.Wartosc.get(pomoc) + " ");
                    dObliczenia = dObliczenia + (M.Wartosc.get(pomoc)*-1);
                }
                
            }

        }
        for(int pomoc = 0;pomoc<q.licznikq;pomoc++){
            if(q.Odleglosc.get(pomoc)<GranicePrzedzialow[i+1] && q.Odleglosc.get(pomoc) + q.Dlugosc.get(pomoc) >= GranicePrzedzialow[i+1]){
                if(q.Wartosc.get(pomoc)<0){
                    taObliczenia.append("+" +q.Wartosc.get(pomoc)*-1 + "/2 *(x- "+ q.Odleglosc.get(pomoc) + ")*(x- "+ q.Odleglosc.get(pomoc) + ") ");
                    dObliczeniax2 = dObliczeniax2 + (((double)q.Wartosc.get(pomoc) / 2));
                    dObliczeniax = dObliczeniax + (((double)q.Wartosc.get(pomoc) / 2)*-2*q.Odleglosc.get(pomoc));
                    dObliczenia = dObliczenia + (((double)q.Wartosc.get(pomoc) / 2)*(q.Odleglosc.get(pomoc)*q.Odleglosc.get(pomoc)));
                }
                else{
                    taObliczenia.append("-" + q.Wartosc.get(pomoc) + "/2 *(x- "+ q.Odleglosc.get(pomoc) + ")*(x- "+ q.Odleglosc.get(pomoc) + ") ");
                    dObliczeniax2 = dObliczeniax2 + (((double)q.Wartosc.get(pomoc) / 2));
                    dObliczeniax = dObliczeniax + (((double)q.Wartosc.get(pomoc) / 2)*-2*q.Odleglosc.get(pomoc));
                    dObliczenia = dObliczenia + (((double)q.Wartosc.get(pomoc) / 2)*(q.Odleglosc.get(pomoc)*q.Odleglosc.get(pomoc)));
                }
                
            }
            if(q.Odleglosc.get(pomoc)<GranicePrzedzialow[i+1] && q.Odleglosc.get(pomoc) + q.Dlugosc.get(pomoc) < GranicePrzedzialow[i+1]){
                if(q.Wartosc.get(pomoc)<0){
                    taObliczenia.append("+" +q.Wartosc.get(pomoc)*-1 + " *(x- "+ q.Odleglosc.get(pomoc) + "- " + q.Dlugosc.get(pomoc) + "/2 ");
                    dObliczeniax = dObliczeniax + ((double)q.Wartosc.get(pomoc) * (double)q.Dlugosc.get(pomoc));
                    dObliczenia = dObliczenia + (((double)q.Wartosc.get(pomoc) * (double)q.Dlugosc.get(pomoc))*((double)q.Odleglosc.get(pomoc)*-1)-((double)q.Wartosc.get(pomoc) * (double)q.Dlugosc.get(pomoc))*((double)q.Dlugosc.get(pomoc)/2));
                }
                else{
                    taObliczenia.append("-" + q.Wartosc.get(pomoc) + " *(x- "+ q.Odleglosc.get(pomoc) + "- " + q.Dlugosc.get(pomoc) + "/2 ");
                    dObliczeniax = dObliczeniax + ((double)q.Wartosc.get(pomoc) * (double)q.Dlugosc.get(pomoc));
                    dObliczenia = dObliczenia + (((double)q.Wartosc.get(pomoc) * (double)q.Dlugosc.get(pomoc))*((double)q.Odleglosc.get(pomoc)*-1)-((double)q.Wartosc.get(pomoc) * (double)q.Dlugosc.get(pomoc))*((double)q.Dlugosc.get(pomoc)/2));
                }
                
            }
        }

        if(R.irodzajpodpory == 1){
            if(R.iRa<0){
               taObliczenia.append("+" + R.iRa*-1 + " *x ");
               dObliczeniax = dObliczeniax + R.iRa;
            }
            else{
                taObliczenia.append( R.iRa*-1 + " *x ");
                dObliczeniax = dObliczeniax + R.iRa;
            }
            if(R.iRb<0){
                taObliczenia.append(R.iRb + " ");
                dObliczenia = dObliczenia + (R.iRb *-1);
            }
            else{
                taObliczenia.append("+" + R.iRb + " ");
                dObliczenia = dObliczenia + (R.iRb *-1);            }
        }

        if(R.irodzajpodpory == 2 && R.dOdleglosc1 < GranicePrzedzialow[i+1]){
                if(R.iRa<0){
                    taObliczenia.append("+" + R.iRa*-1 + " *(x- " + R.dOdleglosc1 + ") ");
                    dObliczeniax = dObliczeniax + R.iRa;
                    dObliczenia = dObliczenia + (R.iRa * R.dOdleglosc1 *-1);
                }
                else{
                    taObliczenia.append(R.iRa*-1 + " *x ");
                    dObliczeniax = dObliczeniax + R.iRa;
                    dObliczenia = dObliczenia + (R.iRa * R.dOdleglosc1 *-1);
                }
        }
            
        if(R.irodzajpodpory == 2 && R.dOdleglosc2 < GranicePrzedzialow[i+1]){
                if(R.iRb<0){
                    System.out.println("+" + R.iRb*-1 + " *(x- " + R.dOdleglosc2 + ") ");
                    dObliczeniax = dObliczeniax + R.iRb;
                    dObliczenia = dObliczenia + (R.iRb * R.dOdleglosc2 *-1);
                }
                else{
                    System.out.println(R.iRb*-1 + " *(x- " + R.dOdleglosc2 + ") ");
                    dObliczeniax = dObliczeniax + R.iRb;
                    dObliczenia = dObliczenia + (R.iRb * R.dOdleglosc2 *-1);
                }
        }
        WstawianieWynikow(taObliczenia, i);
      }
        
    }
    
    public void WstawianieWynikow(JTextArea taObliczenia, int i){
        taObliczenia.append(". \n");
        Mg.add(dObliczenia);
        Mgx.add(dObliczeniax);
        Mgx2.add(dObliczeniax2);
        taObliczenia.append("Mg" + (i+1) + "(x)= ");
        if(Mgx2.get(i)!=0){
            taObliczenia.append(Mgx2.get(i) + "x^2 " );
        }
        if(Mgx.get(i)!=0){
            if(Mgx.get(i)>0){
                taObliczenia.append("+" +  Mgx.get(i) + "x ");
            }
            else{
                taObliczenia.append(Mgx.get(i) + "x ");
            }
        }
        if(Mg.get(i)!=0){
            if(Mg.get(i)>0){
                taObliczenia.append("+" + Mg.get(i));
            }
            else{
                taObliczenia.append(Mg.get(i) + " ");
            }
        }
        taObliczenia.append("\n");
    }
}

    
