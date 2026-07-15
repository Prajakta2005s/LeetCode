class Solution {
    public long countVowels(String word) {
        long ans= 0;
        int n=word.length();
        
        for(int i=0;i<n;i++){
            char ch=word.charAt(i);

            if(ch=='a'|| ch=='e' || ch=='i'|| ch=='o'|| ch=='u'){
                ans +=(long)(i+1)*(n-i);
            }
        }
        return ans;
    }
}