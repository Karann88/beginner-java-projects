package MorseCode;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        // invoke later insures that the GUI is created and updated in a thread-safe mcanner
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MorseCodeTranslatorGUI().setVisible(true);
            }
            
        });
    }
}
