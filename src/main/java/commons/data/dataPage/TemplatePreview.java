package commons.data.dataPage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemplatePreview {
    String brandName;
    String email;
    String phone;
    String address;
    String fontStyle;
    String headerFontStyle;
    String generalBackgroundColor;
    String headerColor;
    String footerColor;
    String buttonColor;
    String fontColor;
    String linkColor;
}
