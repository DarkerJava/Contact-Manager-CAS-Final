//imports required packages
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicMenuItemUI;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class Main extends javax.swing.JFrame {

	 	private static final int animDurationMillis = 300;
	    private static final int frameCount = 8;

	    private static class Fader {
	        private final Component component;

	        private final Color normalBackground;
	        private final float[] normalRGBA;
	        private final float[] targetRGBA;
	        private final float[] rgba = new float[4];

	        private final Timer timer = new Timer(animDurationMillis / frameCount,
	            e -> updateBackground());

	        private int frameNumber;

	        Fader(Component component,
	              Color targetBackground,
	              Color normalBackground) {

	            this.component = Objects.requireNonNull(component);
	            this.normalBackground = normalBackground;
	            this.normalRGBA = normalBackground.getComponents(null);
	            this.targetRGBA = targetBackground.getComponents(null);
	        }

	        private void updateBackground() {
	            if (++frameNumber > frameCount) {
	                timer.stop();
	                return;
	            }

	            for (int i = rgba.length - 1; i >= 0; i--) {
	                float normal = normalRGBA[i];
	                float target = targetRGBA[i];
	                rgba[i] =
	                    normal + (target - normal) * frameNumber / frameCount;
	            }

	            component.setBackground(
	                new Color(rgba[0], rgba[1], rgba[2], rgba[3]));
	        }

	        void start() {
	            frameNumber = 0;
	            timer.restart();
	            component.setBackground(normalBackground);
	        }

	        void stop() {
	            timer.stop();
	            component.setBackground(normalBackground);
	        }
	    }

		private static Container button;

	    static void addFadeOnHover(Component component,
	                               Color targetBackground,
	                               Color normalBackground) {

	        Fader entryFader =
	            new Fader(component, targetBackground, normalBackground);
	        Fader exitFader =
	            new Fader(component, normalBackground, targetBackground);

	        component.addMouseListener(new MouseAdapter() {

	            public void mouseEntered(MouseEvent event) {
	                exitFader.stop();
	                entryFader.start();
	            }


	            public void mouseExited(MouseEvent event) {
	                entryFader.stop();
	                exitFader.start();
	            }
	            public void mousePressed(MouseEvent events) {

	                component.setBackground(new Color(240,240,180));

	            }
	            public void mouseReleased(MouseEvent s) {

	            	exitFader.stop();

	            }

	        });
	    }

    public Main() {
        initComponents();

    }

    static Scanner reader = new Scanner(System.in);

    static int currentOption = -1;

    /**********
     *name: initComponents
     *description: attaches the components to the gui and sets thy style of the application
     *input/output:no input, no output (void)
     ***************************/
    private void initComponents() {


        jPanel5 = new JPanel();
        jPopupMenu1 = new JPopupMenu();
        jPopupMenu2 = new JPopupMenu();
        jPopupMenu3 = new JPopupMenu();
        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        buttonGroup3 = new ButtonGroup();
        buttonGroup4 = new ButtonGroup();
        buttonGroup5 = new ButtonGroup();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jPanel3 = new JPanel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jLabel20 = new JLabel();
        jLabel8 = new JLabel();
        jButton1 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton11 = new JButton();
        jButton9 = new JButton();
        jPanel1 = new JPanel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel10 = new JLabel();
        jLabel12 = new JLabel();
        jLabel5 = new JLabel();

        jPanel8 = new JPanel();
        //jPanel7 = new javax.swing.JPanel();
        jButton10 = new JButton();
        jButton12 = new JButton();
        jButton2 = new JButton();
        jButton7 = new JButton();

        jLabel10 = new JLabel();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jLabel11 = new JLabel();

        jTextField2 = new JComboBox(Contact.CONTACT_FIELDS);
        dark = false;

        Color foreground = new Color(255,232,88); //pressed
        Color background = new Color(251,222,68); //Start color
        Color highlight = new Color(221,192,38); //user on button

        jButton1.setBackground(new Color(251,222,68));
        jButton2.setBackground(new Color(251,222,68));
        jButton3.setBackground(new Color(251,222,68));
        jButton4.setBackground(new Color(251,222,68));
        jButton5.setBackground(new Color(251,222,68));
        jButton6.setBackground(new Color(251,222,68));
        jButton7.setBackground(new Color(251,222,68));
        jButton9.setBackground(new Color(251,222,68));
        jButton10.setBackground(new Color(251,222,68));
        jButton11.setBackground(new Color(251,222,68));

        BasicMenuItemUI ui = new BasicMenuItemUI() {
            {
                selectionForeground = foreground;
                selectionBackground = background;
            }


            public void paintBackground(Graphics g,
                                        JMenuItem item,
                                        Color background) {
                super.paintBackground(g, item, item.getBackground());
            }
        };

            AbstractButton b = (AbstractButton) jButton1;
            b.setContentAreaFilled(false);
            b.setOpaque(true);

            AbstractButton c = (AbstractButton) jButton2;
            c.setContentAreaFilled(false);
            c.setOpaque(true);

            AbstractButton d = (AbstractButton) jButton3;
            d.setContentAreaFilled(false);
            d.setOpaque(true);

            AbstractButton e = (AbstractButton) jButton4;
            e.setContentAreaFilled(false);
            e.setOpaque(true);

            AbstractButton f = (AbstractButton) jButton5;
            f.setContentAreaFilled(false);
            f.setOpaque(true);

            AbstractButton g = (AbstractButton) jButton6;
            g.setContentAreaFilled(false);
            g.setOpaque(true);

            AbstractButton h = (AbstractButton) jButton7;
            h.setContentAreaFilled(false);
            h.setOpaque(true);

            AbstractButton i = (AbstractButton) jButton9;
            i.setContentAreaFilled(false);
            i.setOpaque(true);

            AbstractButton j = (AbstractButton) jButton9;
            j.setContentAreaFilled(false);
            j.setOpaque(true);

            AbstractButton k = (AbstractButton) jButton10;
            k.setContentAreaFilled(false);
            k.setOpaque(true);

            AbstractButton l = (AbstractButton) jButton11;
            l.setContentAreaFilled(false);
            l.setOpaque(true);

            addFadeOnHover(jButton1, highlight, background);
            addFadeOnHover(jButton2, highlight, background);
            addFadeOnHover(jButton3, highlight, background);
            addFadeOnHover(jButton4, highlight, background);
            addFadeOnHover(jButton5, highlight, background);
            addFadeOnHover(jButton6, highlight, background);
            addFadeOnHover(jButton7, highlight, background);
            addFadeOnHover(jButton9, highlight, background);
            addFadeOnHover(jButton10, highlight, background);
            addFadeOnHover(jButton11, highlight, background);

        //Everything below was generated by NetBean's Form maker

        //background and foregrounds are set
        //jTextField2.setBackground(new Color(211,182,28));

       //jTextField2.setUI(ColorArrowUI.createUI(jTextField2));
        jTextField2.setForeground(Color.BLACK);
        jTextField2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //jTextField2.setBackground(new Color(211,182,28));
        jTextField2.setBackground(Color.YELLOW);



        jPanel5.setBackground(new Color(102, 102, 102));
        jPanel2.setBackground(new Color(40,51,74)); //main panel

        jLabel1.setForeground(new Color(255, 255, 255));
        jPanel3.setBackground(new Color(226, 226, 226));

       // jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        setBackground(new Color(255, 255, 0));
        jTextField1.setBackground(new Color(228, 228, 228));
        jTextField1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel2.requestFocusInWindow();
        jTextField1.setEditable(false);
        jTextField1.getCaret().setVisible(false);
        jTextField1.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent a){
            	jTextField1.setText("");
            	 jTextField1.setEditable(true);
            	jTextField1.getCaret().setVisible(true);
            }



        });
        jPanel1.setBackground(new Color(251,222,68)); //headers
        jPanel8.setBackground(new Color(251,222,68));

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(GroupLayout.Alignment.CENTER)

        );
        jPanel5Layout.setVerticalGroup(

                jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)

        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new Font("Lucida Grande", 1, 20));

        jLabel1.setText(" MY CONTACTS");
        jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/logo.png")));
        setUndecorated(false);

        //jTextField1 customized
        jTextField1.setFont(new Font("Lucida Grande", 0, 18));
        jTextField1.setText("Search");
        jLabel2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/search.png"))); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
        jTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        //jPanel3Layout customized
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
                               // .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MIN_VALUE))
        );

        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 2, Short.MAX_VALUE))
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        //jPanel1 customized
        jButton1.setFont(new Font("Lucida Grande", 0, 15));

        jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/add.png")));

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        //jButton3 customized
        jButton3.setFont(new Font("Lucida Grande", 0, 15));
        jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/edit.png")));

        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });


        //jButton4 customized
        jButton4.setFont(new Font("Lucida Grande", 0, 15));
        jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/delete.png")));

        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });


        //jButton9 customized
        jButton9.setFont(new Font("Lucida Grande", 0, 13));
        jButton9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/save.png")));


        jButton9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        //jButton11 customized
        jButton11.setFont(new Font("Lucida Grande", 0, 15));
        jButton11.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/invertColour.png")));
        jButton11.addActionListener(this::jButton11ActionPerformed);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setLayout(new AbsoluteLayout());

        //jLabel3 customized
        jLabel3.setFont(new Font("Lucida Grande", 0, 15));
        jLabel3.setText("FIRST NAME");
        jLabel3.setAlignmentY(0.0F);

        jPanel1.add(jLabel3, new AbsoluteConstraints(80, 7, -1, -1));
        jPanel1.add(jLabel4, new AbsoluteConstraints(61, 7, -1, -1));

        //jLabel6 customized
        jLabel6.setFont(new Font("Lucida Grande", 0, 15));
        jLabel6.setText("LAST NAME");
        jLabel6.setAlignmentY(0.0F);
        jPanel1.add(jLabel6, new AbsoluteConstraints(250, 7, -1, -1));

        //jLabel7 customized
        jLabel7.setFont(new Font("Lucida Grande", 0, 15));
        jLabel7.setText("PHONE");
        jLabel7.setAlignmentY(0.0F);
        jPanel1.add(jLabel7, new AbsoluteConstraints(580, 7, -1, -1));

        jLabel12.setFont(new Font("Lucida Grande", 0, 15));
        jLabel12.setText("ADDRESS");
        jLabel12.setAlignmentY(0.0F);
        jPanel1.add(jLabel12, new AbsoluteConstraints(740, 7, -1, -1));

        //jLabel5 customized
        jLabel5.setFont(new Font("Lucida Grande", 0, 15));
        jLabel5.setText("EMAIL");
        jLabel5.setVerticalAlignment(SwingConstants.BOTTOM);
        jLabel5.setAlignmentY(0.0F);
        jPanel1.add(jLabel5, new AbsoluteConstraints(430, 7, -1, -1));

        //jPanel8.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/excel.png")));

        jButton7.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/close.png")));

        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    File file = new File("allcontactsvf.csv");

                    //first check if Desktop is supported by Platform or not
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("Desktop is not supported");
                        return;
                    }

                    Desktop desktop = Desktop.getDesktop();

                    //let's try to open PDF file
                    file = new File("allcontactsvf.csv");
                    jButton2ActionPerformed(evt);
                    //System.out.println("file open");


                    if (file.exists()) {
                        desktop.open(file);
                    }
                } catch (IOException e) {
                    System.out.println("No default application supported. Install Excel or similar application.");
                }
            }
        });

        //listener for the quit button
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Program terminated!");
                //program is terminated
                System.exit(0);
            }
        });

        //Netbeans generated code

        jLabel10.setBorder(BorderFactory.createEmptyBorder(8, 0, 7, 7));
        jLabel11.setBorder(BorderFactory.createEmptyBorder(2, 6, 7, 7));


        jLabel20.setBorder(BorderFactory.createEmptyBorder(2, 0, 7, 7));

        //jButton7.setBorder(BorderFactory.createEmptyBorder(2, 0, 7, 7));


        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(GroupLayout.Alignment.CENTER)

                .addGroup(jPanel8Layout.createSequentialGroup()
                        //.addContainerGap(30, Short.MAX_VALUE)


                		.addGap(50, 50, 50)
                        .addComponent(jLabel10)
                        .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)

                        .addGap(30, 30, 30)

                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)



                        .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jButton12, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        );

        //Netbeans generated code
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

                                        .addComponent(jButton5)
                                        .addComponent(jButton6)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jTextField2)
                                        .addComponent(jButton1)
                                        .addComponent(jButton3)
                                        .addComponent(jButton4)
                                        .addComponent(jButton9)
                                        .addComponent(jLabel20)
                                        .addComponent(jButton2)
                                        .addComponent(jButton11)
                                        .addComponent(jButton7)
                                        .addComponent(jButton12)
                                ))
        );
        jPanel8.setMinimumSize(new Dimension(0,0));
        jLabel10.setText("SORT BY:");
        jLabel10.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        jButton5.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/A-Z.png")));
        jButton12.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/blank.png")));
        jButton5.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton4.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton6.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton7.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton10.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton11.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jButton9.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        jButton12.setContentAreaFilled(false);
        jButton5.setContentAreaFilled(false);
        //listener for the button
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        //listener for the button
        jButton6.setIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/Z-A.png")));


        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel11.setText("BY FIELD:");
        jLabel11.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        jLabel20.setText("   ");

        File file = new File(Paths.get("allcontactsvf.csv").toString());
        if(!file.exists()){
            try {
                file.createNewFile();
                PrintWriter writer = new PrintWriter(file);
                StringBuilder finalString = new StringBuilder();
                for (int inc = 0; inc < Contact.CONTACT_FIELDS.length - 1; inc++) {
                    finalString.append(Contact.CONTACT_FIELDS[inc]).append(",");
                }
                finalString.append(Contact.CONTACT_FIELDS[Contact.CONTACT_FIELDS.length - 1]).append("\n");
                writer.println();
                writer.close();
            } catch (IOException ioException){
                System.err.println("Unable to create save file.");
            }
        }

        Path path = Paths.get("allcontactsvf.csv");
        grid = new CSVGrid(path);
        grid.setPreferredSize(new Dimension(grid.getPreferredSize().width, 400));

        //scrollbar is customized
        grid.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        grid.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        grid.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        grid.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        grid.getVerticalScrollBar().setUnitIncrement(8);

        grid.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        //Netbeans generated code
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addGroup(jPanel2Layout.createSequentialGroup()


                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)


                                                .addComponent(jPanel8, 870, 870, 870)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel1)
                                                                .addGap(34, 34, 34)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)) //search bar
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 870, GroupLayout.PREFERRED_SIZE) //titles
                                                                        .addComponent(grid, GroupLayout.PREFERRED_SIZE, 870, GroupLayout.PREFERRED_SIZE)))) //that should work
                                                 )))
        );

        //Netbeans generated code
        jPanel2Layout.setVerticalGroup(

                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)

                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      //  .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(grid, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

                                .addContainerGap())
        );
        //Netbeans generated code
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 883, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(

                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        lightMode();
    }

    public static void main(String args[]) {
    	SplashScreen splash = new SplashScreen(500);

        // Normally, we'd call splash.showSplash() and get on
        // with the program. But, since this is only a test...
        splash.showSplash();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException  | IllegalAccessException | UnsupportedLookAndFeelException c) {
        	System.out.println("Look and Feel broken");
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }

    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private static javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton10;
    private static javax.swing.JButton jButton2;
    private static javax.swing.JButton jButton3;
    private static javax.swing.JButton jButton4;
    private static javax.swing.JButton jButton5;
    private static javax.swing.JButton jButton6;
    private static javax.swing.JButton jButton9;
    private static javax.swing.JButton jButton7;
    private static javax.swing.JButton jButton11;
    private static javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox jTextField2;

    private CSVGrid grid;

    private boolean dark;

    /**********
     *name: jTextField1ActionPerformed
     *description: takes in the user's input in the TextField and sends it to a method in the CSVGrid class
     *input/output: input is the event of enter occurring, no output (void)
     ***************************/

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        //new string searchterm is set to what has been entered in the searcg bar
        String searchTerm = jTextField1.getText();
        //sends the string to the method "searches" in CSVGrid
        grid.searches(searchTerm);

    }
    /**********
     *name: jButton1ActionPerformed
     *description: when the add button is pressed, this method calls other methods from the CSVGrid class to add extra fields
     *input/output: input is the event of the button press occuring, no output (void)
     ***************************/
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        //calls addContact method with a new contact as the input
        grid.addContact(new Contact());
        //new fancy scrollbar
        JScrollBar vertical = grid.getVerticalScrollBar();
        //scrolls to the lowest extreme of the grid
        //this allows the user to see the new fields
        vertical.setValue(vertical.getMaximum());
    }

    /**********
     *name: jButton4ActionPerformed
     *description: when the delete button is pressed, this method calls other methods from the CSVGrid class to delete a row or rows
     *input/output: input is the event of enter occuring, no output (void)
     ***************************/
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        //calls the method that deletes selected contacts
        grid.deleteSelectedContacts();
    }
    /**********
     *name: jButton9ActionPerformed
     *description: when the delete button is pressed, this method calls the save method in CSVGrid to save the contacts
     *input/output: input is the event of enter occuring, no output (void)
     ***************************/
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt)
    {
        //saves the grid by calling the method "save" in CSVGrid
        grid.save();
    }

    /**********
     *name: jButton6ActionPerformed
     *description: when the Z-A button is pressed, this method calls the sort method to sort the contacts based on what is selected on the combobox
     *input/output: input is the event of enter occuring, no output (void)
     ***************************/
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        //makes a new string and it is set to what the combox box is set to
        String selected = (String) jTextField2.getSelectedItem();
        //calls the sort method in CSVGrid and the methods input is the string selected and 0, because the user is sorting from Z-A
        grid.sort(selected, 0);
    }

    /**********
     *name: jButton5ActionPerformed
     *description: when the A-Z button is pressed, this method calls the sort method to sort the contacts based on what is selected on the combobox
     *input/output: input is the event of enter occuring, no output (void)
     ***************************/
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        //makes a new string and it is set to what the combo box is set to
        String selected = (String) jTextField2.getSelectedItem();
        //calls the sort method in CSVGrid and the methods input is the string selected and 1, because the user is sorting from A-Z
        grid.sort(selected, 1);
    }

    private void jButton11ActionPerformed(ActionEvent evt) {
        if (dark) {
            dark = false;
            lightMode();
        } else {
            dark = true;
            darkMode();
        }
    }

    public void darkMode(){
        jTextField2.setBackground(new Color(251, 222, 68));
        //jTextField2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        jTextField2.setForeground(Color.red);
        //jTextField2.setContentAreaFilled(false);
        jPanel5.setBackground(new Color(102, 102, 102));
        jPanel2.setBackground(new Color(45, 45, 45)); //main panel
        jPanel2.setForeground(new Color(102, 102, 102));
        jLabel1.setForeground(new Color(255, 255, 255));
        jPanel3.setBackground(new Color(226, 226, 226));
        jPanel3.setForeground(new Color(240, 240, 240));
        jPanel3.setForeground(new Color(240, 240, 240));
        // jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        setBackground(new Color(255, 255, 0));
        jTextField1.setBackground(new Color(228, 228, 228));

        jPanel1.setBackground(new Color(177, 91, 46)); //headers

        jPanel8.setBackground(new Color(177, 91, 46));

        grid.setBackground2(new Color(60, 63, 65));
        grid.setLabelColour(Color.decode("#a9b7c6"));
        grid.setFieldBackground(new Color(69, 73, 74));
        grid.setFieldTextColor(Color.decode("#a9b7c6"));
        grid.setHighlightColor(new Color(175, 109, 74, 255));
        grid.setHighlightLabelColor(Color.decode("#ffffff"));
        grid.setCheckboxSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/checked.png")));
        grid.setCheckboxDeselectedIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/unchecked.png")));
        grid.setFieldEditTextColor(new Color(0,255,0));
    }

    public void lightMode(){
        jTextField2.setBackground(new Color(251, 222, 68));
        //jTextField2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        jTextField2.setForeground(Color.red);
        //jTextField2.setContentAreaFilled(false);
        jPanel5.setBackground(new Color(102, 102, 102));
        jPanel2.setBackground(new Color(40, 51, 74)); //main panel
        jPanel2.setForeground(new Color(102, 102, 102));
        jLabel1.setForeground(new Color(255, 255, 255));
        jPanel3.setBackground(new Color(226, 226, 226));
        jPanel3.setForeground(new Color(240, 240, 240));
        // jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        setBackground(new Color(255, 255, 0));
        jTextField1.setBackground(new Color(228, 228, 228));

        jPanel1.setBackground(new Color(251, 222, 68)); //headers

        jPanel8.setBackground(new Color(251, 222, 68));
        grid.setBackground2(Color.white);

        grid.setBackground2(new Color(199, 199, 199));
        grid.setLabelColour(new Color(0,0,0));
        grid.setFieldBackground(new Color(255,255,255));
        grid.setFieldTextColor(new Color(0,0,0));
        grid.setHighlightColor(new Color(0,0,255));
        grid.setHighlightLabelColor(new Color(255,255,255));
        grid.setCheckboxSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/checked.png")));
        grid.setCheckboxDeselectedIcon(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/unchecked.png")));
        grid.setFieldEditTextColor(new Color(255,0,0));
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        grid.edit();
    }

    }
