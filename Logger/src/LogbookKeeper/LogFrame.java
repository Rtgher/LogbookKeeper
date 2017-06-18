package LogbookKeeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ray-Ray on 18/06/2017.
 * The main frame of the program.
 */
public class LogFrame extends JFrame
{
    /** Main Panel. */
    public final JPanel mainPanel = new JPanel();
    /** Buttons Panel. */
    public final JPanel buttons = new JPanel();
    /** Text area for text content. */
    public final JTextArea text = new JTextArea(5,50);
    /** The message area where notices appear. */
    public final JTextField notice = new JTextField("Notices and messages will appear here.",50);
    /** Type of the LogEntry. */
    public final JComboBox<LogType> typeBox= new JComboBox(LogType.values());
    /** The button to add a new Entry. */
    public final Button logEntryButton;
    /** Check to see if the frame is running. */
    public boolean isRunning;

    /**
     * Create a new LogFrame.
     */
    public LogFrame ()
    {
        //FRAME SETUP
        super("Logbook Keeper");
        isRunning = true;
        this.setBackground(Color.DARK_GRAY);
        this.setSize(new Dimension(840, 620));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //LAYOUT SETUP
        LayoutManager layoutManager = new BorderLayout();
        this.setLayout(layoutManager);

        //TEXTAREA SETUP
        text.setForeground(Color.BLACK);
        text.setLineWrap(true);
        text.setBackground(Color.WHITE);
        text.setSelectedTextColor(Color.LIGHT_GRAY);
        text.setToolTipText("Enter the logbook entry here.");

        //TEXTFIELD SETUP
        notice.setToolTipText("System messages are displayed here.");
        notice.setEditable(false);

        //COMBOBOX SETUP
        typeBox.setToolTipText("Choose the type of the logbook entry.");

        //BUTTONS SETUP
        logEntryButton = new Button("ADD ENTRY");
        Button exitButton = new Button("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                System.exit(0);
            }
        });

        //PANEL SETUP
        mainPanel.setLayout(new BorderLayout(10, 5));
        mainPanel.add(typeBox, BorderLayout.EAST);
        mainPanel.add(text, BorderLayout.WEST);
        mainPanel.add(notice, BorderLayout.SOUTH);
        buttons.setLayout(new FlowLayout());
        buttons.add(logEntryButton);
        buttons.add(exitButton);

        //ADD TO FRAME
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setFocusable(true);
    }

    /** Creates a new LogEntry via the input in the boxes and returns it. */
    public LogEntry getLogEntry(){return new LogEntry(text.getText(),(LogType)typeBox.getSelectedItem());}


}
