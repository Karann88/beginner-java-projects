package ToDoListProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.awt.event.ActionListener;
import java.io.File;

public class ToDoListGui extends JFrame implements ActionListener {
    // taskPanel will act as the container for the taskComponentPanel
    // taskComponentPanel will store all of the taskComponents
    private JPanel taskPanel, taskComponentJPanel;

    public ToDoListGui() {
        super("To Do List Applications");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // banner text
        JLabel bannerLabel = new JLabel("To Do List");
        bannerLabel.setFont(createFont("resources/LEMONMILK-Light.otf", 36f));
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2,
                15,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height);

        // taskpanel
        taskPanel = new JPanel();

        // taskComponentPanel
        taskComponentJPanel.setLayout(new BoxLayout(taskComponentJPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentJPanel);

        // add scrolling to the task panel
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, CommonConstants.TASKPANEL_SIZE.height, CommonConstants.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // changing the speed of the scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        // add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(createFont("resources/LEMONMILK-Light.otf", 18f));
        addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88,
                CommonConstants.ADDTASK_BUTTON_SIZE.width, CommonConstants.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        // add to Frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resource, float size) {
        // get the font file path
        String filePath = getClass().getClassLoader().getResource(resource).getPath();

        // check to see if the path contains a folder with spaces in them
        if (filePath.contains("%20")) {
            filePath = getClass().getClassLoader().getResource(resource).getPath().replaceAll("%20", " ");
        }

        // create font
        try {
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add Task")) {
            // create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentJPanel);
            taskComponentJPanel.add(taskComponent);

            // make the previous task appear disabled
            if (taskComponentJPanel.getComponentCount() > 1) {
                TaskComponent previousTask = (TaskComponent) taskComponentJPanel.getComponent(
                        taskComponent.getComponentCount() - 2);
                previousTask.setBackground(null);
            }

            // make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
