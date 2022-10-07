package co.pragra.learning;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Key in Data separated by space");

        String next = scanner.nextLine();
        String[] split = next.split(" ");
        Contact contact = Contact.builder().firstName(split[0])
                .lastName(split[1])
                .phoneNumber(split[2])
                .build();

        ContactService service = new ContactService();

        service.addContact(contact);

        System.out.println("Key in Data separated by space");
        String next1 = scanner.nextLine();
        String[] split1 = next1.split(" ");
        Contact contact1 = Contact.builder().firstName(split1[0])
                .lastName(split1[1])
                .phoneNumber(split1[2])
                .build();
        service.addContact(contact1);

        service.printAll();

    }
}
