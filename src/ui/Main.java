package src.ui;

import com.google.gson.Gson;
import src.model.Controller;
import src.model.Hoarding;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private Scanner sc;
    private Controller con;
    private Gson gson;

    public Main(){
        sc = new Scanner(System.in);
        con = new Controller();
        gson = new Gson();
    }
    //JSON
    //https://mvnrepository.com/
    //https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1
    public static void main(String[] args) {
        Main main = new Main();
        main.importData();
        main.init();

    }

    public void importData(){
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir.getName()+"/data");
        File result = new File(projectDir.getName()+"/data/result.json");

        try {
            FileInputStream fis = new FileInputStream(result);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            String json = "";
            while ( (line = reader.readLine()) != null){
                json += line;
            }
            con = gson.fromJson(json, Controller.class);


        }catch (FileNotFoundException e){;
        }catch (IOException e){
        }
    }

    public void init(){
        System.out.println("Bienvenido! Inserte la opción deseada \n"+
                "1. Importar datos CSV\n"+
                "2. Agregar valla publicitaria\n"+
                "3. Mostrar vallas publicitarias \n"+
                "4. Exportar reporte de peligrosidad \n"+
                "0. Salir");
        int option = sc.nextInt();
        sc.nextLine();
        execute(option);
    }

    public void execute(int option){
        switch (option){
            case 1:
                importCSV();
                break;

            case 2:
                addHoarding();
                break;

            case 3:
                System.out.println(con.showHoarding());
                init();
                break;

            case 4:
                System.out.println(con.showHazardousHoard());
                init();
                break;

            case 0:
                updateSave();
                System.out.println("Salida del programa");
                break;

            default:
                System.out.println("Opción invalida");
                init();
        }
    }

    public void importCSV(){
        System.out.println("Inserte la dirección absoluta donde se encuentra el archivo CSV");
        String path = sc.nextLine();
        File dataDirectory = new File(path);
            try{
            FileInputStream fis = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            String json = "";
            while ((line = reader.readLine()) !=null){
                json+=line;
            }
            System.out.println(json);
            Hoarding[] hoardings = gson.fromJson((json), Hoarding[].class);
            con.addHoardings(hoardings);
            System.out.println("Archivo importado");
            updateSave();
            init();
        }catch (FileNotFoundException e){
                System.out.println("No se encontró el archivo");
                init();
        }catch (IOException e){
                System.out.println("El archivo esta siendo usado por otro programa");
                init();
        }
    }

    public void addHoarding(){
        System.out.println("Inserte los datos de la valla en el siguiente formato width++height++inUse++brand");
        String newHoarding = sc.nextLine();
        String[] data = newHoarding.split("\\++");
        con.addHoarding(data[0], data[1], data[2],data[3]);
        System.out.println("Valla agregada");
        updateSave();
        init();
    }
    public void updateSave(){
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir.getName()+"/data");
        File result = new File(projectDir.getName()+"/data/result.json");

        if(!dataDirectory.exists()){
            dataDirectory.mkdirs();
        }

        String json =  gson.toJson(con);
        try{
            FileOutputStream fos = new FileOutputStream(result);
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
