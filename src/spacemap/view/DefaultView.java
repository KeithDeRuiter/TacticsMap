/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import spacemap.ViewListener;
import spacemap.model.track.HostilityLevel;
import spacemap.model.track.Position;
import spacemap.model.track.Track;
import spacemap.view.rendering.Coordinates;

/**
 * The default map view.
 * @author Keith
 */
public class DefaultView implements View {
    
    /** Listeners to notify about things happening in the view. */
    private final List<ViewListener> listeners;

    /** The main frame for the view. */
    private JFrame frame;
    
    /** The display for the map. */
    private MapDisplay map;

    /** List of IDs for all tracks created. */
    private List<UUID> trackIds;
    
    /** ComboBox to determine hostility of new tracks. */
    private JComboBox<HostilityLevel> hostilityComboBox;
    
    /** Text field for the name of new tracks. */
    private JTextField nameField;
    
    /** Text field for the number of randomly generated of new tracks. */
    private JTextField randomQuantityField;
    
    /** Cached array of hostility values for random generatoin. */
    private final HostilityLevel[] HOSTILITY_LEVELS = HostilityLevel.values();
    
    /** Random number generator. */
    private final Random rand;
    
    /**
     * Constructs a new instance of {@code View}.
     */
    public DefaultView() {
        listeners = new ArrayList<>();
        rand = new Random();
        trackIds = new ArrayList<>();
        initComponents();
    }
    
    private void initComponents() {
        frame = new JFrame("Tactics Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        
        //Map
        map = new MapDisplay();
        JComponent mapComponent = map.getComponent();
        mapComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("Mouse Released at " + e.getPoint());
                Position p = map.getPositionForScreenCoordinates(new Coordinates(e.getX(), e.getY()));
                HostilityLevel h = hostilityComboBox.getItemAt(hostilityComboBox.getSelectedIndex());
                String n = nameField.getText();
                notifyAllCreateTrack(p, h, n);
            }
        });
        frame.add(mapComponent, BorderLayout.CENTER);
        
        //Controls
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx++;
        nameField = new JTextField("Name", 15);
        controlPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        controlPanel.add(new JLabel("Hostility:"), gbc);
        
        gbc.gridx++;
        hostilityComboBox = new JComboBox<>(HostilityLevel.values());
        controlPanel.add(hostilityComboBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        controlPanel.add(new JLabel("Num Random:"), gbc);
        
        gbc.gridx++;
        randomQuantityField = new JTextField("50", 4);
        controlPanel.add(randomQuantityField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton randomGenerateButton = new JButton("Generate Random Tracks");
        randomGenerateButton.addActionListener((ActionEvent e) -> {
            int numRand = 0;
            try {
                numRand = Integer.valueOf(randomQuantityField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Cannot generate tracks, " + randomQuantityField.getText() + " is not a valid quantity");
                return;
            }
            
            for (int i = 0; i < numRand; i++) {
                float x = rand.nextFloat() * map.getComponent().getWidth();
                float y = rand.nextFloat() * map.getComponent().getHeight();
                Position p = new Position(x, y);
                HostilityLevel h = HOSTILITY_LEVELS[rand.nextInt(HOSTILITY_LEVELS.length)];
                
                notifyAllCreateTrack(p, h, "Track " + String.valueOf(i));
            }
        });
        controlPanel.add(randomGenerateButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JButton clearButton = new JButton("Clear All Tracks");
        clearButton.addActionListener((ActionEvent e) -> {
            trackIds.stream().forEach((id) -> {
                notifyAllDeleteTrack(id);
            });
        });
        controlPanel.add(clearButton, gbc);
        gbc.gridwidth = 1;
        
        frame.add(controlPanel, BorderLayout.EAST);
    }

    public void notifyAllCreateTrack(Position p, HostilityLevel hostility, String name) {
        listeners.stream().forEach((l) -> {
            l.createTrack(p, hostility, name);
        });
    }

    public void notifyAllDeleteTrack(UUID id) {
        listeners.stream().forEach((l) -> {
            l.deleteTrack(id);
        });
    }
    
    @Override
    public void showView() {
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void setDeclutterEnabled(boolean state) {
        map.setDeclutterEnabled(state);
    }

    @Override
    public void addOrUpdateTrackToView(Track track) {
        trackIds.add(track.getId());
        map.addRenderablesForTrack(track);
    }

    @Override
    public void removeTrackFromView(UUID id) {
        map.removeRenderablesForTrack(id);
    }
    
    @Override
    public void addViewListener(ViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeViewListener(ViewListener listener) {
        listeners.remove(listener);
    }
    
}
