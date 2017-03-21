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


public class Main {
    public static void main(String args[]){
        System.out.println("Hello World!");

        Crate c = new Crate(5);
        System.out.println(c.ToString());
        
        Room r = new Room(7,7);
        System.out.println("\n" + r.ToString());



    }
}

class Room{
    
    String[][] map;
    
    public Room(int h, int w){
        map = new String[w][h];
        for (int x = 0; x < w; x++){
            for (int y = 0; y < h; y++){
                if (x == 0 || x == w-1){
                    map[x][y] = "W";
                }
                else if (y == 0 || y == h-1){
                    map[x][y] = "W";
                }
                else{
                    map[x][y] = ".";
                }
            }
        }
    }
    
    public String ToString(){
        String s = new String();
        for (int x = 0; x < map.length; x++){
            for (int y = 0; y < map[0].length; y++){
                s += map[x][y];
            }
            s += "\n";
        }
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
