package commons.data.dataPage;

public enum ItemType {
    ACTUAL("[dusk=\"newest\"]"),
    POPULAR("[dusk=\"popular\"]");

    private final String type;

    ItemType(String type) {this.type = type;}

    public String getSelector() {return this.type;}
}
