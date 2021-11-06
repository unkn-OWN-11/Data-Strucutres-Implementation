import java.util.*;

class Nodes{
    char data;
    Nodes[] arr;
    boolean end;

    public void initialize(){
        end= false;
        arr= new Nodes[26]; // total char in english alphabets is 26
    }

    Nodes(){
        initialize();
    }

    Nodes(char _data){
        data= _data;
        initialize();
    }
}

public class Trie{

    public static void implementTrie(Nodes root, String word){
        char[] chars= word.toCharArray();
        for(char c: chars){
            int index= (int)c-97; // checking for lower cases letters.
            if(root.arr[index]== null){
                root.arr[index]= new Nodes(c);
            }
            root= root.arr[index]; // If its not null then the root will point towards that node.
        }
        root.end= true; /* After the for loop, root will point towards the last character
                        which we can set as true to indicate that the word has ended. */
    }

    public static void printWords(Nodes root, String word){
        if(root.end){
            System.out.println(word);
        }
        for(Nodes node: root.arr){
            if(node!= null){
                word= word+String.valueOf(node.data);
                printWords(node, word);
                word= word.substring(0, word.length()-1);
            }
        }
        return; /* If its the last character then it will have no child.
                In that case we will return, so as to continue with other words. */
    }


    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Nodes root= new Nodes();
        Nodes temp= root; // Storing the address of the root pointer.
        System.out.println("ENTER TOTAL NUMBER OF WORDS:");
        int totalWords= sc.nextInt();
        sc.nextLine();
        System.out.println("ENTER WORDS:");
        while(totalWords-->0){
            String word= sc.nextLine();
            implementTrie(root, word); // It will change the address of the root pointer.
            root= temp; // Pointing root pointer to the address where it should actually points.
        }
        System.out.println();
        printWords(root, ""); /* Always prints the words in lexicographical order. */
        sc.close();
    }
}