import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

public class FireTask extends JFrame implements ComponentListener, ActionListener, ItemListener {
    JButton buttonForeground;
    JButton buttonBackground;
    JToggleButton tbPlay;
    Viewer viewer;
    FireAnimation animation;

    public FireTask(){
        this.initClass();
        this.configureJFrame();
        this.addUIComponents();
        this.setVisible(true);
        this.pack();
    }

    public void playAnimation(){
        while (true) {
            if (this.tbPlay.isSelected()){
                this.viewer.paintBackground();
                this.viewer.paintForeground();
            }
            try {
                sleep(100);
            }catch (InterruptedException e){
                System.err.println(e);
            }
        }
    }

    private void addButtons(Container panel){
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;

        this.buttonForeground = new JButton("Mostrar Foreground");
        this.buttonForeground.addActionListener(this);
        panel.add(this.buttonForeground, c);

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;

        this.buttonBackground = new JButton("Mostrar Background");
        this.buttonBackground.addActionListener(this);
        panel.add(this.buttonBackground, c);

        c.gridy = 2;
        this.tbPlay = new JToggleButton("Play");
        this.tbPlay.addActionListener(this);
        panel.add(this.tbPlay, c);
    }

    private void addViewer(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 4;
        c.gridwidth = 1;

        this.viewer = new Viewer(512, 512, this.animation);
        panel.add(this.viewer, c);
    }

    private void addUIComponents() {
        Container panel;
        panel = this.getContentPane();
        this.addViewer(panel);
        this.addButtons(panel);
    }

    private void initClass() {
        this.animation = new FireAnimation(100, 100);
    }

    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.addComponentListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Mostrar Foreground":
                this.viewer.paintForeground();
                break;
            case "Mostrar Background":
                this.viewer.paintBackground();
                break;
            case "Play":
                this.viewer.paintBackground();
                break;
            default:
                System.err.println("Acción NO tratada: " + e);
        }
    }


    @Override
    public void componentHidden(ComponentEvent ce) {

    }


    @Override
    public void componentMoved(ComponentEvent ce) {

    }


    @Override
    public void componentResized(ComponentEvent ce) {

    }


    @Override
    public void componentShown(ComponentEvent ce) {

    }


    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        int estado = itemEvent.getStateChange();
        if (estado == ItemEvent.SELECTED) {
        } else {
        }
    }
}
