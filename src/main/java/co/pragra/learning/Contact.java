package co.pragra.learning;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private ContactType contactType;
}
