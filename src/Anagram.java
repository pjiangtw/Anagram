import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Anagram {
	HashMap<String,HashSet<String>> mdict = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagram anagram = new Anagram();
		try {
			anagram.loadDictionary("test/dict.txt");
			anagram.saveDictionary("test/preprocessed.txt");
			anagram.loadPreprocessedDictionary("test/preprocessed.txt");
			Set<String> result = anagram.query("abc");
			for(String s : result){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize with an empty dictionary.
	 */
	public Anagram(){
	}
	
	/**
	 * Initialize with a plain text dictionary.
	 * @param path the path to the dictionary file
	 * @throws IOException
	 */
	public Anagram(String path) throws IOException{
		this.loadDictionary(path);
	}
	
	/**
	 * If preprocessed == true, load from a preprocessed dictionary file. Otherwise, load from a plain text dictionary. 
	 * @param path the path to the dictionary file
	 * @param preprocessed indicating if the dictionary file is preprocessed or not 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Anagram(String path, boolean preprocessed) throws ClassNotFoundException, IOException{
		if(preprocessed){
			this.loadPreprocessedDictionary(path);
		}else{
			this.loadDictionary(path);
		}
	}
	
	/**
	 * Load the dictionary from the a plain text file, wherein each line is a word in the vocabulary. For each word, the characters are sorted in alphabetical order and the sorted string is used as a look-up key. Note that if two words are anagram with each other, they will have the same key after sorting their char arrays.
	 * @param path the path to the dictionary file
	 * @throws IOException
	 */
	public void loadDictionary(String path) throws IOException{
		InputStream input = null;
		InputStreamReader reader = null;
		BufferedReader br = null;
		try{
			input = new FileInputStream(path);
			reader = new InputStreamReader(input, Charset.forName("UTF-8"));
			br = new BufferedReader(reader);
			String line;
			this.mdict = new HashMap<String,HashSet<String>>();
			while ((line = br.readLine()) != null) {
				char[] strChar = line.toCharArray();
				Arrays.sort(strChar);
				String key = new String(strChar);
				if(!mdict.containsKey(key)){
					mdict.put(key, new HashSet<String>());
				}
				mdict.get(key).add(line);
		    }	
		}catch(IOException e){
			throw e;
		}finally{
			if(br != null)
				br.close();
		}
	}
	
	/**
	 * Save the preprocessed dictionary object to a file so that it can used immediately next time without preprocessing again. 
	 * @param outPath output path
	 * @throws IOException
	 */
	public void saveDictionary(String outPath) throws IOException{
		if(this.mdict == null)
			return;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = new FileOutputStream(outPath);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.mdict);	
		}catch(IOException e){
			throw e;
		}finally{
			if(oos != null)
				oos.close();			
		}
	}
	
	/**
	 * Find all the anagrams of the input word.
	 * @param input the query word
	 * @return a set of anagrams
	 */
	public Set<String> query(String input){
		if(input ==null || this.mdict == null || !this.mdict.containsKey(input))
			return new HashSet<String>(); // return an empty list
		else{
			return this.mdict.get(input);
		}
	}
	
	/**
	 * Load from a preprocessed dictionary.
	 * @param path the path to the preprocessed dictionary
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void loadPreprocessedDictionary(String path) throws IOException, ClassNotFoundException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			this.mdict = (HashMap<String, HashSet<String>>) ois.readObject();
		}catch(IOException e){
			throw e;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally{
			if(ois != null)
				ois.close();
		}
	}
}
