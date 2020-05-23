import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class CSVGrid extends JScrollPane {
    private boolean editing;
    private ArrayList<Contact> contacts;
    private ArrayList<Integer> map;
    private ArrayList<Integer> mapColumns;
    private HighlightableRowsPanel content;
    private JPanel top;
    private HighlightableRowsPanel side;
    private int lineCount;
    private int headerCount;
    private Path path;


    private ArrayList<JLabel> lineNumbers;
    private ArrayList<JTextField> fields;
    private ArrayList<JCheckBox> checkboxes;

    private Color backgroundColor;
    private Color labelColor;
    private Color textFieldBackgroundColor;
    private Color defaultFieldBackground;
    private Color highlightLabelColor;
    private Color fieldTextEditColor;

    private ImageIcon checkboxSelected;
    private ImageIcon checkboxDeselected;

    //use path to get contacts when CSVGrid is created
    public CSVGrid(Path path) {
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException c) {
            System.out.println("Look and Feel broken");
        }
        editing = false;
        this.path = path;
        contacts = new ArrayList<>();
        headerCount = Contact.CONTACT_FIELDS.length;
        try {
            //number of lines to read inside file
            lineCount = (int) Files.lines(path).count();
            Scanner lineReader = new Scanner(path);
            //the first line of a CSV file contains the headers, separated by commas
            lineReader.nextLine();
            System.out.println("line count: " + headerCount);
            //reads the values under the headers
            for (int i = 0; i < lineCount - 1; i++) {
                String[] contactInfo = lineReader.nextLine().split(",");
                contacts.add(new Contact(contactInfo));
            }

        } catch (IOException e) {
            //this shouldn't happen, because bad paths were checked for before, but java requires this.
            e.printStackTrace();
            System.out.println("BAD PATH");
            System.exit(0);
        }

        //creates the initial map for columns (though maps for columns are not used)
        map = new ArrayList<>();
        for (int i = 0; i < lineCount - 1; i++) {
            map.add(i);
        }

        //creates the initial map for the position of columns.
        mapColumns = new ArrayList<>();
        for (int i = 0; i < headerCount; i++) {
            mapColumns.add(i);
        }

        //JLabels for line numbers
        lineNumbers = new ArrayList<>();

        //layout for panel
        GridBagLayout contentLayout = new GridBagLayout();
        //creates the panel that contains the JTextFields to be scrolled
        content = new HighlightableRowsPanel(lineNumbers, 25);

        content.setLayout(contentLayout);


        //the headers of the grid (won't disappear when scrolling down)
        top = new JPanel();
        GridBagLayout topLayout = new GridBagLayout();
        top.setLayout(topLayout);

        //JLabels for headers
        JLabel[] headers = new JLabel[headerCount];

        //the numbers of the grid (won't disappear when scrolling side to side)
        GridBagLayout sideLayout = new GridBagLayout();
        side = new HighlightableRowsPanel(lineNumbers, 25);
        side.setLayout(sideLayout);

        //constraints help position the contacts in a grid
        GridBagConstraints constraints = new GridBagConstraints();

        content.setBorder(new EmptyBorder(0, 0, 0, 0));
        top.setBorder(new EmptyBorder(0, 0, 0, 0));
        side.setBorder(new EmptyBorder(0, 0, 0, 0));

        //instantiates the line numbers and adds them to the side
        for (int i = 0; i < map.size(); i++) {
            lineNumbers.add(new JLabel(Integer.toString(i + 1)));
            lineNumbers.get(i).setMinimumSize(new Dimension(0, 25));
            lineNumbers.get(i).setPreferredSize(new Dimension(lineNumbers.get(i).getPreferredSize().width, 25));

            constraints.weightx = 0;
            constraints.weighty = 1;
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.anchor = GridBagConstraints.PAGE_START;

            side.add(lineNumbers.get(i), constraints);
        }

        //instantiates the headers and adds them to the top
        for (int i = 0; i < headerCount; i++) {
            headers[i] = new JLabel();
            headers[i].setMinimumSize(new Dimension(100, 25));
            headers[i].setPreferredSize(new Dimension(160, 25));

            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.gridx = i;
            constraints.gridy = 0;

            headers[i].setText(Contact.CONTACT_FIELDS[i]);
            top.add(headers[i], constraints);
        }

        //JTextFields for fields
        fields = new ArrayList<>(map.size() * headerCount);

        //checkboxes
        checkboxes = new ArrayList<>(map.size());

        //instantiating and adding fields and checkboxes
        for (int i = 0; i < map.size(); i++) {
            final int row = i;
            for (int j = 0; j < headerCount; j++) {
                final int column = j;
                JTextField field = new JTextField();
                //listener that edits the values inside of the CSVObject when the JTextField is edited
                FocusRemovedListener f = (e) -> {
                    JTextField text = (JTextField) e.getComponent();
                    String[] info = getContact(row).asArray();
                    info[column] = text.getText();

                    getContact(row).setAll(info);
                };

                field.setEditable(false);
                field.addFocusListener(f);
                field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                field.setBorder(new EmptyBorder(0, 10, 0, 0));

                field.setForeground(defaultFieldBackground);
                if (j == 0) {
                    field.setMinimumSize(new Dimension(120, 25));
                    field.setPreferredSize(new Dimension(120, 25));
                } else if (j == 1) {
                    field.setMinimumSize(new Dimension(120, 25));
                    field.setPreferredSize(new Dimension(120, 25));
                } else if (j == 2) {
                    field.setMinimumSize(new Dimension(200, 25));
                    field.setPreferredSize(new Dimension(200, 25));
                } else if (j == 3) {
                    field.setMinimumSize(new Dimension(150, 25));
                    field.setPreferredSize(new Dimension(150, 25));
                } else {
                    field.setMinimumSize(new Dimension(200, 25));
                    field.setPreferredSize(new Dimension(200, 25));
                }

                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.gridx = j + 1;
                constraints.gridy = i;
                constraints.anchor = GridBagConstraints.PAGE_START;

                field.setText(getContact(i).asArray()[j]);

                content.add(field, constraints);
                fields.add(field);

            }

            if (!fields.isEmpty()) {
                defaultFieldBackground = fields.get(0).getForeground();
            }

            JCheckBox checkbox = new JCheckBox();
            checkbox.setMinimumSize(new Dimension(25, 25));
            checkbox.setPreferredSize(new Dimension(25, 25));
            checkbox.setSelectedIcon(checkboxSelected);
            checkbox.setIcon(checkboxDeselected);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.anchor = GridBagConstraints.PAGE_START;

            //highlights the row if checkbox is selected, and changes line number colour to white
            checkbox.addActionListener(a -> {
                if (checkbox.isSelected()) {
                    content.highlightRow(row);
                    side.highlightRow(row);
                    lineNumbers.get(row).setForeground(highlightLabelColor);
                } else {
                    content.unhighlightRow(row);
                    side.unhighlightRow(row);
                    lineNumbers.get(row).setForeground(labelColor);
                }
            });

            content.add(checkbox, constraints);
            checkboxes.add(checkbox);

        }

        //attaches the top, side, and content to the JScrollPane
        this.setViewportView(content);
        this.setRowHeaderView(side);


    }

    /**********
     *name: getContact
     *description: gets the contact at a row using the link at a certain part of the map
     *input/output: row number (int), contact that visually appears in that row (Contact)
     ***************************/
    public Contact getContact(int row) {
        return contacts.get(map.get(row));
    }

    /**********
     *name: addContact
     *description: takes in a contact and shows it in GUI
     *input/output: a contact (Contact), no output (void)
     ***************************/
    public void addContact(Contact c) {

        GridBagConstraints constraints = new GridBagConstraints();
        lineCount++;
        //add contact to array
        contacts.add(c);
        //create map link
        map.add(lineCount - 2);

        final int row = map.size() - 1;

        //creates GUI elements for new row (similar to creating GUI elements in constructor)
        for (int j = 0; j < headerCount; j++) {
            final int column = j;
            JTextField field = new JTextField();

            FocusRemovedListener f = (e) -> {
                JTextField text = (JTextField) e.getComponent();
                String[] info = getContact(row).asArray();
                info[column] = text.getText();

                getContact(row).setAll(info);
            };

            field.setEditable(false);
            field.addFocusListener(f);

            if (j == 0) {
                field.setMinimumSize(new Dimension(120, 25));
                field.setPreferredSize(new Dimension(120, 25));
            } else if (j == 1) {
                field.setMinimumSize(new Dimension(120, 25));
                field.setPreferredSize(new Dimension(120, 25));
            } else if (j == 2) {
                field.setMinimumSize(new Dimension(200, 25));
                field.setPreferredSize(new Dimension(200, 25));
            } else if (j == 3) {
                field.setMinimumSize(new Dimension(150, 25));
                field.setPreferredSize(new Dimension(150, 25));
            } else {
                field.setMinimumSize(new Dimension(200, 25));
                field.setPreferredSize(new Dimension(200, 25));
            }
            field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.gridx = j + 1;
            constraints.gridy = row;
            constraints.anchor = GridBagConstraints.PAGE_START;

            field.setText(c.getField(j));
            field.setBorder(new EmptyBorder(0, 10, 0, 0));
            field.setBackground(textFieldBackgroundColor);
            content.add(field, constraints);
            fields.add(field);
        }

        //defaultFieldBackground = fields.get(fields.size() - 1).getForeground();

        JLabel lineNumber = new JLabel(Integer.toString(row + 1));
        lineNumber.setMinimumSize(new Dimension(0, 25));
        lineNumber.setPreferredSize(new Dimension(lineNumber.getPreferredSize().width, 25));

        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.PAGE_START;

        lineNumber.setForeground(labelColor);
        side.add(lineNumber, constraints);
        lineNumbers.add(lineNumber);

        JCheckBox checkbox = new JCheckBox();
        checkbox.setMinimumSize(new Dimension(25, 25));
        checkbox.setPreferredSize(new Dimension(25, 25));
        checkbox.setSelectedIcon(checkboxSelected);
        checkbox.setIcon(checkboxDeselected);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.PAGE_START;

        checkbox.addActionListener(a -> {
            if (checkbox.isSelected()) {
                content.highlightRow(row);
                side.highlightRow(row);
                lineNumbers.get(row).setForeground(highlightLabelColor);
            } else {
                content.unhighlightRow(row);
                side.unhighlightRow(row);
                lineNumbers.get(row).setForeground(labelColor);
            }
        });

        content.add(checkbox, constraints);
        checkboxes.add(checkbox);

        //ensures new row is visible properly
        this.validate();
        this.repaint();

        System.out.println(lineNumbers.size());
    }

    /**********
     *name: save
     *description: save contacts in the contacts array in a CSV file
     *input/output:no input, no output (void)
     ***************************/
    public void save() {
        try {
            PrintWriter writer = new PrintWriter(path.toFile(), "UTF-8");
            writer.print(toString());
            writer.close();
        } catch (FileNotFoundException f) {
            System.out.println("error");
            f.printStackTrace();
        } catch (UnsupportedEncodingException u) {
            System.out.println("other");
        }
    }

    /**********
     *name: toString
     *description: arranges all contact fields in CSV format
     *input/output:no input, CSV string (String)
     ***************************/
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < headerCount - 1; i++) {
            finalString.append(Contact.CONTACT_FIELDS[i]).append(",");
        }
        finalString.append(Contact.CONTACT_FIELDS[headerCount - 1]).append("\n");
        for (Integer integer : map) {
            String[] info = contacts.get(integer).asArray();
            for (int j = 0; j < headerCount - 1; j++) {
                finalString.append((info[j].equals("")) ? " " : info[j]).append(",");
            }
            finalString.append((info[headerCount - 1].equals("")) ? " " : info[headerCount - 1]).append("\n");
        }
        return finalString.toString();
    }

    /**********
     *name: searches
     *description: selects contacts that have fields identical to searchTerm
     *input/output: searchTerm, no output (void)
     ***************************/
    public void searches(String searchTerm) {
        System.out.println("yes");
        //arraylist will store the contact number that contains the search searchTerm

        ArrayList<Integer> locations = new ArrayList<>();
        //used to write to arraylist
        int posLast = 0;
        boolean found = false;
        for (int i = 0; i < map.size(); i++) {
            //takes in a row from the grid in the form of a string array
            String[] current = getContact(i).asArray();
            for (int a = 0; a < headerCount; a++) {
                //if an element of the array is the same as the search term, boolean found is set to true
                if (current[a].equals(searchTerm)) {
                    found = true;
                    System.out.println("found");
                }
            }
            if (found) {
                //writes to the element at posLast and increments (so it writes to the next element)
                locations.add(posLast, i);
                posLast++;

            }
            //resets the boolean in preparation for the next row
            found = false;
        }

        for (int row : new ArrayList<>(content.rows)) {
            checkboxes.get(row).doClick();
        }

        for (int row : locations) {
            checkboxes.get(row).doClick();
        }
    }


    /**********
     *name: sort
     *description: rearranges the map according to sort type and then updates field values
     *input/output: field type, no output (void)
     ***************************/
    public void sort(String field, int type) {
        //System.out.print("Sort! Field: " + field + " & Direction: ");
        if (type == 1) {
            System.out.print("A-Z");
        } else {
            System.out.print("Z-A");
        }
        System.out.println();
        int numbLine = map.size();

        //populates the selectedFields array depended on what is choice the combobox is on
        int fieldType = -1;
        for (int s = 0; s < numbLine; s++) {
            if (field.equals("First Name")) {
                fieldType = 0;
            } else if (field.equals("Last Name")) {
                fieldType = 1;
            } else if (field.equals("Email")) {
                fieldType = 2;
            } else if (field.equals("Phone")) {
                fieldType = 3;
            } else {
                fieldType = 4;
            }
        }

        for (int i = 0; i < numbLine; i++) {
            //new loop starts at zero, increments positively by one and ends when the loop integer is one less than contactCounter
            for (int j = i + 1; j < numbLine; j++) {

                //if the elements compared are not in alphabetical order in terms of A to Z
                int compared = contacts.get(map.get(i)).getField(fieldType).compareTo(contacts.get(map.get(j)).getField(fieldType));
                if (compared > 0 && type == 1) {
                    Collections.swap(map, i, j);
                }

                //if the elements compared are not in alphabetical order in terms of Z to A
                else if (compared < 0 && type == 0) {
                    Collections.swap(map, i, j);
                }
            }
        }

        for (int e = 0; e < numbLine; e++) {
            String[] contactInfo = contacts.get(map.get(e)).asArray();
            for (int f = 0; f < headerCount; f++) {
                fields.get(e * headerCount + f).setText(contactInfo[f]);
            }
        }

    }

    /**********
     *name: deleteSelectedContacts
     *description: deleted rows that have been selected
     *input/output:no input, no output (void)
     ***************************/
    public void deleteSelectedContacts() {
        ArrayList<Integer> rowsToDelete = new ArrayList<>(content.rows);
        rowsToDelete.sort(Collections.reverseOrder());

        for (int row : rowsToDelete) {
            checkboxes.get(row).doClick();
            map.remove(row);
            side.remove(lineNumbers.remove(map.size()));
            content.remove(checkboxes.remove(map.size()));

            for (int j = 0; j < headerCount; j++) {
                content.remove(fields.remove(fields.size() - 1));
            }
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < headerCount; j++) {
                fields.get(i * headerCount + j).setText(contacts.get(map.get(i)).getField(j));
            }
        }
        side.repaint();
        content.repaint();
        System.out.println(lineNumbers.size());
    }

    public void edit() {
        if (editing) {
            for (JTextField field : fields) {
                field.setEditable(false);
                field.setForeground(defaultFieldBackground);
                editing = false;
            }
        } else {
            for (JTextField field : fields) {
                field.setEditable(true);
                field.setForeground(fieldTextEditColor);
                editing = true;
            }
        }
    }

    public void setBackground2(Color bg) {
        backgroundColor = bg;
        super.setBackground(backgroundColor);
        content.setBackground(backgroundColor);
        side.setBackground(backgroundColor);
        top.setBackground(backgroundColor);
    }

    public void setLabelColour(Color c) {
        labelColor = c;
        lineNumbers.forEach((label) -> {
            label.setForeground(labelColor);
        });
    }

    public void setFieldBackground(Color c) {
        textFieldBackgroundColor = c;
        fields.forEach((field) -> field.setBackground(textFieldBackgroundColor));
    }

    public void setFieldTextColor(Color c) {
        defaultFieldBackground = c;
        fields.forEach((field) -> field.setForeground(defaultFieldBackground));
    }

    public void setHighlightColor(Color c) {
        content.setHighlighterColor(c);
        side.setHighlighterColor(c);
    }

    public void setHighlightLabelColor(Color c) {
        highlightLabelColor = c;
        side.rows.forEach((i) -> lineNumbers.get(i).setForeground(highlightLabelColor));
    }

    public void setCheckboxSelectedIcon(ImageIcon icon) {
        checkboxSelected = icon;
        checkboxes.forEach((checkbox) -> checkbox.setSelectedIcon(icon));
    }

    public void setCheckboxDeselectedIcon(ImageIcon icon) {
        checkboxDeselected = icon;
        checkboxes.forEach((checkbox) -> checkbox.setIcon(icon));
    }

    public void setFieldEditTextColor(Color c) {
        fieldTextEditColor = c;
        if (editing) {
            fields.forEach(field -> field.setForeground(fieldTextEditColor));
        }
    }
}