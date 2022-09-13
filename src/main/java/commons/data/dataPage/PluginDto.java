package commons.data.dataPage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PluginDto {
        String senderName;
        String email;
        String website;
        String emailsCreated;
        String employees;
        String customers;
        String position;
        String phone;
    }

