package miinaharava.domain;

public class Miina {
    private int x;
    private int y;

    public Miina(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Miina verrattava = (Miina) obj;
        
        return (verrattava.x == this.x && verrattava.y == this.y);
    }
    
    
    
    
}
