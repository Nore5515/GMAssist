import java.util.List;

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



public class Main {
    public static void main(String args[]){
        System.out.println("Hello World!");






    }
}

class Crate{

    List<Loot> content;
    int wealth;

    public Crate(int _wealth){
        wealth = _wealth;
        for (int x = 0; x < wealth; x++){
            content.add(new Loot("crate"));
        }
    }

}

class Loot{

    String type;

    public Loot(String _type){
        type = _type;

    }

}
