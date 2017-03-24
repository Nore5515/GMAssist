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
        
        /*Room r = new Room(7,7);
        System.out.println("\n" + r.ToString());
        r.AddCrates();
        r.AddBarrels();
        r.AddHoles();
        System.out.println("\n" + r.ToString());
        */

        //Event e = new Event();
        //System.out.println(e.ToString());

        Story s = new Story();
        System.out.println(s.ToString());


        //Weather w = new Weather();
        //System.out.println(w.ToString());

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

class Story{

    String type,theme,main,setting,rising,climax,motif;
    int chance;

    public Story(){
        Random rand = new Random();

        //TYPE OF STORY
        chance = rand.nextInt(4);
        if (chance == 0){
            type = "a scroll";
        } else if (chance == 1){
            type = "a play";
        } else if (chance == 2){
            type = "a story";
        } else if (chance == 3){
            type = "a poem";
        } else{
            type = "ERR";
        }

        //THEME
        chance = rand.nextInt(4);
        if (chance == 0){
            theme = "true love";
        } else if (chance == 1){
            theme = "the unknown";
        } else if (chance == 2){
            theme = "revenge";
        } else if (chance == 3){
            theme = "justice";
        } else{
            theme = "ERR";
        }

        //MAIN
        chance = rand.nextInt(4);
        if (chance == 0){
            main = "a ranger";
        } else if (chance == 1){
            main = "a wanderer";
        } else if (chance == 2){
            main = "a lowly farmer";
        } else if (chance == 3){
            main = "a noble";
        } else{
            main = "ERR";
        }

        //SETTING
        chance = rand.nextInt(4);
        if (chance == 0){
            setting = "a forgotten place";
        } else if (chance == 1){
            setting = "another dimension";
        } else if (chance == 2){
            setting = "the criminal underground";
        } else if (chance == 3){
            setting = "the city streets";
        } else{
            setting = "ERR";
        }

        //ACTION RISING
        chance = rand.nextInt(4);
        if (chance == 0){
            rising = "a threat on their life";
        } else if (chance == 1){
            rising = "someone framing them for murder";
        } else if (chance == 2){
            rising = "finding their true love";
        } else if (chance == 3){
            rising = "learning forbidden secrets";
        } else{
            rising = "ERR";
        }

        //Climax
        chance = rand.nextInt(4);
        if (chance == 0){
            climax = "a final battle";
        } else if (chance == 1){
            climax = "a showdown";
        } else if (chance == 2){
            climax = "a duel";
        } else if (chance == 3){
            climax = "a challenge";
        } else{
            climax = "ERR";
        }

        //motif
        chance = rand.nextInt(4);
        if (chance == 0){
            motif = "Religion";
        } else if (chance == 1){
            motif = "Killing";
        } else if (chance == 2){
            motif = "Guilt";
        } else if (chance == 3){
            motif = "Honor";
        } else{
            motif = "ERR";
        }
    }

    public String ToString(){
        String s = new String();
        s += "This is " + type + " about " + theme + ".\nThe story is about " +main + ", taking place in " + setting + ".\n";
        s += "It is about " + rising + ", eventually leading to " + climax +".\n";
        s += motif + " plays an important role in the story.";
        return s;
    }

}

class Event{

    String attribute, attributeDesc, item, value, relevence, omen;
    int amount, chance;
    boolean moving;

    public Event(){
        Random rand = new Random();
        chance = rand.nextInt(2);
        //Still
        if (chance == 0){
            moving = false;
        }
        //Moving
        else if (chance == 1){
            moving = true;
        }
        else{
            System.out.println("ERROR");
        }

        chance = rand.nextInt(6);
        //Item
        if (chance == 0){
            if (moving){
                item = "Fog";
            }
            else{
                item = "Tree";
            }
        }
        else if (chance == 1){
            if (moving){
                item = "Snail";
            }
            else{
                item = "Bridge";
            }
        }
        else if (chance == 2){
            if (moving){
                item = "Butterfly";
            }
            else{
                item = "Fruit";
            }
        }
        else if (chance == 3){
            if (moving){
                item = "Worm";
            }
            else{
                item = "Bone";
            }
        }
        else if (chance == 4){
            if (moving){
                item = "Leaf";
            }
            else{
                item = "Tower";
            }
        }
        else if (chance == 5){
            if (moving){
                item = "Floating Orb";
            }
            else{
                item = "Wreckage";
            }
        }
        else{
            item = "ERR";
        }

        //ATTRIBUTES
        chance = rand.nextInt(10);
        if (chance == 0){
            attribute = "Giant";
        }
        else if (chance == 1){
            attribute = "Tiny";
        }
        else if (chance == 2){
            if (moving){
                attribute = "Fast";
            }
            else{
                attribute = "Tasty";
            }
        }
        else if (chance == 3){
            if (moving){
                attribute = "Slow";
            }
            else{
                attribute = "Smelly";
            }
        }
        else if (chance == 4){
            attribute = "Tasty";
        }
        else if (chance == 5){
            attribute = "Smelly";
        }
        else if (chance == 6){
            if (moving){
                attribute = "Flying";
            }
            else{
                attribute = "Engraved";
            }
        }
        else if (chance == 7){
            attribute = "Engraved";
        }
        else if (chance == 8){
            attribute = "Meaty";
        }
        else if (chance == 9){
            attribute = "Sentient";
        }

        //AMOUNT
        chance = rand.nextInt(8);
        if (chance  <= 3){
            amount = 1;
        }
        else if (chance == 4){
            amount = 2;
        }
        else if (chance == 5){
            amount = 3;
        }
        else if (chance == 6){
            amount = 5;
        }
        else if (chance == 7){
            amount = 20;
        }
        else{
            amount = -999999;
        }

        //VALUE
        chance = rand.nextInt(6);
        if (chance == 0){
            value = "Worthless";
        }
        else if (chance == 1){
            value = "Prized";
        }
        else if (chance == 2){
            value = "Mediocre";
        }
        else if (chance == 3){
            value = "Above-Average";
        }
        else if (chance == 4){
            value = "Decadent";
        }
        else if (chance == 5){
            value = "Incredible";
        }
        else{
            value = "ERR";
        }

        //RELEVANCE
        chance = rand.nextInt(8);
        if (chance == 0){
            relevence = "Father";
        }
        else if (chance == 1){
            relevence = "Past";
        }
        else if (chance == 2){
            relevence = "Future";
        }
        else if (chance == 3){
            relevence = "Now";
        }
        else if (chance == 4){
            relevence = "Enemy";
        }
        else if (chance == 5){
            relevence = "Friend";
        }
        else if (chance == 6){
            relevence = "Global";
        }
        else if (chance == 7){
            relevence = "Mother";
        }
        else{
            relevence = "ERR";
        }

        /*
        //OMEN
        chance = rand.nextInt(9);
        if (chance == 0){
            omen = "Death";
        } else if (chance == 1){
            omen = "Creation";
        }
        else if (chance == 2) {
            omen = "Journey";
        }
        else if (chance == 3) {
            omen = "Love";
        }
        else if (chance == 4){
            omen = "Challenges";
        }
        else if (chance == 5) {
            omen = "Ignorance";
        }
        else if (chance == 6){
            omen = "Fortune";
        }
        else if (chance == 7){
            omen = "Sickness";
        }
        else if (chance == 8) {
            omen = "Secrets";
        }
        */


        //Unique Attribute Testing
        if (attribute.equals("Tasty")){
            attributeDesc = "It has a vaguely " + GetFlavor() + " flavor.";
        } else if (attribute.equals("Smelly")){
            attributeDesc = "It reeks of " + GetSmell() +".";
        } else if (attribute.equals("Engraved")){
            attributeDesc = "It has " + GetEngraving() + " engraved on it.";
        } else if (attribute.equals("Engraved")){
            attributeDesc = "It has " + GetEngraving() + " engraved on it.";
        } else if (attribute.equals("Giant")){
            attributeDesc = "It " + GetBig();
        } else if (attribute.equals("Tiny")){
            attributeDesc = "It " + GetSmall();
        } else if (attribute.equals("Fast")){
            attributeDesc = "It " + GetFast();
        } else if (attribute.equals("Slow")){
            attributeDesc = "It " + GetSlow();
        } else if (attribute.equals("Flying")){
            attributeDesc = "It " + GetFlying();
        } else if (attribute.equals("Meaty")){
            attributeDesc = "It " + GetMeaty();
        } else if (attribute.equals("Sentient")){
            attributeDesc = "It " + GetSentient();
        } else{
            attributeDesc = "ERR";
        }

        //Relevence to Text
        if (relevence.equals("Father")){
            relevence = "It vaguely reminds you of your father.";
        } else if (relevence.equals("Past")){
            relevence = "Have you seen this before?";
        } else if (relevence.equals("Future")){
            relevence = "Perhaps of some use, at some point?";
        } else if (relevence.equals("Now")){
            relevence = "This feels important.";
        } else if (relevence.equals("Enemy")){
            relevence = "Looking at it makes you angry.";
        } else if (relevence.equals("Friend")){
            relevence = "Looking at it makes you feel homesick.";
        } else if (relevence.equals("Global")){
            relevence = "This feels very important.";
        } else if (relevence.equals("Mother")){
            relevence = "It vaguely reminds you of your mother.";
        } else{
            relevence = "ERR";
        }



        //Value to Text
        if (value.equals("Worthless")){
            value = "It would be hard to find someone who would want this.";
        } else if (value.equals("Decadent")){
            value = "Selling this to someone would be insulting.";
        } else if (value.equals("Mediocre")){
            value = "It probably has some value.";
        } else if (value.equals("Above-Average")){
            value = "It's properties could be of some value.";
        } else if (value.equals("Prized")) {
            value = "It seems of great value.";
        } else if (value.equals("Incredible")){
            value = "This could sell for a LOT of money.";
        } else{
            value = "ERR";
        }
    }

    private String GetFlying(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "flutters in the wind.";
        } else if (chance == 1){
            return "furiously flaps a little pair of wings.";
        } else if (chance == 2){
            return "levitates a few feet off the ground.";
        } else if (chance == 3){
            return "rises like a balloon.";
        }
        else{
            return "ERR";
        }
    }

    private String GetMeaty(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "has bulging veins.";
        } else if (chance == 1){
            return "has well toned muscles.";
        } else if (chance == 2){
            return "is awfully squishy.";
        } else if (chance == 3){
            return "is beating like a heart.";
        }
        else{
            return "ERR";
        }
    }

    private String GetSentient(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "seems to be watching you.";
        } else if (chance == 1){
            return "makes weird noises when not watched.";
        } else if (chance == 2){
            return "is continuously babbling about nonsense.";
        } else if (chance == 3){
            return "moves when not watched.";
        }
        else{
            return "ERR";
        }
    }

    private String GetFast(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "becomes a blur when it moves.";
        } else if (chance == 1){
            return "could cut you if you get too close.";
        } else if (chance == 2){
            return "moves at a brisk pace.";
        } else if (chance == 3){
            return "is hard to keep up with.";
        }
        else{
            return "ERR";
        }
    }

    private String GetSlow(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "is slower than a snail.";
        } else if (chance == 1){
            return "is growing moss.";
        } else if (chance == 2){
            return "slowly trudges around.";
        } else if (chance == 3){
            return "is slower than a rock.";
        }
        else{
            return "ERR";
        }
    }

    private String GetSmall(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "sits below your ankles.";
        } else if (chance == 1){
            return "sits a few feet beneath you.";
        } else if (chance == 2){
            return "could get squashed by an ant.";
        } else if (chance == 3){
            return "fits in your hand perfectly.";
        }
        else{
            return "ERR";
        }
    }

    private String GetBig(){
        Random rand = new Random();
        int chance = rand.nextInt(4);
        if (chance == 0){
            return "towers over you.";
        } else if (chance == 1){
            return "could step on you.";
        } else if (chance == 2){
            return "has a few feet on you.";
        } else if (chance == 3){
            return "could throw a mountain at you.";
        }
        else{
            return "ERR";
        }
    }

    private String GetEngraving(){
        Random rand = new Random();
        int chance = rand.nextInt(33);
        if (chance == 0){
            if (relevence.equals("Father")){
                return "your father's name";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 1){
            if (relevence.equals("Mother")){
                return "your mother's name";
            }
            else{
                return GetEngraving();
            }
        }else if (chance == 2){
            if (relevence.equals("Past")){
                return "your own name";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 3){
            if (relevence.equals("Father")){
                return "your father's initials";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 4){
            if (relevence.equals("Mother")){
                return "your mother's initials";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 5){
            if (relevence.equals("Past")){
                return "your own initials";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 6){
            if (relevence.equals("Father")){
                return "'(Father's name) is a dirty " + GetInsult();
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 7){
            if (relevence.equals("Mother")){
                return "'(Mother's name) is a dirty " + GetInsult();
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 8){
            if (relevence.equals("Past")){
                return "'(Your name) is a dirty " + GetInsult();
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 9){
            if (relevence.equals("Future")){
                return "'Beware of (Future Encounter)";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 10){
            if (relevence.equals("Enemy")){
                return "'(Enemy's name) is a dirty " + GetInsult();
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 11){
            if (relevence.equals("Enemy")){
                return "your enemy's name";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 12){
            if (relevence.equals("Friend")){
                return "'(Friend's name) is a dirty " + GetInsult();
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 13){
            if (relevence.equals("Enemy")){
                return "'(Your name) bows to (Enemy's name)!'";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 14){
            if (relevence.equals("Future")){
                return "'(Future encounter) is because of (Your name)!'";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 15){
            if (relevence.equals("Now")){
                return "If you are reading this, it is too late.";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 16){
            if (relevence.equals("Now")){
                return "'This is my turf!'";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 17){
            if (relevence.equals("Enemy")){
                return "'This is (Enemy's name)'s turf!";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 18){
            if (relevence.equals("Now")){
                return "you, reading this engraving";
            }
            else{
                return GetEngraving();
            }
        } else if (chance == 19){
            return "'Tunnel Snakes rule!'";
        } else if (chance == 20){
            return "arcane symbols";
        } else if (chance == 21){
            return "a sly cat";
        } else if (chance == 22){
            return "a guard dog";
        } else if (chance == 23){
            return "a fox's head";
        } else if (chance == 24){
            return "the infinity symbol";
        } else if (chance == 25){
            return "an eagle";
        } else if (chance == 26){
            return "a stick figure";
        } else if (chance == 27){
            return "something phallic";
        } else if (chance == 28){
            return "a cross";
        } else if (chance == 29){
            return "a sickle";
        } else if (chance == 30){
            return "a starburst";
        } else if (chance == 31){
            return "a smiling face";
        } else if (chance == 32){
            return "a frowning face";
        } else{
            return "ERR";
        }
    }

    private String GetInsult(){
        Random rand = new Random();
        int chance = rand.nextInt(5);
        if (chance == 0){
            return "Thief";
        } else if (chance == 1) {
            return "Adulterer";
        } else if (chance == 2){
            return "Cheater";
        } else if (chance == 3){
            return "Murderer";
        } else if (chance == 4){
            return "Monster";
        } else{
            return "ERR";
        }
    }

    private String GetFlavor(){
        Random rand = new Random();
        int chance = rand.nextInt(7);
        if (chance == 0){
            return "Lemony";
        } else if (chance == 1) {
            return "Chicken";
        } else if (chance == 2){
            return "Minty";
        } else if (chance == 3){
            return "Sugary";
        } else if (chance == 4){
            return "Fruity";
        } else if (chance == 5){
            return "Buttery";
        } else if (chance == 6){
            return "Carmel";
        } else{
            return "ERR";
        }
    }

    private String GetSmell(){
        Random rand = new Random();
        int chance = rand.nextInt(7);
        if (chance == 0){
            return "Rot";
        } else if (chance == 1) {
            return "Mold";
        } else if (chance == 2){
            return "Vomit";
        } else if (chance == 3){
            return "Garlic";
        } else if (chance == 4){
            return "Fish";
        } else if (chance == 5){
            return "Cheese";
        } else if (chance == 6){
            return "Smoke";
        } else{
            return "ERR";
        }
    }

    public String ToString(){
        String s = new String();
        String sVal = new String();
        if (amount > 1){
            sVal = "s";
        }else{
            sVal = "";
        }
        s += amount + " " + item + sVal +".\n\t" + attributeDesc;
        s += "\n\t" + value;
        s += "\n\t" + relevence;

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
