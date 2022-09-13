package commons.data.dataPage;

public enum Locale {
 EN("en"), DE("de"), FR("fr"), ES("es"), IT("it"), PT("pt"), UA("ua"), RU("ru");

 private final String pref;

 Locale(String pref) {this.pref = pref;}

 public String getLocale() {
            return pref;
        }

}
