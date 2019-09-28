package Greedy;

/*
    There are N children standing in a line. Each child is assigned a rating value.

    You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.
    What is the minimum candies you must give?

    Example 1:

    Input: [1,0,2]
    Output: 5
    Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
    Example 2:

    Input: [1,2,2]
    Output: 4
    Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
                 The third child gets 1 candy because it satisfies the above two conditions.
 */
public class candy {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candies = new int[len];
        for(int i = 0; i < len; i++){
            candies[i] = 1;
        }
        int total = 0;
        for(int i = 1; i < len; i++){
            if(ratings[i] > ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }
        }

        for(int i = len - 2; i >= 0; i--){
            if(ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]){
                candies[i] = candies[i+1] +1;
            }
        }

        for (int i = 0; i < candies.length; i++) {
            total += candies[i];
        }
        return total;

    }

    /*
         One pass solution
     */
    public int candy2(int[] ratings) {
        int up = 0;
        int down = 0;
        int peak = 0;
        int candies = 1;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] > ratings[i-1]){
                up++;
                candies += (1+up);
                peak = up;
                down = 0;

            } else if(ratings[i] == ratings[i-1]){
                candies ++;
                up = 0;
                down = 0;
                peak = 0;
            } else{
                up = 0;
                down ++;
                candies += (1+down) + (peak >= down ? -1 : 0);
            }
        }
        return candies;

    }
}
