package co.pragra.learning;

import co.pragra.learning.Contact;
import co.pragra.learning.ContactType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if(!contact.getFirstName().matches("[a-zA-Z]+")){
            throw new RuntimeException("First name can not have numbers and special characters");
        }
        if(!contact.getLastName().matches("[a-zA-Z]+")){
            throw new RuntimeException("Last name can not have numbers and special characters");
        }
        if(!contact.getPhoneNumber().matches("[0-9]+")){
            throw new RuntimeException("Phone number should only have numbers");
        }
        int index = checkIfExist(contact.getPhoneNumber());
        if(index>=0){
            contacts.set(index,contact);
        }else {
            contact.setContactType(ContactType.PERSONAL);
            contacts.add(contact);
        }
    }

    public int checkIfExist(String phone) {
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getPhoneNumber().equals(phone)){
                return i;
            }
        }
        return -1;
    }

    public int getContactSize(){
        return contacts.size();
    }

    public Contact getContactByPhone(String phone) {
        if (!StringUtils.isNumeric(phone)) {
           throw new RuntimeException("Supplied phone is not a numeric value");
        }
        int exist = checkIfExist(phone);
        if(exist>=0){
            return contacts.get(exist);
        }else {
            throw new RuntimeException("Contact Doesn't exits");
        }
    }

    public void deleteContact(String phone){
        if(!StringUtils.isNumeric(phone)){
            throw new RuntimeException("Phone should only contain numbers");
        }
        int present = checkIfExist(phone);
        if(present >=0){
            contacts.remove(present);
        }else{
            throw new NullPointerException("Contact doesn't exist");
        }
    }

    public void printAll() {
        if(contacts.size()>0){
            for (Contact c: contacts) {
                System.out.println(c);
            }
        } else{
            System.out.println("No contact records found");
        }
    }


}

