import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;


public class Time {

    int hours;
    int mins;
    int totalM;
    Scanner sc = new Scanner(System.in,"UTF-8");
     int getMinutes(String text, boolean start){
         int minutes = 0;
         boolean validFormat = false;

         while(!validFormat){
             try{
                 System.out.println(text);
                 sc.useDelimiter(":|\\s+");
                 int hours = sc.nextInt();
                 int mins = sc.nextInt();
                 if(start){
                     this.hours = hours;
                     this.mins = mins;
                 }
                 if (mins < 0 || mins > 59 || hours < 0 || hours > 23) {
                     System.out.println("Zadali jste spatny pocet minut nebo hodin. Zadejte znovu.");
                 }else {
                    minutes = hours * 60 + mins;
                    validFormat = true;
                 }
             }catch (InputMismatchException e){
                 System.out.println("Cas je ve spatnem formatu. Zadejte znovu.");
                 sc.nextLine();
             }
         }
         return minutes;
     }



    int Duration(){
         boolean validCount = false;
         while (!validCount){
             int start = getMinutes("Zadejte zacatek hodiny ve formatu hh:mm", true);
             int end = getMinutes("Zadejte konec hodiny ve formatu hh:mm", false);
             this.totalM = end - start;
             if(this.totalM<0){
                 System.out.println("Zadali jste spatne casove rozmezi");
             }else{
                 validCount = true;
             }
         }
        return totalM;
    }

    int SingleDuration(int num){
         int duration = totalM / num;
         //Math.floor(duration);
         return duration;
    }

    String[][] NameTime(List<String> list, int size, int duration){
         int i = 0;
         int mins = this.mins;
         int hours = this.hours;
         String[][] seznam = new String[size][3];
         seznam[i][0] = list.get(i);
         seznam[i][1] = String.format("%02d:%02d", hours, mins);
         i++;
         while(size > i){
            mins += duration;
            if(mins >= 60){
                hours++;
                mins %=60;
            }
            seznam[i-1][2] = String.format("%02d:%02d", hours, mins);
            seznam[i][0] = list.get(i);
            seznam[i][1] = String.format("%02d:%02d", hours, mins);
            i++;
         }
        mins += duration;
        if(mins >= 60){
            hours++;
            mins %=60;
        }
        seznam[i-1][2] = String.format("%02d:%02d", hours, mins);
        return seznam;

    }


}
