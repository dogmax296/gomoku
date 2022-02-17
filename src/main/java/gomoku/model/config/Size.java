package gomoku.model.config;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public enum Size {

    SIZE15,
    SIZE19;

    public int getSizeInt(){
        return Integer.parseInt(name().substring(4));
    }

}
