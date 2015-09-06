# Anagram
Find all the anagrams in a dictionary

For interview purpose only. 
Outside of programming interview and perhaps the Da Vinci Code, I haven't seen much practical use of anagram.

# Usage
		try {
			Anagram anagram = new Anagram(); //create an empty object
			anagram.loadDictionary("test/dict.txt");  //load dictionary
			Set<String> result = anagram.query("abc"); //query 
			for(String s : result){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

Equivalently, we can initialize an Anagram object with a dictionary directly.

		try {
			Anagram anagram = new Anagram("test/dict.txt"); //create an Anagram object and load dictionary
			Set<String> result = anagram.query("abc"); //query 
			for(String s : result){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

After loading the dictionary, we can save the preprocessed dictionary to a file by:

		anagram.saveDictionary("test/preprocessed.txt");

Next time, we can load directly from the preprocessed file, saving the time to preprocess the dictionary again:

		anagram.loadPreprocessedDictionary("test/preprocessed.txt");

We can also create an object directly with a preprocessed dictionary:

		Anagram anagram = new Anagram("test/preprocessed.txt", true);


Enjoy it.
