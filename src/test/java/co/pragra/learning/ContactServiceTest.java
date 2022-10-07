package co.pragra.learning;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContactServiceTest {

    private final ContactService service = new ContactService();

    @Before
    public void setUp() throws Exception {
        Contact contact = Contact.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("4566547893")
                .build();
        service.addContact(contact);

        Contact contact1 = Contact.builder()
                .firstName("Jason")
                .lastName("Woolgar")
                .phoneNumber("6548523759")
                .build();
        service.addContact(contact1);
    }

    @Test
    public void getContactsList() {
        Contact contact = Contact.builder()
                .firstName("Steve")
                .lastName("Harvey")
                .phoneNumber("4568523189")
                .build();
        service.addContact(contact);
        Assert.assertEquals(3, service.getContactSize());
    }

    @Test
    public void addValidContact() {
        int size = service.getContactSize();

        Contact contact = Contact.builder()
                .firstName("Sahrukh")
                .lastName("Khan")
                .phoneNumber("4662235453")
                .build();
        service.addContact(contact);

        Assert.assertTrue(service.getContactSize() > size);
    }

    @Test
    public void addInvalidContact() {
        int size = service.getContactSize();

        Contact contact = Contact.builder()
                .firstName("Sahrukh")
                .lastName("Khan")
                .phoneNumber("46adf622354g53")
                .build();
        try {
            service.addContact(contact);
        } catch (Exception ex) {
        }

        Assert.assertTrue(service.getContactSize() == size);
    }

    @Test
    public void getExistingContact() {
        Contact existingContact = service.getContactByPhone("4566547893");

        Assert.assertTrue(existingContact.getFirstName().equals("John") &&
                existingContact.getLastName().equals("Doe") &&
                existingContact.getContactType().equals(ContactType.PERSONAL));
    }

    @Test
    public void getNonExistingContact() {
        String m = "";
        try{
            Contact existingContact = service.getContactByPhone("45665478938");
        }catch(RuntimeException e){
            m = e.getMessage();
        }
        Assert.assertEquals("Contact Doesn't exits", m);
    }

    @Test
    public void updateExistingContact() {
        Contact existingContact = service.getContactByPhone("4566547893");

        Contact contact = Contact.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("4566547893")
                .contactType(ContactType.BUSINESS)
                .build();

        service.addContact(contact);

        Contact updatedContact = service.getContactByPhone("4566547893");

        Assert.assertNotEquals(existingContact.getContactType(), updatedContact.getContactType());
    }



    @Test
    public void deleteExistingContact() {
        int size = service.getContactSize();

        service.deleteContact("4566547893");

        Assert.assertTrue(service.getContactSize() < size);
    }

    @Test
    public void deleteNonExistingContact() {
        int size = service.getContactSize();

        try {
            service.deleteContact("45665478935");
        }catch (NullPointerException ex) {
            ex.getMessage();
        }

        Assert.assertFalse(service.getContactSize() < size);
    }

    @Test
    public void checkExistingContact(){
        Assert.assertEquals(1, service.checkIfExist("6548523759"));
    }

    @Test
    public void checkNonExistingContact(){
        Assert.assertEquals(-1, service.checkIfExist("6548523767"));
    }

    @After
    public void tearDown() throws Exception {

    }
}