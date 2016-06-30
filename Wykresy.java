
import static java.lang.Math.pow;
import java.math.BigDecimal;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Szynal L
 */
public class Wykresy extends JDialog {
    public int licznik=0,pomoc=0;
    public int wskaznik1 = 0;
    public double wskaznik2 = 0;
    public double dobliczenia=0;
    public double dWyliczenia=0, dPunktx=0, dWzrost = 0.01, dIloscProcentowa = 100;
    public double pom1=0, pom2=0, pom3=0, pom4=0, pom5=0, pom6=0;
    public double C=0, D=0;
    

    
    public Wykresy(JFrame owner, PrzedzialyObliczenia przeObl){
        
        
        
        
        //Dane do wykresu 3d
            XYSeries seria = new XYSeries("");
            dPunktx = 0;
            
            for(int i=0;i<przeObl.IloscPrzedzialow;i++){
                
                dWzrost = przeObl.GranicePrzedzialow[i+1]-przeObl.GranicePrzedzialow[i];
                dWzrost = dWzrost / dIloscProcentowa;
                while(dPunktx < przeObl.GranicePrzedzialow[i+1]){
                
                    dWyliczenia = przeObl.Mg.get(i) + (przeObl.Mgx.get(i) * dPunktx) + (przeObl.Mgx2.get(i)*dPunktx*dPunktx);
                    seria.add(dPunktx, dWyliczenia);
                    
                    dPunktx = dPunktx + dWzrost;
                }
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(seria);
        
        
        //Tworzymy wykres XY
        JFreeChart chart = ChartFactory.createXYAreaChart(
	"Wykres momentów gnących",//Tytuł
	"x [m]", // x-axis Opis
	"Mg(x)[kNm]", // y-axis Opis
	dataset, // Dane
	PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
	true, // pozkaż legende
	true, // podpowiedzi tooltips
	false
	);
        
        
        ChartFrame Okno = new ChartFrame("Wykres momentów gnących: Mg(x) ",chart);
        Okno.setVisible(true);
        Okno.setSize(600,500);
        Okno.setLocationRelativeTo(null);
        
    }

    public void rysowanie(XYSeries seria,Sila p,Moment M,ObciazenieCiagle q,Podpory r,PrzedzialyObliczenia PrzedzialyObl, MetodaClebsha OblClebsha, ModulYanga E) {
        for(int i=0;i<PrzedzialyObl.IloscPrzedzialow;i++){
            Wykresy wWx = this;
            wWx.dWzrost = PrzedzialyObl.GranicePrzedzialow[i+1]-PrzedzialyObl.GranicePrzedzialow[i];
            wWx.dWzrost = wWx.dWzrost / wWx.dIloscProcentowa;
            while(wWx.dPunktx <= PrzedzialyObl.GranicePrzedzialow[i+1]){

                wWx.Zapis(p, M, q, r, PrzedzialyObl, OblClebsha, E, i);
                seria.add(wWx.dPunktx, wWx.dWyliczenia);
                //pw.println(wWx.dPunktx + "   " + wWx.dWyliczenia + "\n");
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
    }
    
    public Wykresy(JFrame owner,Sila P,Moment M,ObciazenieCiagle q,Podpory R,PrzedzialyObliczenia przeObl, MetodaClebsha MC, ModulYanga E){
        XYSeries seria = new XYSeries("");
            dPunktx = 0;
        
                C = MC.C;
                D = MC.D;
                C = C * 1000;
                C = new BigDecimal(C).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                D = D * 1000;
                D = new BigDecimal(D).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        
            rysowanie(seria, P, M, q, R, przeObl, MC, E);
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(seria);
        
        
        //Tworzymy wykres XY
        JFreeChart chart = ChartFactory.createXYLineChart(
	"Wykres lini ugięcia",//Tytuł
	"x[m]", // x-axis Opis
	"w(x)[m]", // y-axis Opis
	dataset, // Dane
	PlotOrientation.VERTICAL, // Orjentacja wykresu 
	true, // pozkaż legende
	true, // podpowiedzi tooltips
	false
	);
        
        
        ChartFrame Okno = new ChartFrame("Wykres lini ugięcia: w(x) ",chart);
        Okno.setVisible(true);
        Okno.setSize(600,500);
        Okno.setLocationRelativeTo(null);
        
        

    }
    
    public void Zapis(Sila P,Moment M,ObciazenieCiagle q,Podpory R,PrzedzialyObliczenia przeObl, MetodaClebsha MC, ModulYanga E, int i){
            
        
            pom1 =0;
            pom2 =0;
            pom3 =0;
            pom4 =0;
            pom5 =0;
            pom6 =0;
            
            if(pomoc > 0){
                
                pom6 = ((q.Wartosc.get(wskaznik1)/24)*pow((dPunktx - wskaznik2), 4));
                pom6 = pom6 * 1000;
              //  pom6 = new BigDecimal(pom6).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                dobliczenia = dobliczenia + pom6;
                System.out.println(pom6 + "   dP= " + dPunktx + "   Wskaznik: " + wskaznik2 + "    q.Wart" + q.Wartosc.get(wskaznik1));
                licznik = 0;

            }
            for(int y=0;y<P.licznikp;y++){
                if(przeObl.GranicePrzedzialow[i+1] > P.Odleglosc.get(y)){
                    pom1 = ((P.Wielkosc.get(y)*-1 /6) * pow((dPunktx - P.Odleglosc.get(y)), 3));
                    pom1 = pom1 * 1000;
                  //  pom1 = new BigDecimal(pom1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    dobliczenia = dobliczenia + pom1;
                }
            }
            for(int y=0;y<M.licznikm;y++){
                if(przeObl.GranicePrzedzialow[i+1] > M.Odleglosc.get(y)){                    
                    pom2 = ((M.Wartosc.get(y)/2)*pow((dPunktx - M.Odleglosc.get(y)), 2));
                    pom2 = pom2 * 1000;
                  //  pom2 = new BigDecimal(pom2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    dobliczenia = dobliczenia + pom2;
                }
            }
            for(int y=0;y<q.licznikq;y++){
                if(przeObl.GranicePrzedzialow[i+1] > q.Odleglosc.get(y)){
                    pom3 = ((q.Wartosc.get(y)*-1/24)*pow((dPunktx - q.Odleglosc.get(y)), 4));
                    pom3 = pom3 * 1000;
                   // pom3 = new BigDecimal(pom3).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    dobliczenia = dobliczenia + pom3;
                    
                    if(wskaznik2 == 0){
                    licznik = 1;
                    wskaznik1 = y;
                    wskaznik2 = przeObl.GranicePrzedzialow[i+1];
                    }
                }
            }
            if( przeObl.GranicePrzedzialow[i+1] > R.dOdleglosc1){
                    pom4 = ((R.iRa *-1/6)*pow((dPunktx - R.dOdleglosc1), 3));
                    pom4 = pom4 * 1000;
                   // pom4 = new BigDecimal(pom4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    dobliczenia = dobliczenia + pom4;
            }
            if(przeObl.GranicePrzedzialow[i+1] > R.dOdleglosc2){
                if(R.irodzajpodpory==2){
                        pom5 = ((R.iRb*-1 /6)*pow((dPunktx - R.dOdleglosc2), 3));
                        pom5 = pom5 * 1000;
                       // pom5 = new BigDecimal(pom5).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        dobliczenia = dobliczenia + pom5;
                }
                else if(R.irodzajpodpory==1){
                        pom5 = ((R.iRb /2)*pow((dPunktx - R.dOdleglosc2), 2));
                        pom5 = pom5 * 1000;
                       // pom5 = new BigDecimal(pom5).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        dobliczenia = dobliczenia + pom5;
                }
            }
            
            dobliczenia = dobliczenia + (C * dPunktx) + D;
            dWyliczenia = dobliczenia;
            dWyliczenia = dWyliczenia /(E.E*E.J);
    }

}
