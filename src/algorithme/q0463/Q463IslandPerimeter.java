package algorithme.q0463;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;


/**
 * 463. Island Perimeter

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

 * @author Lee
 *
 */
public class Q463IslandPerimeter {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] {
				new int[][] {
					{0,1,0},
					{0,1,0},
					{0,1,1}
				},
				new int[][] { 
					{ 0, 1, 0, 0 }, 
					{ 1, 1, 1, 0 }, 
					{ 0, 1, 0, 0 }, 
					{ 1, 1, 0, 0 } 
				} ,
				new int[][] { 
					{ 0, 0, 0, 0 }, 
					{ 1, 1, 1, 1 }, 
					{ 1, 0, 0, 1 }, 
					{ 1, 0, 1, 1 } 
				}
		};

		TimerUtils.batchRunAll(Q463IslandPerimeter.class, times, argsArr);
	}

	//穷举 O(mn) 适用于水域与陆地面积相当时。当水域比陆地面积大很多时，应该用DFS（因为所有陆地连接在一起）
	@RunTimer
	public int islandPerimeter0(int[][] grid) {
		int l = grid[0].length;
		int h = grid.length;
		int perimeter = 0;
		for (int x = 0; x < l; x++) { // horizontal
			for (int y = 0; y < h; y++) { // vertical
				if (grid[y][x] == 1) {
					if( y == 0 || grid[y-1][x] == 0) perimeter++;
					if( y == h - 1 || grid[y+1][x] == 0) perimeter++;
					if( x == 0 || grid[y][x-1] == 0) perimeter++;
					if( x == l - 1 || grid[y][x+1] == 0) perimeter++;
//					int p = 4;
//					if ((y - 1) >= 0 && grid[y - 1][x] == 1) // up
//						p--;
//					if ((y + 1) < x && grid[y + 1][x] == 1) // down
//						p--;
//					if ((x - 1) >= 0 && grid[y][x - 1] == 1) // left
//						p--;
//					if ((x + 1) < l && grid[y][x + 1] == 1) // right
//						p--;
//					perimeter += p;
				}
			}
		}
		return perimeter;
	}
	
	@RunTimer
	public int islandPerimeter1(int[][] grid) {
	    int[][] d = new int[][] {{0,-1}, {-1,0}, {0,1}, {1,0}};
	    
	    int perimeter = 0;
	    for(int i=0; i<grid.length; i++) {
	        for(int j=0; j<grid[0].length; j++) {
	            if(grid[i][j] == 0)
	                continue;
	            
	            for(int k=0; k<d.length; k++) {
	                int x=i+d[k][0], y=j+d[k][1];
	                if(x<0 || x>=grid.length || y<0 || y>=grid[0].length || grid[x][y] == 0)
	                    perimeter++;
	            }
	        }
	    }
	    
	    return perimeter;
	}
	
	//DFS depth-first-search 深度优先搜索算法  递归
	@RunTimer
	public int islandPerimeter2(int[][] grid) {
        if (grid == null) return 0;
        
        //此处必须深度复制源二维数组用于DFS，因为该DFS算法中改变了原来数组的值，不可重复使用。
        //也可以用另一个标志数组来标识搜索过的区块，就可不用改变源数组的值。
        int[][] tmpGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < tmpGrid.length; i++) {	//深度复制内层数组
			System.arraycopy(grid[i], 0, tmpGrid[i], 0, grid[i].length);
		}
        
        for (int i = 0 ; i < grid.length ; i++){
            for (int j = 0 ; j < grid[0].length ; j++){
                if (grid[i][j] == 1) {
                    return getPerimeter(tmpGrid,i,j);
                }
            }
        }
        return 0;
    }
    
    public int getPerimeter(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {return 1;}
        if (grid[i][j] == 0) {
            return 1;
        }
        if (grid[i][j] == -1) return 0;
        
        int count = 0;
        grid[i][j] = -1;
        
        count += getPerimeter(grid, i-1, j);
        count += getPerimeter(grid, i, j-1);
        count += getPerimeter(grid, i, j+1);
        count += getPerimeter(grid, i+1, j);
        
        return count;
        
    }
    
    @RunTimer
    public int islandPerimeter3(int[][] grid) {

        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            int[] line = grid[i];
            for (int j = 0; j < line.length; j++) {
                if (i == 0) {
                    perimeter += grid[i][j] ^ 0;
                }
                if (j == 0) {
                	perimeter += grid[i][j] ^ 0;
                }
                
                if (i == grid.length - 1) {
                    perimeter += grid[i][j] ^ 0;
                }
                else {
                    perimeter += grid[i][j] ^ grid[i + 1][j];
                }
                if (j == line.length - 1) {
                    perimeter += grid[i][j] ^ 0;
                }
                else {
                    perimeter += grid[i][j] ^ grid[i][j + 1];
                }
            }
        }
        return perimeter;
    }
}
