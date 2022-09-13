package commons.data.dataPage;

public enum TemplateType {
    ALL("[data-type=\"all\"]"),
    PREMIUM("[data-type=\"premium\"]"),
    FREE("[data-type=\"free\"]");

    private final String type;

    TemplateType(String type) {this.type = type;}

    public String getSelector() {return this.type;}
}
