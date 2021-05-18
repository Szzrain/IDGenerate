import java.util.Map;

public class Place {
    public String code;
    public Map<String,String> region;

    public Place(String name, Map<String, String> region) {
        this.code = name;
        this.region = region;
    }

    @Override
    public String toString() {
        return "\n"+code+region;
    }
}
