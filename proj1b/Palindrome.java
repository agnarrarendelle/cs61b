public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordsInDeque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            Character letter = word.charAt(i);
            wordsInDeque.addLast(letter);
        }

        return wordsInDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordsInDeque = wordToDeque(word);
        return  isPalindromeHelper(wordsInDeque);
    }

    public boolean isPalindromeHelper(Deque<Character> words){
        if(words.size() <= 1){
            return true;
        }else if(words.removeFirst() == words.removeLast()){
            return isPalindromeHelper(words);
        }


        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordsInDeque = wordToDeque(word);
        return isPalindromeHelper(wordsInDeque, cc);
    }

    public boolean isPalindromeHelper(Deque<Character> words, CharacterComparator cc){
        if(words.size() <= 1){
            return true;
        }else{
            char head = words.removeFirst();
            char tail = words.removeLast();

            if(cc.equalChars(head, tail)){
                return  isPalindromeHelper(words,cc);
            }
        }

        return false;
    }
}