package raf.graffito.dsw.model;

public abstract class GraffNode {

    protected String ime;
    protected int x;
    protected int y;

    public GraffNode(String ime, int x, int y) {
        this.ime = ime;
        this.x = x;
        this.y = y;
    }

    public abstract void draw();
    public abstract void move(int dx, int dy);

    public GraffNode findByName(String searchName){
        if(this.ime !=null && this.ime.equals(searchName)){
            return this;
        }
        return null;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
