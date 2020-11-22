import java.util.ArrayList;

/*
 * HeroTeam.java - class that creates a list of heroes in the players team.
 */
public class HeroTeam {
    private ArrayList<Hero> heros;

    public HeroTeam() {
        heros = new ArrayList<>();
    }

    public ArrayList<Hero> getHeros() {
        return this.heros;
    }

    public void setHeros(ArrayList<Hero> heros) {
        this.heros = heros;
    }
    public void add(Hero h) {
        heros.add(h);
    }


}
