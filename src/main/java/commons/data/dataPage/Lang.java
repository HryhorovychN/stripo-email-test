package commons.data.dataPage;

public enum Lang {
 EN("en"), DE("de"), FR("fr"), ES("es"), IT("it"), PT("pt"), UA("ua"), RU("ru");

 private final String pref;

 Lang(String pref) {this.pref = pref;}

 public String getLocale() {
            return pref;
        }

}
