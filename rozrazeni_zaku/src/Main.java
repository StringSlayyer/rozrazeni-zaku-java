import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Time time = new Time();
        Scanner sc = new Scanner(System.in, "UTF-8");
        boolean validFile = false;
        File file;
        do{
            System.out.println("Zadejte nazev souboru");
            String filename = sc.nextLine();
            file = new File(filename);
            if(file.exists()){
                System.out.println("Soubor nacten");
                validFile = true;
            }else {
                System.out.println("Spatny nazev souboru");
            }
        }while(!validFile);




        if(getNames(file).size() > time.Duration()){
            System.out.println("pocet zaku je vetsi nez pocet minut");
        }else{

            int size = getNames(file).size();
            List<String> jmena = RozradJmena(getNames(file));
            String[][] names = time.NameTime(jmena,size, time.SingleDuration(size));
            
            writeFile(names);
            System.out.println("Seznam vytvoren.");
        }


    }

    public static List RozradJmena(List list){
        List<Integer> usedNum = new ArrayList<>();
        List<String> randomNames = new ArrayList<>();
        Random random = new Random();
        while (randomNames.size() != list.size()){
            int num = random.nextInt(0, list.size());
            if(!usedNum.contains(num)){
                String name = list.get(num).toString();
                randomNames.add(name);
                usedNum.add(num);
            }
        }
        return randomNames;
    }



    public static List getNames(File file){
        List<String> names = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(file))){
            String input;

            while((input = in.readLine()) != null){
                names.add(input);
            }
        }catch (IOException e){
            System.out.println("chyba pri cteni");
        }
        return names;
    }

    public static void writeFile(String[][] seznam){
        File vypis = new File("seznam.txt");
        if(!vypis.exists()){
            try {
                vypis.createNewFile();
            }catch (Exception e){
                System.out.println("Chyba nacitani souboru pro vypis");
            }
        }
        try(BufferedWriter out = new BufferedWriter(new FileWriter(vypis))) {
            for(int i = 0; i < seznam.length; i++){
                out.write(seznam[i][0] + " " + seznam[i][1] + " do " + seznam[i][2]);
                out.newLine();
            }
            out.flush();
        }catch (Exception e){
            System.out.println("Chyba pri vypisu do souboru");
        }
    }

    

}

