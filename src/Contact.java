class Contact {
    //new contact information variables
    private String lastName, firstName, phoneNumber, emailAddress, address;
    private String[] fields;

    final public static String[] CONTACT_FIELDS = {"First Name", "Last Name", "Email", "Phone", "Address"};

    /**********
     *name: getLastName --> GetNotesUser
     *description: Each method returns the related variable
     *input/output: no input, out is the related variable
     ***************************/

    public String getField(int fieldIndex) {
        return fields[fieldIndex];
    }

    /**********
     *name: ContactHelper
     *description: attaches the class's variables to the method inputs
     *input/output: input is all the current contact's information
     ***************************/

    public Contact(String[] fields) {
        this.fields = fields;
    }

    public Contact() {
        fields = new String[CONTACT_FIELDS.length];
        for (int i = 0; i < CONTACT_FIELDS.length; i++) {
            fields[i] = "";
        }
    }

    /**********
     *name: toString
     *description: prints the contact's information in a clean way
     *input/output: no input, output is a string (the information of the current contact)
     ***************************/
    //fixxxxxxx
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n   Phone: " + phoneNumber + ", Email: " + emailAddress + ", Address: " + address;
    }

    /**********
     *name: setAll
     *description: sets all the method inputs to the class's variables
     *input/output: no input, no output (void)
     ***************************/

    public void setAll(String[] fields) {
        this.fields = fields;
    }

    /**********
     *name: contains
     *description: returns whether the inputed string matches that of an element of the array that stores the current contact's information
     *input/output: input is string s, output is a boolean value
     ***************************/
    //fixxxxxxxxx
    public boolean contains(String s) {
        return s.equals(lastName) || s.equals(firstName) || s.equals(phoneNumber) || s.equals(emailAddress) || s.equals(address);
    }

    /**********
     *name: 
     *description:
     *input/output:no input, no output (void)
     ***************************/
    public String[] asArray() {
        return fields;
    }
}
