package LogbookKeeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Ray-Ray on 18/06/2017.
 * Main Class to run the Logbook Keeper software.
 */
public class Main
{
    /** The frame */
    private static LogFrame frame;
    /** The File Printer */
    private static PrintWriter out;
    /** Path to file. */
    private static String path;

    public static void main(String[] args)
    {
        frame = new LogFrame();
        frame.notice.setText("Welcome {0}, setting up your logbook now.".replace("{0}", LoggerProperties.prop.getProperty("user")));
        frame.repaint();

        path = LoggerProperties.prop.getProperty("logbook.path");
        if (path != null && !path.equals(""))
        {
            frame.notice.setText("Found logbook path in: "+ path);
            frame.repaint();
        }
        else
        {
            frame.notice.setText("No path set. Searching for logbook in main directory.");
            frame.repaint();
            path = "logbook.log";
        }

        ActionListener logEntryListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToFile(frame.getLogEntry().toString());
                frame.notice.setText("Log entry added to file.");
                frame.text.setText("");
                frame.repaint();
            }
        };
        frame.logEntryButton.addActionListener(logEntryListener);
    }

    public static void writeToFile(String logLine)
    {
        try
        {
            FileWriter fw = new FileWriter(path, true);
            out = new PrintWriter(fw);
            out.println(logLine);
        }catch (IOException io)
        {
            frame.notice.setText("Caught an error opening/creating your logbook file. Searched for: " + path);
        }
        finally
        {
            if(out != null) out.close();
        }
    }


}
