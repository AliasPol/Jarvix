
import static java.lang.Math.pow;
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


public class MetodaClebsha {
    
    private int licznik = 0, wskaznik1 = 0, iloscprzedzialow =1;
    private double dobliczenia = 0, dobliczenia2 = 0, granica= 0;
    public double C, D;
    public double pom1, pom2,pom3,pom4,pom5,pom6;
    public ArrayList<Double> DoWykresu;
    
    public MetodaClebsha(JTextArea taObliczenia, Sila P, Moment M, ObciazenieCiagle q, Podpory R, PrzedzialyObliczenia PrzedzialyObl){
        
        
        taObliczenia.append("\n\nMetoda Clebscha: " + "\n" + "EJw''(x)= -Mg(x)" + "\n");
        taObliczenia.append("-Mg(x) = ");
        
        for(int i=0;i<PrzedzialyObl.IloscPrzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                }
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                        
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                        
                    }
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^0 ");
                       
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^0 ");
                        
                    }
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                    }
                    licznik = 1;
                    wskaznik1 = y;
                    
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                if(R.irodzajpodpory == 2){    
                    if(R.iRb >= 0){
                        taObliczenia.append(R.iRb*-1 + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    
                    }
                    else{
                        taObliczenia.append("+" + R.iRb*-1 + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    
                    }
                }
                else if(R.irodzajpodpory == 1){
                    if(R.iRb >= 0){
                        taObliczenia.append("+" + R.iRb + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^0 ");
                    
                    }
                    else{
                        taObliczenia.append(R.iRb + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^0 ");
                    
                    }
                }
            }
            taObliczenia.append(" || " + "\n");
        }
        
        
        licznik = 0;
        wskaznik1 = 0;
        
        taObliczenia.append("\n Pierwsze całkowanie: " + "\n");
        taObliczenia.append("EJw'(x) = C ");
        
        for(int i=0;i<PrzedzialyObl.IloscPrzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^6 ");
                        
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^6 ");
                        
                }
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                    }
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                       
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                        
                    }
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        
                    }
                    licznik = 1;
                    wskaznik1 = y;
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                if(R.irodzajpodpory == 2){    
                    if(R.iRb >= 0){
                        taObliczenia.append(R.iRb*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    
                    }
                    else{
                        taObliczenia.append("+" + R.iRb*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    
                    }
                }
                else if(R.irodzajpodpory == 1){
                    if(R.iRb >= 0){
                        taObliczenia.append("+" + R.iRb + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    
                    }
                    else{
                        taObliczenia.append(R.iRb + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    
                    }
                }
            }
            taObliczenia.append(" || " + "\n");
        }
        
        
        licznik = 0;
        wskaznik1 = 0;
        
        taObliczenia.append("\n Drugie całkowanie: " + "\n");
        taObliczenia.append("EJw(x) = D + Cx ");
        
        for(int i=0;i<PrzedzialyObl.IloscPrzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/24*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/24*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        
                }
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        
                    }
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                       
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        
                    }
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/24*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/24*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        
                    }
                    licznik = 1;
                    wskaznik1 = y;
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                if(R.irodzajpodpory == 2){    
                    if(R.iRb >= 0){
                        taObliczenia.append(R.iRb*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    
                    }
                    else{
                        taObliczenia.append("+" + R.iRb*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    
                    }
                }
                else if(R.irodzajpodpory == 1){
                    if(R.iRb >= 0){
                        taObliczenia.append("+" + R.iRb + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    
                    }
                    else{
                        taObliczenia.append(R.iRb + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    
                    }
                }
            }
            taObliczenia.append(" || " + "\n");
        }
        licznik = 0;
        wskaznik1 = 0;
        
        wx(taObliczenia, P, M, q, R, PrzedzialyObl);
        Rownanie(taObliczenia, R);
    }
    
    public void wx(JTextArea taObliczenia, Sila P, Moment M, ObciazenieCiagle q, Podpory R, PrzedzialyObliczenia PrzedzialyObl){
          
      if(R.irodzajpodpory == 1){
            licznik = 0;
            wskaznik1 = 0;
            
            granica = R.dOdleglosc1;
            for(int i=0;i<PrzedzialyObl.IloscPrzedzialow; i++){
                if(granica == PrzedzialyObl.GranicePrzedzialow[i]){
                    break;
                }
                else{
                    iloscprzedzialow ++;
                }
            }
            
            taObliczenia.append("\n Obliczanie stałych całkowania 'C' i 'D' z warunków brzegowych: \n EJw(" + R.dOdleglosc1 + ") = D + C*" + R.dOdleglosc1 + " ");
            
          for(int i=0;i<iloscprzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/24*("+ R.dOdleglosc1 + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                    dobliczenia = dobliczenia + ((q.Wartosc.get(wskaznik1)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/24*("+ R.dOdleglosc1 + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                    dobliczenia = dobliczenia + ((q.Wartosc.get(wskaznik1)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                }
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia = dobliczenia + ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia = dobliczenia + ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "/2*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia = dobliczenia + ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "/2*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia = dobliczenia + ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/24*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        dobliczenia = dobliczenia + ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/24*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        dobliczenia = dobliczenia + ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    }
                    licznik = 1;
                    wskaznik1 = y;
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia = dobliczenia + ((R.iRa *-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia = dobliczenia + ((R.iRa *-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                    if(R.iRb >= 0){
                        taObliczenia.append("+" + R.iRb + "/2*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia = dobliczenia + ((R.iRb /2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                    else{
                        taObliczenia.append(R.iRb + "/2*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia = dobliczenia + ((R.iRb /2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                
            }
            taObliczenia.append(" || " + "\n");
        }
        licznik = 0;
        wskaznik1 = 0;  
            
        taObliczenia.append("EJw'(" + R.dOdleglosc1 +") = C ");
        
        for(int i=0;i<iloscprzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/6*("+ R.dOdleglosc1 + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia = dobliczenia + ((q.Wartosc.get(wskaznik1)*-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/6*("+ R.dOdleglosc1 + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia = dobliczenia + ((q.Wartosc.get(wskaznik1)*-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia2 = dobliczenia2 + ((P.Wielkosc.get(y)*-1 /2) * pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));                        
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia2 = dobliczenia2 + ((P.Wielkosc.get(y)*-1 /2) * pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));                        
                    }
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                        dobliczenia2 = dobliczenia2 + (M.Wartosc.get(y)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 1));                        
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                        dobliczenia2 = dobliczenia2 + (M.Wartosc.get(y)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 1));                        
                    }
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia2 = dobliczenia2 + ((q.Wartosc.get(y)*-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/6*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia2 = dobliczenia2 + ((q.Wartosc.get(y)*-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    licznik = 1;
                    wskaznik1 = y;
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    dobliczenia2 = dobliczenia2 + ((R.iRa *-1/2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "/2*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                    dobliczenia2 = dobliczenia2 + ((R.iRa *-1/2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){                                             
                if(R.iRb >= 0){
                    taObliczenia.append("+" + R.iRb + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    dobliczenia2 = dobliczenia2 + (R.iRb *pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 1));
                }
                else{
                    taObliczenia.append(R.iRb + "*(x-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^1 ");
                    dobliczenia2 = dobliczenia2 + (R.iRb *pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 1));
                    }
            }
            taObliczenia.append(" || " + "\n");
        }
      }
        else{
            licznik = 0;
            wskaznik1 = 0;
            granica = R.dOdleglosc1;
            iloscprzedzialow = 1;
            for(int i=0;i<PrzedzialyObl.IloscPrzedzialow; i++){
                if(granica == PrzedzialyObl.GranicePrzedzialow[i]){
                    break;
                }
                else{
                    iloscprzedzialow ++;
                }
            }
            
            taObliczenia.append("\nObliczanie stałych całkowania 'C' i 'D' z warunków brzegowych: \n EJw(" + R.dOdleglosc1 + ") = D + C*" + R.dOdleglosc1 + " ");
            
          for(int i=0;i<iloscprzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/24*("+ R.dOdleglosc1 + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                    dobliczenia = dobliczenia + ((q.Wartosc.get(wskaznik1)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/24*("+ R.dOdleglosc1 + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                    dobliczenia = dobliczenia + ((q.Wartosc.get(wskaznik1)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                }
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia = dobliczenia + ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia = dobliczenia + ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "/2*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia = dobliczenia + ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "/2*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia = dobliczenia + ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/24*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        dobliczenia = dobliczenia + ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/24*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        dobliczenia = dobliczenia + ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    }
                    licznik = 1;
                    wskaznik1 = y;
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia = dobliczenia + ((R.iRa *-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia = dobliczenia + ((R.iRa *-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                    if(R.iRb >= 0){
                        taObliczenia.append( R.iRb*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia = dobliczenia + ((R.iRb *-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    else{
                        taObliczenia.append("+" + R.iRb*-1 + "/6*("+ R.dOdleglosc1 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia = dobliczenia + ((R.iRb *-1/6)*pow((R.dOdleglosc1 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                
            }
            taObliczenia.append(" || " + "\n");
        }

            licznik = 0;
            wskaznik1 = 0;
            granica = R.dOdleglosc2;
            iloscprzedzialow = 1;
            for(int i=0;i<PrzedzialyObl.IloscPrzedzialow; i++){
                if(granica == PrzedzialyObl.GranicePrzedzialow[i]){
                    break;
                }
                else{
                    iloscprzedzialow ++;
                }
            }
            if(PrzedzialyObl.IloscPrzedzialow < iloscprzedzialow){
                iloscprzedzialow --;
            }
            
            taObliczenia.append("\n EJw(" + R.dOdleglosc2 + ") = D + C*" + R.dOdleglosc2 + " ");
            
          for(int i=0;i<iloscprzedzialow;i++){
            if(licznik > 0){
                if(q.Wartosc.get(wskaznik1)>=0){
                    taObliczenia.append("+" + q.Wartosc.get(wskaznik1) + "/24*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                    dobliczenia2 = dobliczenia2 + ((q.Wartosc.get(wskaznik1)/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    pom5 = ((q.Wartosc.get(wskaznik1)/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                }
                else{
                    taObliczenia.append(q.Wartosc.get(wskaznik1) + "/24*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                    dobliczenia2 = dobliczenia2 + ((q.Wartosc.get(wskaznik1)/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    pom5 = ((q.Wartosc.get(wskaznik1)/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                }
                
                licznik = 0;
                wskaznik1 = 0;
            }
            for(int y=0;y<P.licznikp;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= P.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    if(P.Wielkosc.get(y)>=0){
                        taObliczenia.append(P.Wielkosc.get(y)*-1 + "/6*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia2 = dobliczenia2 + ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    else{
                        taObliczenia.append("+" + P.Wielkosc.get(y)*-1 + "/6*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia2 = dobliczenia2 + ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    pom1 = ((P.Wielkosc.get(y)*-1 /6) * pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= M.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){
                    if(M.Wartosc.get(y)>0){
                        taObliczenia.append("+" + M.Wartosc.get(y) + "/2*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia2 = dobliczenia2 + ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                    else{
                        taObliczenia.append(M.Wartosc.get(y) + "/2*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^2 ");
                        dobliczenia2 = dobliczenia2 + ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                    }
                    pom2 = ((M.Wartosc.get(y)/2)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 2));
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(PrzedzialyObl.GranicePrzedzialow[i] <= q.Odleglosc.get(y) && PrzedzialyObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    if(q.Wartosc.get(y)>=0){
                        taObliczenia.append(q.Wartosc.get(y)*-1 + "/24*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        dobliczenia2 = dobliczenia2 + ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    }
                    else{
                        taObliczenia.append("+" + q.Wartosc.get(y)*-1 + "/24*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^4 ");
                        dobliczenia2 = dobliczenia2 + ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    }
                    pom3 = ((q.Wartosc.get(y)*-1/24)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 4));
                    licznik = 1;
                    wskaznik1 = y;
                }
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc1 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                if(R.iRa >= 0){
                    taObliczenia.append(R.iRa*-1 + "/6*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia2 = dobliczenia2 + ((R.iRa *-1/6)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
                else{
                    taObliczenia.append("+" + R.iRa*-1 + "/6*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                    dobliczenia2 = dobliczenia2 + ((R.iRa *-1/6)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                }
                pom4 = ((R.iRa *-1/6)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
            }
            if(PrzedzialyObl.GranicePrzedzialow[i] <= R.dOdleglosc2 && PrzedzialyObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                    if(R.iRb >= 0){
                        taObliczenia.append(R.iRb *-1+ "/6*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia2 = dobliczenia2 + ((R.iRb *-1/6)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    else{
                        taObliczenia.append("+" + R.iRb *-1+ "/6*("+ R.dOdleglosc2 + "-" + PrzedzialyObl.GranicePrzedzialow[i] + ")^3 ");
                        dobliczenia2 = dobliczenia2 + ((R.iRb *-1/6)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                    }
                    pom6 = ((R.iRb*-1 /6)*pow((R.dOdleglosc2 - PrzedzialyObl.GranicePrzedzialow[i]), 3));
                
            }
            taObliczenia.append(" || " + "\n");
        }
        }
    }

    public void Rownanie(JTextArea taObliczenia, Podpory R){
        if(R.irodzajpodpory == 1){
            taObliczenia.append("\n 0 = D + C*" + R.dOdleglosc1 );
            if(dobliczenia >=0){
                taObliczenia.append("+" + dobliczenia);
            }
            else{
                taObliczenia.append("" + dobliczenia);
            }
            
            taObliczenia.append("\n 0 = C");
            if(dobliczenia >=0){
                taObliczenia.append("+" + dobliczenia2);
            }
            else{
                taObliczenia.append("" + dobliczenia2);
            }
            C = dobliczenia2*-1;
            D = C*-1 * R.dOdleglosc1 + dobliczenia*-1;
            taObliczenia.append("\n C = " + C);
            taObliczenia.append("\n D = " + D);
        }
        else{
            taObliczenia.append("\n 0 = D + C*" + R.dOdleglosc1 );
            if(dobliczenia >=0){
                taObliczenia.append("+" + dobliczenia);
            }
            else{
                taObliczenia.append("" + dobliczenia);
            }
            
            taObliczenia.append("\n 0 =D + C*" + R.dOdleglosc2);
            if(dobliczenia >=0){
                taObliczenia.append("+" + dobliczenia2);
            }
            else{
                taObliczenia.append("" + dobliczenia2);
            }
            
            C = (dobliczenia*-1 + dobliczenia2)/(-R.dOdleglosc2 + R.dOdleglosc1);
            D = C * R.dOdleglosc1*-1 - dobliczenia;
            taObliczenia.append("\n C = " + C);
            taObliczenia.append("\n D = " + D);
        }
        
    }
    
}
