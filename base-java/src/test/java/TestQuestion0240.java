import org.junit.Test;
import org.wpy.leetcode.Question0240;

public class TestQuestion0240 {
    Question0240 q = new Question0240();

    @Test
    public void testSearchMatrixA() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        System.out.println(q.searchMatrix(matrix,target));
    }

    @Test
    public void testSearchMatrixB() {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}};
        int target = 20;
        System.out.println(q.searchMatrix(matrix,target));
    }
}
