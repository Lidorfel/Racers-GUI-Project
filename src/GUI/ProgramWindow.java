package GUI;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import utilities.EnumContainer.*;
import utilities.Fate;

/**
 * Main Frame of the GUI
 */
public class ProgramWindow extends JFrame implements ActionListener {
    private final int DEFAULT_SCREEN_HEIGHT=737;
    private final int DEFAULT_SCREEN_WIDTH=1054;
    private final int widthMargin=54;
    private final int heightMargin=37;
    private JPanel mainPanel,propertiesPanel,arenaPanel,racerPanel,startPanel;
    private ArenaPanel arenaBackground;
    private JComboBox<String> arenaChoose,racerChoose,colorChoose;
    private JTextField arenaLength,maxRacers,racerName,racerMaxSpeed,racerAcceleration;
    private JButton buildArena,addRacer,startRace,showInfo;
    private final String [] arenaTypes=new String[] {"AerialArena","NavalArena","LandArena"};
    private final String [] racersTypes=new String[] {"Airplane","Helicopter","RowBoat","SpeedBoat","Car","Bicycle","Horse"};
    private ArrayList<String> colors;
    private final String[] colorsTypes=new String[]{"BLACK","BLUE","GREEN","RED","YELLOW"};
    private final COLOR[] enumColorChoice= new COLOR[]{COLOR.BLACK,COLOR.BLUE,COLOR.GREEN,COLOR.RED,COLOR.YELLOW};
    private HashMap<String, BufferedImage[]> images;
    private final String IMAGES_PATH="src\\icons\\";
    private final String IMAGES_SUFIX=".png";
    private Arena arena;
    private HashMap<String,String> racerTypeConv;
    private final String racerConvStart="game.racers";
    private int[] gaps=new int[]{320,200,130,100,90,80,75,70,66,63,59,55,50,45,40,40,35,35,35,32};
    private resultTable DataTable;


    /**
     * default constructor of the Frame, setting al the properties of the frame and some help attributes
     */
    public ProgramWindow(){
        super("Race");
        //
        racerTypeConv=new HashMap<>();
        racerTypeConv.put("Airplane",".air.Airplane");
        racerTypeConv.put("Helicopter",".air.Helicopter");
        racerTypeConv.put("Car",".land.Car");
        racerTypeConv.put("Bicycle",".land.Bicycle");
        racerTypeConv.put("Horse",".land.Horse");
        racerTypeConv.put("RowBoat",".naval.RowBoat");
        racerTypeConv.put("SpeedBoat",".naval.SpeedBoat");

        arena=null;
        colors=new ArrayList<>();
        for(int i=0;i<5;i++){
            colors.add(colorsTypes[i]);
        }
        //main panel
        mainPanel=new JPanel(new BorderLayout());
        arenaBackground = new ArenaPanel();
        arenaBackground.setLayout(null);
        setSize(DEFAULT_SCREEN_WIDTH+200,DEFAULT_SCREEN_HEIGHT);
        arenaBackground.setPreferredSize(new Dimension(DEFAULT_SCREEN_WIDTH,DEFAULT_SCREEN_HEIGHT));
        //side panel
        propertiesPanel = new JPanel(new GridBagLayout());
        propertiesPanel.setPreferredSize(new Dimension(200,700));
        propertiesPanel.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.blue));

        //arena side panel
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.weighty = 0.3;
        gbc1.fill = GridBagConstraints.BOTH;
        arenaPanel=new JPanel(new GridLayout(4,1));
        JPanel p1=new JPanel(new GridLayout(2,1));
        JPanel p2=new JPanel(new GridLayout(2,1));
        JPanel p3=new JPanel(new GridLayout(2,1));
        JLabel arenaLabel = new JLabel("Choose arena : ");
        JLabel arenaLengthLabel = new JLabel("Arena length : ");
        JLabel maxRacersLabel = new JLabel("Max racers number : ");
        buildArena=new JButton("Build Arena");
        buildArena.addActionListener(this);
        arenaLength=new JTextField("1000");
        maxRacers=new JTextField("8");
        arenaChoose=new JComboBox<>(arenaTypes);
        arenaChoose.addActionListener(this);
        p1.add(arenaLabel);
        p1.add(arenaChoose);
        p1.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        p2.add(arenaLengthLabel);
        p2.add(arenaLength);
        p2.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        p3.add(maxRacersLabel);
        p3.add(maxRacers);
        p3.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        buildArena.setSize(20,20);
        arenaPanel.add(p1);
        arenaPanel.add(p2);
        arenaPanel.add(p3);
        arenaPanel.add(buildArena);
        arenaPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.blue));
        propertiesPanel.add(arenaPanel,gbc1);
        // racer side panel
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.weighty = 0.5;
        gbc2.fill = GridBagConstraints.BOTH;
        racerPanel=new JPanel(new GridLayout(6,1));
        JPanel p4=new JPanel(new GridLayout(2,1));
        JLabel racerTypeLabel=new JLabel("Choose racer : ");
        racerChoose=new JComboBox<>(racersTypes);
        racerChoose.addActionListener(this);
        p4.add(racerTypeLabel);
        p4.add(racerChoose);
        p4.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        JPanel p5=new JPanel(new GridLayout(2,1));
        JLabel colorLabel=new JLabel("Choose color : ");
        colorChoose=new JComboBox<>(colorsTypes);
        colorChoose.addActionListener(this);
        p5.add(colorLabel);
        p5.add(colorChoose);
        p5.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        JPanel p6=new JPanel(new GridLayout(2,1));
        JLabel racerNameLabel=new JLabel("Racer name : ");
        racerName=new JTextField();
        p6.add(racerNameLabel);
        p6.add(racerName);
        p6.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        JPanel p7=new JPanel(new GridLayout(2,1));
        JLabel maxSpeedLabel=new JLabel("Max speed : ");
        racerMaxSpeed=new JTextField();
        p7.add(maxSpeedLabel);
        p7.add(racerMaxSpeed);
        p7.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        JPanel p8=new JPanel(new GridLayout(2,1));
        JLabel accelerationLabel=new JLabel("Acceleration : ");
        racerAcceleration=new JTextField();
        p8.add(accelerationLabel);
        p8.add(racerAcceleration);
        p8.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        addRacer=new JButton("Add Racer");
        addRacer.addActionListener(this);
        racerPanel.add(p4);
        racerPanel.add(p5);
        racerPanel.add(p6);
        racerPanel.add(p7);
        racerPanel.add(p8);
        racerPanel.add(addRacer);
        racerPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.blue));
        propertiesPanel.add(racerPanel,gbc2);

        // start side panel
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.weighty = 0.2;
        gbc3.weightx = 1.0;
        gbc3.fill = GridBagConstraints.BOTH;
        GridLayout startLayout=new GridLayout(2,1);
        startLayout.setVgap(10);
        startPanel=new JPanel(startLayout);
        startRace=new JButton("Start race");
        startRace.addActionListener(this);
        showInfo=new JButton("Show info");
        showInfo.addActionListener(this);
        startPanel.add(startRace);
        startPanel.add(showInfo);
        startPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        propertiesPanel.add(startPanel,gbc3);
        mainPanel.add(arenaBackground,BorderLayout.CENTER);
        mainPanel.add(propertiesPanel,BorderLayout.EAST);
        getContentPane().add(mainPanel);
        loadImages();
        DataTable=new resultTable();

        setVisible(true);
    }

    /**
     * function to load all the images into a HashMap inorder to access it easily
     */
    private void loadImages() {
        images=new HashMap<>();
        BufferedImage[] horses=new BufferedImage[5];
        BufferedImage[] cars=new BufferedImage[5];
        BufferedImage[] bicycles=new BufferedImage[5];
        BufferedImage[] airplanes=new BufferedImage[5];
        BufferedImage[] helicopters=new BufferedImage[5];
        BufferedImage[] rowboats=new BufferedImage[5];
        BufferedImage[] speedboats=new BufferedImage[5];
        BufferedImage[] arenas=new BufferedImage[3];
        for (int i=1;i<=3;i++){
            String imageName = IMAGES_PATH +"Arena" +i + ".jpg";
            try {
                arenas[i-1] = ImageIO.read(new File(imageName));
            }
            catch (IOException e) {
                System.out.println(imageName);
            }
        }
        for (String racer : racersTypes){
            for(int i=0;i<5;i++){
                String imageName = IMAGES_PATH +racer +(i+1) + IMAGES_SUFIX;

                try {
                    switch (racer){
                        case "Airplane":
                            airplanes[i] = ImageIO.read(new File(imageName));
                            break;
                        case "Helicopter":
                            helicopters[i] = ImageIO.read(new File(imageName));
                            break;
                        case "RowBoat":
                            rowboats[i] = ImageIO.read(new File(imageName));
                            break;
                        case "SpeedBoat":
                            speedboats[i] = ImageIO.read(new File(imageName));
                            break;
                        case "Horse":
                            horses[i] = ImageIO.read(new File(imageName));
                            break;
                        case "Car":
                            cars[i] = ImageIO.read(new File(imageName));
                            break;
                        case "Bicycle":
                            bicycles[i] = ImageIO.read(new File(imageName));
                            break;
                    }
                }
                catch (IOException e) {
                    System.out.println(imageName);
                }
            }
        }
        images.put("Arena",arenas);
        images.put("Airplane",airplanes);
        images.put("Helicopter",helicopters);
        images.put("RowBoat",rowboats);
        images.put("SpeedBoat",speedboats);
        images.put("Horse",horses);
        images.put("Car",cars);
        images.put("Bicycle",bicycles);
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        RaceBuilder builder=RaceBuilder.getInstance();
        if (e.getSource().equals(buildArena)){
            String arenaType="game.arenas";
            boolean flag=true;
            int length=1000;
            int maxR=8;
            String choice=arenaChoose.getItemAt(arenaChoose.getSelectedIndex());
            try {
                length = Integer.parseInt(arenaLength.getText());
                maxR = Integer.parseInt(maxRacers.getText());
                if (maxR>20 || maxR<1 || length<100 || length>3000) {
                    JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
                    flag=false;
                }
            }
            catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(this,"Invalid input values! Please try again.");
                flag=false;

            }
            if (flag) {
                switch (choice) {
                    case "AerialArena":
                        arenaBackground.setImage(images.get("Arena")[0]);
                        arenaType+=".air.AerialArena";
                        break;
                    case "NavalArena":
                        arenaBackground.setImage(images.get("Arena")[1]);
                        arenaType+=".naval.NavalArena";
                        break;
                    case "LandArena":
                        arenaBackground.setImage(images.get("Arena")[2]);
                        arenaType+=".land.LandArena";
                        break;
                }
                try {
                    if(arena!=null){
                        for(Racer r : arena.getActiveRacers()){
                            r.getRacerPanel().removeAll();
                            r.getRacerPanel().repaint();
                        }
                        for(Racer r:arena.getCompletedRacers()){
                            r.getRacerPanel().removeAll();
                            r.getRacerPanel().repaint();
                        }
                    }
                    arena = builder.buildArena(arenaType, length, maxR);
                    arena.setMinYGap(gaps[maxR-1]);
                    arenaBackground.setPreferredSize(new Dimension(length+widthMargin,DEFAULT_SCREEN_HEIGHT));
                    setSize(length+widthMargin+200,DEFAULT_SCREEN_HEIGHT);
                    setVisible(true);
                }
                catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                       | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1){
                    System.out.println("Unable to build arena!");
                    arena = new AerialArena();
                }
                arena.setTable(DataTable);
                arena.setArenaPanel(arenaBackground);
                DataTable.resetTable();
                racerName.setText("");
                racerMaxSpeed.setText("");
                racerAcceleration.setText("");
            }

        }
        else if (e.getSource().equals(addRacer)){
            boolean flag=true;
            int type=-1;
            if(arena==null) {
                JOptionPane.showMessageDialog(this,"Please build arena first!");
                flag=false;
            }
            else if(arena.isRaceInProgress()){
                JOptionPane.showMessageDialog(this,"Race is in progress");
                flag=false;
                return;
            }
            else {
                int racerCo=colorChoose.getSelectedIndex();
                COLOR c=enumColorChoice[racerCo];
                Racer r=null;
                String name=racerName.getText();
                if (Objects.equals(name, "")){
                    JOptionPane.showMessageDialog(this,"Invalid input values! Please try again.");
                    flag=false;
                }
                else {
                    double maxS = -1;
                    double acc = -1;
                    try {
                        maxS = Double.parseDouble(racerMaxSpeed.getText());
                        acc = Double.parseDouble(racerAcceleration.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
                        flag = false;
                    }
                    if (flag) {
                        try {
                            String racerCh = racerChoose.getItemAt(racerChoose.getSelectedIndex());
                            if(racerCh.equals("Airplane")||racerCh.equals("Car")||racerCh.equals("Bicycle")){
                                r=builder.buildWheeledRacer((racerConvStart+racerTypeConv.get(racerCh)),name, maxS, acc, c, 3);

                            }
                            else{
                                r=builder.buildRacer((racerConvStart+racerTypeConv.get(racerCh)),name, maxS, acc, c);
                            }
                            JPanel racPanel=new JPanel(new BorderLayout());
                            JLabel racPicture=new JLabel();
                            arena.addRacer(r);
                            Image img=images.get(racerCh)[racerCo].getScaledInstance(40,40,Image.SCALE_DEFAULT);
                            racPicture.setIcon(new ImageIcon(img));
                            racPanel.add(racPicture);
                            racPanel.setBackground(new Color(1f,0f,0f,0f));
                            racPanel.setVisible(true);
                            r.setRacerPanel(racPanel);
                            arena.initRace();
                            DataTable.addTask(r);
                            arenaBackground.add(racPanel);
                            arenaBackground.setComponentZOrder(racPanel,0);
                            racPanel.revalidate();
                            racPanel.repaint();
                            System.out.println("racer added successfully "+arena.getNumPlayers());
                        }
                        catch (RacerLimitException e1){
                            JOptionPane.showMessageDialog(this, "Reached max racers!");

                        }
                        catch (RacerTypeException e1){
                            JOptionPane.showMessageDialog(this, "Racer type is not valid to arena.");
                        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException |
                                 NoSuchMethodException | IllegalAccessException ex) {
                            JOptionPane.showMessageDialog(this, "Unexpected Error happened while building Racer.");
                        }
                        racerName.setText("");
                        racerMaxSpeed.setText("");
                        racerAcceleration.setText("");
                    }
                }
            }
        }
        else if(e.getSource().equals(startRace)){
            if(arena.isRaceInProgress()){
                if(arena.getActiveRacers().size()>0) {
                    JOptionPane.showMessageDialog(this, "Race is in progress");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Race is already ended");
                }
                return;
            }
            else if (arena!=null&&arena.hasActiveRacers()){
                arena.startRace();
            }
            else{
                JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
            }
        }
        else if (e.getSource().equals(showInfo)) {
            if (arena!=null){
                DataTable.showMe();
            }
            else{
                JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
            }
        }

        repaint();
    }

    /**
     * @param args main function that runs the system
     */
    public static void main(String[] args){
        Fate.setSeed(477734503);
        JFrame mainWindow=new ProgramWindow();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }

}
