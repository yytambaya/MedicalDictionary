/**
 * @(#)Main.java
 *
 *
 * @author 
 * @version 1.00 2019/7/30
 */
//import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class Main extends JFrame{
        
    /**
     * Creates a new instance of <code>Main</code>.
     */
    public Main() {
		super("Medical Dictionary");
       	setSize(560, 370);
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JTextField search = new JTextField();	
		JButton btnSearch = new JButton("Search");
		JTextArea meaning  = new JTextArea("Lorem Ipsup");
		meaning.setLineWrap(true);
		meaning.setEditable(false);
		
		JList words = new JList();
		words.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		connection conn = new connection();
		String[] wordsList = conn.listWords(); 
		words.setListData(wordsList);
		add(new JScrollPane(words));
		
		search.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e){
				//System.out.println(words.getSelectedIndex());
				String c = search.getText();
				String[] f_wordsList = new String[2500];
				for(int i=0; i<wordsList.length; i++){
					if(wordsList[i].toLowerCase().contains(c.toLowerCase())){
						f_wordsList[i] = wordsList[i];
					}
					//words.setListData(wordList);
				}
				words.setListData(f_wordsList);	
			}
		});
		
		words.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = words.getSelectedValuesList();
                    //System.out.println(selectedValuesList);
                    String word = String.join(", ", words.getSelectedValuesList());
                   	String defination = conn.search(word);
					if(defination != ""){	
						meaning.setText(defination);
					}else{
						meaning.setText("Word not found");
					}				
                    
                }
            }
        });
			
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1, 2, 2, 2));
		north.add(search);
		north.add(btnSearch);
	
		add(search);
		add(words);
		add(meaning);
		
		search.setBounds(5, 5, 535, 25);
		words.setBounds(5, 32, 220, 295);
		meaning.setBounds(230, 32, 310, 295);
		
	
        setVisible(true);
        //pack();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Interface w = new Interface();
        //w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //w.setSize(400, 300);
        //w.setResizable(false);
        //w.setVisible(true);
       	//connection conn = new connection();
       	//System.out.println(conn.search("Abba"));
       	//System.out.println(conn.search("Sani"));
       	
       	 new Main();
    }
}
