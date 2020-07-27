/**
 * @(#)MedFile.java
 *
 *
 * @author 
 * @version 1.00 2019/7/30
 */
import java.util.*;
import java.io.*;
//import java.lang;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class connection{
	private static HashMap<String, String> map; 
	private int nowords = 0;
	private String status = "Unknown";
	public connection(){
		load();
	}
	
	public static void load(){
		try{
			File file = new File("C://MedicalDictionary//words.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			map = new HashMap<>(); 
			String line = "";
			while((line = reader.readLine()) != null){
				String[] pair = line.split("  ");
				//System.out.print(pair.length);
				map.put(pair[0], pair[1]);
				//System.out.println("Done");	
			}
			reader.close();
			
			
		}catch(Exception e){
			System.out.print("Cannot open file for reading: " + e);
		}		
		
	}
	
	
	public String[] listWords(){	
		 String[] words = map.keySet().toArray(new String[map.keySet().size()]);
		 return words;
	}
	
	public String search(String word){
		String meaning = "";
		if(map.containsKey(word)){
			meaning = map.get(word);
			
		}	
			return meaning;
	}
	
}


class Interface extends JFrame{
	private JFrame x;
	private JButton s;
	private JTextField b;
	private JLabel d;
	private JLabel w;
	public JLabel labels[] = new JLabel[2];
	private connection conn; 
	public Interface(){
		super("Medical Dictionary");
		setLayout(new FlowLayout());
		
		handler thehandler = new handler();
		
		b = new JTextField("", 10);
		add(b);
		b.addActionListener(thehandler);
		
		s = new JButton("Search");
		add(s);
		s.addActionListener(thehandler);
		
	}
	
	private class handler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == s){
				
				connection conn = new connection();
				String[] words = conn.listWords(); 
		 
				for(int i=0; i<words.length; i++){
					labels[i] = new JLabel(words[i]);
					add(labels[i]);
				}
							
				w = new JLabel("");
				add(w);
				d = new JLabel("");
				add(d);
				String word = b.getText();
				String meaning = conn.search(word);
				if(meaning != ""){	
					w.setText(word);
					d.setText(meaning);
					//System.out.println(word);
					//System.out.println(meaning);
					
				}else{
					d.setText("Word not found");
					//System.out.println("Word not found");
				}				
				
			}
		}
	}
	
	
	
}