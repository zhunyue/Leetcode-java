package Greedy;

/*
    In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

    We may rotate the i-th domino, so that A[i] and B[i] swap values.

    Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

    If it cannot be done, return -1.
 */
public class DominoRotation {
    public int minDominoRotations(int[] A, int[] B) {
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];
        for(int i=0;i<A.length;i++){
            countA[A[i]]++;
            countB[B[i]]++;
            if(A[i]==B[i]){
                same[A[i]]++;
            }
        }
        for(int i=1;i<7;i++){
            if(countA[i]+countB[i]-same[i]>=A.length){
                return Math.min(countA[i],countB[i])-same[i];
            }
        }
        return -1;
    }
}
