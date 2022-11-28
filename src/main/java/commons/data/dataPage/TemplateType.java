package commons.data.dataPage;

public enum TemplateType {
    ALL(""),
    PREMIUM("[name=\"is_premium\"]"),
    FREE("[name=\"is_free\"]");

    private final String type;

    TemplateType(String type) {this.type = type;}

    public String getSelector() {return this.type;}
}
