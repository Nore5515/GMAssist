import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Nore5515 on 3/19/2017.
 */

//For room generation
    //I want to have crates, chests, barrels, holes, empty space
    //i want the crates and barrels to be clumped with each other
    //i want negative space to have some holes in it
    //chests to be by themselves and very few
    //For crates
        //More material things, like cloth, coal, non-edibles
    //For barrels
        //more food + perishables, like salt and wine
    //For chests
        //chests should hold highly-prized valuables, like weapons, jewelry and books
    //For holes
        //holes are holes.
    //I also want to be able to set the size of the room
    //room key
    //  . is empty
    //  W is wall
    //  C is crate
    //  B is barrel
    //  H is hole


public class Main {
    public static void main(String args[]){
        System.out.println("Hello World!");

        //Crate c = new Crate(5);
        //System.out.println(c.ToString());
        
        Room r = new Room(7,7);
        System.out.println("\n" + r.ToString());
        r.AddCrates();
        r.AddBarrels();
        r.AddHoles();
        System.out.println("\n" + r.ToString());

        Weather w = new Weather();
        System.out.println(w.ToString());

        /*String[] sList = r.AdjacentTiles(1,1);
        System.out.println("\n\n");
        for (int x = 0; x < 9; x++) {
            System.out.print(sList[x] + "\t");
        }*/


    }
}

//Room uses W for walls
//. for empty space

class Room{
    
    String[][] map;
    int width,height;
    
    public Room(int w, int h){
        map = new String[w][h];
        width = w;
        height = h;
        boolean doorPlaced = false;
        int chance = -1;
        Random rand = new Random();
        for (int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                if (x == 0 || x == w-1){
                    chance = rand.nextInt(100)+1;
                    if (chance <= 25 && !doorPlaced){
                        map[x][y] = "D";
                        chance = rand.nextInt(100)+1;
                        if (chance <= 50){
                            doorPlaced = true;
                        }
                    }
                    else{
                        map[x][y] = "W";
                    }

                }
                else if (y == 0 || y == h-1){
                    if (chance <= 15 && !doorPlaced){
                        map[x][y] = "D";
                        chance = rand.nextInt(100)+1;
                        if (chance <= 50){
                            doorPlaced = true;
                        }
                    }
                    else{
                        map[x][y] = "W";
                    }

                }
                else{
                    map[x][y] = ".";
                }
            }
        }
    }
    
    public String ToString(){
        String s = new String();
        for (int y = 0; y < map[0].length; y++){
            for (int x = 0; x < map.length; x++){
                s += map[x][y];
            }
            s += "\n";
        }
        return s;
    }
    
    public void AddCrates(){
        Random rand = new Random();
        int chance = -1;
        String[] adj;
        for (int y = 0; y < map[0].length; y++){
            for (int x = 0; x < map.length; x++){
                if (map[x][y] == "."){
                    adj = AdjacentTiles(x,y);
                    for (int z = 0; z < 9; z++){
                        if (adj[z].equals("W")){
                            chance = rand.nextInt(100) + 1;
                            if (chance <= 2){
                                map[x][y] = "C";
                            }
                        }
                        else if (adj[z].equals("C")){
                            chance = rand.nextInt(100) + 1;
                            if (chance <= 5){
                                map[x][y] = "C";
                            }
                        }
                    }

                }
            }
        }
    }

    public void AddBarrels(){
        Random rand = new Random();
        int chance = -1;
        String[] adj;
        for (int y = 0; y < map[0].length; y++){
            for (int x = 0; x < map.length; x++){
                if (map[x][y] == "."){
                    adj = AdjacentTiles(x,y);
                    for (int z = 0; z < 9; z++){
                        if (adj[z].equals("W")){
                            chance = rand.nextInt(100) + 1;
                            if (chance <= 5){
                                map[x][y] = "B";
                            }
                        }
                        else if (adj[z].equals("B")){
                            chance = rand.nextInt(100) + 1;
                            if (chance <= 5){
                                map[x][y] = "B";
                            }
                        }
                    }

                }
            }
        }
    }


    public void AddHoles(){
        Random rand = new Random();
        int chance = -1;
        int holeCount = -1;
        String[] adj;
        for (int y = 0; y < map[0].length; y++){
            for (int x = 0; x < map.length; x++){
                if (map[x][y] == "."){
                    adj = AdjacentTiles(x,y);
                    holeCount = 0;
                    for (int z = 0; z < 9; z++){
                        if (adj[z].equals(".") || adj[z].equals("H")){
                            holeCount++;
                        }
                    }
                    if (holeCount >= 3){
                        chance = rand.nextInt(100)+1;
                        if (chance <= 5){
                            map[x][y] = "H";
                        }
                    }

                }
            }
        }
    }

    //MY X AND Y ARE SWITCHED AROUND B CAREFUL
    public String[] AdjacentTiles(int xLoc, int yLoc){
        String[] adj = new String[9];
        int adjCount = 0;
        for (int x = -1; x < 2; x++){
            for (int y = -1; y < 2; y++) {
                if (x == 0 && y == 0){
                    adj[adjCount] = "*";
                    adjCount++;
                }
                else{
                    if (map[x + xLoc][y + yLoc] == null || (x+xLoc) < 0 || (x+xLoc) > width || (y+yLoc) < 0 || (y+yLoc) > height){
                        adj[adjCount] = "null";
                    }
                    else {
                        adj[adjCount] = map[x + xLoc][y + yLoc];
                    }
                    adjCount++;
                }
            }
        }
        return adj;

    }
    
    
}

//ORDER OF CHECKING MATTERS
//PRECIP
//HUMID
//TEMP
//WIND
//etc

class Weather{

    String temp;
    String light;
    String humidity;
    String precip;
    String wind;
    String pressure;
    int tempLvl,lightLvl,humidityLvl,precipLvl,windLvl,pressureLvl;

    public Weather(){

        Random rand = new Random();
        int tempchance = rand.nextInt(100)+1;
        int lightchance = rand.nextInt(100)+1;
        int humiditychance = rand.nextInt(100)+1;
        int precipchance = rand.nextInt(100)+1;
        int windchance = rand.nextInt(100)+1;
        int pressurechance = rand.nextInt(100)+1;

        if (precipchance <= 30){
            precipLvl = 0;
        }
        else if (30 < precipchance && precipchance <= 50){
            precipLvl = 1;
        }
        else if (50 < precipchance && precipchance <= 70){
            precipLvl = 2;
        }
        else if (70 < precipchance && precipchance <= 90){
            precipLvl = 3;
        }
        else if (90 < precipchance && precipchance <= 100){
            precipLvl = 4;
        }
        else{
            precipLvl = 99999;
        }

        if (humiditychance <= 5){
            humidityLvl = 0;
        }
        else if (5 < humiditychance && humiditychance <= 25){
            humidityLvl = 1;
        }
        else if (25 < humiditychance && humiditychance <= 75){
            humidityLvl = 2;
        }
        else if (75 < humiditychance && humiditychance <= 95){
            humidityLvl = 3;
        }
        else if (95 < humiditychance && humiditychance <= 100){
            humidityLvl = 4;
        }
        else{
            humidityLvl = 99999;
        }

        if (tempchance <= 5){
            tempLvl = 0;
        }
        else if (5 < tempchance && tempchance <= 25){
            tempLvl = 1;
        }
        else if (25 < tempchance && tempchance <= 75){
            tempLvl = 2;
        }
        else if (75 < tempchance && tempchance <= 95){
            tempLvl = 3;
        }
        else if (95 < tempchance && tempchance <= 100){
            tempLvl = 4;
        }
        else{
            tempLvl = 99999;
        }

        if (windchance <= 5){
            windLvl = 0;
        }
        else if (5 < windchance && windchance <= 45){
            windLvl = 1;
        }
        else if (45 < windchance && windchance <= 70){
            windLvl = 2;
        }
        else if (70 < windchance && windchance <= 95){
            windLvl = 3;
        }
        else if (95 < windchance && windchance <= 100){
            windLvl = 4;
        }
        else{
            windLvl = 99999;
        }

        if (pressurechance <= 5){
            pressureLvl = 0;
        }
        else if (5 < pressurechance && pressurechance <= 25){
            pressureLvl = 1;
        }
        else if (25 < pressurechance && pressurechance <= 75){
            pressureLvl = 2;
        }
        else if (75 < pressurechance && pressurechance <= 95){
            pressureLvl = 3;
        }
        else if (95 < pressurechance && pressurechance <= 100){
            pressureLvl = 4;
        }
        else{
            pressureLvl = 99999;
        }

        if (lightchance <= 5){
            lightLvl = 0;
        }
        else if (5 < lightchance && lightchance <= 25){
            lightLvl = 1;
        }
        else if (25 < lightchance && lightchance <= 75){
            lightLvl = 2;
        }
        else if (75 < lightchance && lightchance <= 95){
            lightLvl = 3;
        }
        else if (95 < lightchance && lightchance <= 100){
            lightLvl = 4;
        }
        else{
            lightLvl = 99999;
        }


        //LIGHT COVERAGE
        if (lightLvl < 0){
            light = "Solar Eclipse (2x Perception--)";
        }
        else if (lightLvl == 0){
            light = "Dark (Perception--)";
        }
        else if (lightLvl == 1){
            light = "Dim (Perception--)";
        }
        else if (lightLvl == 2){
            light = "Average (No effect)";
        }
        else if (lightLvl == 3){
            light = "Bright (Perception++)";
        }
        else if (lightLvl == 4){
            light = "Blinding (Perception--)";
        }
        else{
            light = "Solar Flare (2x Perception--)";
        }


        //PRECIPTATION LEVEL
        if (precipLvl < 0){
            precip = "Drought (Less Humid)";
            humidityLvl--;
        }
        else if (precipLvl == 0){
            precip = "Clear (Perception++, Less Humid)";
            humidityLvl--;
        }
        else if (precipLvl == 1){
            precip = "Foggy (Stealth++)";
        }
        else if (precipLvl == 2){
            precip = "Drizzle (No effect)";
        }
        else if (precipLvl == 3){
            precip = "Rain (DEX--, END++)";
        }
        else if (precipLvl == 4){
            precip = "Storm (DX--, More Humid)";
            humidityLvl++;
        }
        else{
            precip = "Hurricane (More Wind, Flooding, More Humid)";
            humidityLvl++;
        }


        //HUMIDITY LEVEL
        if (humidityLvl < 0){
            humidity = "Wasteland (No leaving area)";
        }
        else if (humidityLvl == 0){
            humidity = "Dust (STR--, need Water";
        }
        else if (humidityLvl == 1){
            humidity = "Dry (STR-- without Water";
        }
        else if (humidityLvl == 2){
            humidity = "Average (No effect)";
        }
        else if (humidityLvl == 3){
            humidity = "Moist (INT-- in H. Armor)";
        }
        else if (humidityLvl == 4){
            humidity = "Damp (INT--, Hotter)";
            tempLvl++;
        }
        else{
            humidity = "Dank (2x INT--)";
        }


        //TEMPERATURE LEVEL
        if (tempLvl < 0){
            temp = "Ice Age (2x END--, Mild DMG)";
        }
        else if (tempLvl == 0){
            temp = "Freezing (END--, Need Heat)";
        }
        else if (tempLvl == 1){
            temp = "Cool (END-- without Heat)";
        }
        else if (tempLvl == 2){
            temp = "Warm (No effect)";
        }
        else if (tempLvl == 3){
            temp = "Hot (END--)";
        }
        else if (tempLvl == 4){
            temp = "Burning (Minor DMG)";
        }
        else{
            temp = "Firestorm (Minor DMG per turn)";
        }


        //WIND LEVEL
        if (windLvl < 0){
            wind = "Silence (2x Stealth--)";
        }
        else if (windLvl == 0){
            wind = "Absent (DEX++, Ranged++)";
        }
        else if (windLvl == 1){
            wind = "Breezy (END++)";
        }
        else if (windLvl == 2){
            wind = "Average (No effect)";
        }
        else if (windLvl == 3){
            wind = "Piercing (Ranged--)";
        }
        else if (windLvl == 4){
            wind = "Dangerous (Ranged--, Melee--)";
        }
        else{
            wind = "Tornado (Major DMG Outdoors)";
        }


        //PRESSURE LEVEL
        if (pressureLvl < 0){
            pressure = "Weightless (2x DEX++, 2x STR++)";
        }
        else if (pressureLvl == 0){
            pressure = "Low (INT++, Melee++)";
        }
        else if (pressureLvl == 1){
            pressure = "Minor (Melee++)";
        }
        else if (pressureLvl == 2){
            pressure = "Mild (No effect)";
        }
        else if (pressureLvl == 3){
            pressure = "Considerable (Melee--)";
        }
        else if (pressureLvl == 4){
            pressure = "Disorientating (Melee--, Will--)";
        }
        else{
            pressure = "Crushing (2x All--)";
        }


    }

    public String ToString(){
        String s = new String();
        s += "Temperature: \t\t" + temp;
        s += "\nLight Coverage: \t" + light;
        s += "\nHumidity:  \t\t\t" + humidity;
        s += "\nPrecipitation: \t\t" + precip;
        s += "\nWind: \t\t\t\t" + wind;
        s += "\nAir Pressure: \t\t" + pressure;
        return s;
    }

}

class Crate{

    List<Loot> content;
    int wealth;

    public Crate(int _wealth){
        wealth = _wealth;
        content = new ArrayList<Loot>();
        for (int x = 0; x < wealth; x++){
            content.add(new Loot("crate"));
        }
    }
    
    public String ToString(){
        String s = new String();
        for (int x = 0; x < wealth; x++){
            s += "\n\t" + x + ": " + content.get(x).loot;
        }
        return s;
    }

}

class Loot{

    String type;
    String loot;

    public Loot(String _type){
        Random rand = new Random();
        type = _type;
        if (type.equals("crate")){
            int chance = rand.nextInt(20)+1;
            if (chance == 1){
                loot = "Coal";
            }
            else if (chance == 2){
                loot = "Ingots";
            }
            else if (chance == 3 || chance == 4){
                loot = "Shovel";
            }
            else if (chance == 5 || chance == 6){
                loot = "Pick";
            }
            else if (chance == 7){
                loot = "Eggs";
            }
            else if (chance == 8 || chance == 9){
                loot = "Hide";
            }
            else if (chance == 10){
                loot = "Hay";
            }
            else if (chance == 11 || chance == 12 || chance == 13){
                loot = "Wood";
            }
            else if (chance == 14){
                loot = "Knife";
            }
            else if (chance == 15 || chance == 16){
                loot = "Plow";
            }
            else if (chance == 16 || chance == 17){
                loot = "Saddle";
            }
            else if (chance == 18 || chance == 19){
                loot = "Hoe";
            }
            else if (chance == 20){
                loot = "Bricks";
            }
            else{
                loot = "ERR";
            }
        }
        else if (type.equals("barrel")){
            int chance = rand.nextInt(20)+1;
            if (chance == 1){
                loot = "Seeds";
            }
            else if (chance == 2 || chance == 3){
                loot = "Dried Meat";
            }
            else if (chance == 4 || chance == 5){
                loot = "Dried Veggies";
            }
            else if (chance == 6){
                loot = "Fruit Jam";
            }
            else if (7 <= chance && chance <= 9){
                loot = "Water";
            }
            else if (chance == 10 || chance == 11){
                loot = "Alcohol";
            }
            else if (chance == 12){
                loot = "Honey";
            }
            else if (chance == 13 || chance == 14){
                loot = "Grain";
            }
            else if (chance == 15){
                loot = "Hay";
            }
            else if (chance == 16 && chance == 17){
                loot = "Milk";
            }
            else if (chance == 18 || chance == 19){
                loot = "Salt";
            }
            else if (chance == 20){
                loot = "Saffron";
            }
            else{
                loot = "ERR";
            }
        }    
        else if (type.equals("chest")){
            int chance = rand.nextInt(20)+1;
            if (chance == 1){
                loot = "Blouse";
            }
            else if (chance == 2){
                loot = "Ingots";
            }
            else if (chance == 3){
                loot = "Fruit Jam";
            }
            else if (chance == 4){
                loot = "Overcoat";
            }
            else if (chance == 5){
                loot = "Boots";
            }
            else if (chance == 6){
                loot = "Trousers";
            }
            else if (chance == 7){
                loot = "Knife";
            }
            else if (chance == 8){
                loot = "Hat";
            }
            else if (chance == 9){
                loot = "Gloves";
            }
            else if (chance == 10 || chance == 11){
                loot = "Book";
            }
            else if (chance == 12){
                loot = "Deed";
            }
            else if (chance == 13 || chance == 14){
                loot = "Weapon";
            }
            else if (chance == 15 || chance == 16){
                loot = "Armor";
            }
            else if (chance == 17 || chance == 18){
                loot = "Ring";
            }
            else if (chance == 19 || chance == 20){
                loot = "Potion";
            }
        }
    }

}
