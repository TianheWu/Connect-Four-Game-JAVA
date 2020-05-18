
public class Chessboard {
	//Chess board has two attributes, they are length and width
	private int length;
	private int width;
	public int [][]chessboardSize;
	
	public Chessboard() {
		length = 0;
		width = 0;
		chessboardSize = new int[length][width];
	}
	
	//Initialize the size of chess board
	public Chessboard(int length, int width) {
		this.length = length;
		this.width = width;
		chessboardSize = new int[this.length][this.width];
		
		//Initialize the chess board with 0
		for (int i = 0; i < this.length; i++)
			for (int j = 0; j < this.width; j++)
				chessboardSize[i][j] = 0;
	}
	
	//Get the length of chess board
	public int GetLength() {
		return length;
	}
	
	//Get the width of chess board
	public int GetWidth() {
		return width;
	}
	
	//Check the status of win, signal is the sign of color
	public boolean StatsOfWin(int xCoordinate, int yCoordinate, int signal) {

		int i = xCoordinate, j = yCoordinate;
		
		//Horizon
		if (j - 2 >= 0 && j + 1 < width) {
			//From this coordinate to left 2 chess
			if (chessboardSize[i][j] == signal && chessboardSize[i][j - 1] == signal
					&& chessboardSize[i][j - 2] == signal && chessboardSize[i][j + 1] == signal)
				return true;
		}
		if (j - 3 >= 0) {
			//From this coordinate to left 3 chess
			if (chessboardSize[i][j] == signal && chessboardSize[i][j - 1] == signal
					&& chessboardSize[i][j - 2] == signal && chessboardSize[i][j - 3] == signal)
				return true;
		}
		if (j + 2 < width  && j - 1 >= 0) {
			//From this coordinate to right 2
			if (chessboardSize[i][j] == signal && chessboardSize[i][j + 1] == signal
					&& chessboardSize[i][j + 2] == signal && chessboardSize[i][j - 1] == signal)
				return true;
		}
		if (j + 3 < width) {
			//From this coordinate to right 3
			if (chessboardSize[i][j] == signal && chessboardSize[i][j + 1] == signal
					&& chessboardSize[i][j + 2] == signal && chessboardSize[i][j + 3] == signal)
				return true;
		}
		
		//Vertical
		if (i + 2 < length && i - 1 >= 0) {
			//From this coordinate to down 2
			if (chessboardSize[i][j] == signal && chessboardSize[i + 1][j] == signal
					&& chessboardSize[i + 2][j] == signal && chessboardSize[i - 1][j] == signal)
				return true;
		}
		if (i + 3 < length) {
			//From this coordinate to down 3
			if (chessboardSize[i][j] == signal && chessboardSize[i + 1][j] == signal
					&& chessboardSize[i + 2][j] == signal && chessboardSize[i + 3][j] == signal)
				return true;
		}
		if (i - 2 >= 0 && i + 1 < length) {
			//From this coordinate to up 2
			if (chessboardSize[i][j] == signal && chessboardSize[i - 1][j] == signal
					&& chessboardSize[i - 2][j] == signal && chessboardSize[i + 1][j] == signal)
				return true;
		}
		if (i - 3 >= 0) {
			//From this coordinate to up 3
			if (chessboardSize[i][j] == signal && chessboardSize[i - 1][j] == signal
					&& chessboardSize[i - 2][j] == signal && chessboardSize[i - 3][j] == signal)
				return true;
		}
		
		//Inclined left line 
		if (j - 2 >= 0 && i - 2 >= 0 && i + 1 < length && j + 1 < width) {
			//From this coordinate to up-left 2
			if (chessboardSize[i][j] == signal && chessboardSize[i - 1][j - 1] == signal
					&& chessboardSize[i - 2][j - 2] == signal && chessboardSize[i + 1][j + 1] == signal)
				return true;
		}
		if (j - 3 >= 0 && i - 3 >= 0) {
			//From this coordinate to up-left 3
			if (chessboardSize[i][j] == signal && chessboardSize[i - 1][j - 1] == signal
					&& chessboardSize[i - 2][j - 2] == signal && chessboardSize[i - 3][j - 3] == signal)
				return true;
		}
		if (j + 2 < width && i + 2 < length && i - 1 >= 0 && j - 1 >= 0) {
			//From this coordinate to down-right 2
			if (chessboardSize[i][j] == signal && chessboardSize[i + 1][j + 1] == signal
					&& chessboardSize[i + 2][j + 2] == signal && chessboardSize[i - 1][j - 1] == signal)
				return true;
		}
		if (j + 3 < width && i + 3 < length) {
			//From this coordinate to down-right 3
			if (chessboardSize[i][j] == signal && chessboardSize[i + 1][j + 1] == signal
					&& chessboardSize[i + 2][j + 2] == signal && chessboardSize[i + 3][j + 3] == signal)
				return true;
		}
		
		//Inclined right line 
		if (j + 2 < width && i - 2 >= 0 && i + 1 < length && j - 1 >= 0) {
			//From this coordinate to up-right 2
			if (chessboardSize[i][j] == signal && chessboardSize[i - 1][j + 1] == signal
					&& chessboardSize[i - 2][j + 2] == signal && chessboardSize[i + 1][j - 1] == signal)
				return true;
		}
		if (j + 3 < width && i - 3 >= 0) {
			//From this coordinate to up-right 2
			if (chessboardSize[i][j] == signal && chessboardSize[i - 1][j + 1] == signal
					&& chessboardSize[i - 2][j + 2] == signal && chessboardSize[i - 3][j + 3] == signal)
				return true;
		}
		if (j - 2 >= 0 && i + 2 < length && i - 1 >= 0 && j + 1 < width) {
			//From this coordinate to down-left
			if (chessboardSize[i][j] == signal && chessboardSize[i + 1][j - 1] == signal
					&& chessboardSize[i + 2][j - 2] == signal && chessboardSize[i - 1][j + 1] == signal)
				return true;
		}
		if (j - 3 >= 0 && i + 3 < length) {
			//From this coordinate to down-left
			if (chessboardSize[i][j] == signal && chessboardSize[i + 1][j - 1] == signal
					&& chessboardSize[i + 2][j - 2] == signal && chessboardSize[i + 3][j - 3] == signal)
				return true;
		}	
		return false;
	}
	
	//Check the status of win, signal is the sign of color
		public boolean StatsOfNextWin(int xCoordinate, int yCoordinate, int signal) {

			int i = xCoordinate, j = yCoordinate;
			
			//Horizon
			if (j - 2 >= 0 && j + 1 < width) {
				//From this coordinate to left 2 chess
				if (chessboardSize[i][j] == 0 && chessboardSize[i][j - 1] == signal
						&& chessboardSize[i][j - 2] == signal && chessboardSize[i][j + 1] == signal)
					return true;
			}
			if (j - 3 >= 0) {
				//From this coordinate to left 3 chess
				if (chessboardSize[i][j] == 0 && chessboardSize[i][j - 1] == signal
						&& chessboardSize[i][j - 2] == signal && chessboardSize[i][j - 3] == signal)
					return true;
			}
			if (j + 2 < width  && j - 1 >= 0) {
				//From this coordinate to right 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i][j + 1] == signal
						&& chessboardSize[i][j + 2] == signal && chessboardSize[i][j - 1] == signal)
					return true;
			}
			if (j + 3 < width) {
				//From this coordinate to right 3
				if (chessboardSize[i][j] == 0 && chessboardSize[i][j + 1] == signal
						&& chessboardSize[i][j + 2] == signal && chessboardSize[i][j + 3] == signal)
					return true;
			}
			
			//Vertical
			if (i + 2 < length && i - 1 >= 0) {
				//From this coordinate to down 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i + 1][j] == signal
						&& chessboardSize[i + 2][j] == signal && chessboardSize[i - 1][j] == signal)
					return true;
			}
			if (i + 3 < length) {
				//From this coordinate to down 3
				if (chessboardSize[i][j] == 0 && chessboardSize[i + 1][j] == signal
						&& chessboardSize[i + 2][j] == signal && chessboardSize[i + 3][j] == signal)
					return true;
			}
			if (i - 2 >= 0 && i + 1 < length) {
				//From this coordinate to up 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i - 1][j] == signal
						&& chessboardSize[i - 2][j] == signal && chessboardSize[i + 1][j] == signal)
					return true;
			}
			if (i - 3 >= 0) {
				//From this coordinate to up 3
				if (chessboardSize[i][j] == 0 && chessboardSize[i - 1][j] == signal
						&& chessboardSize[i - 2][j] == signal && chessboardSize[i - 3][j] == signal)
					return true;
			}
			
			//Inclined left line 
			if (j - 2 >= 0 && i - 2 >= 0 && i + 1 < length && j + 1 < width) {
				//From this coordinate to up-left 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i - 1][j - 1] == signal
						&& chessboardSize[i - 2][j - 2] == signal && chessboardSize[i + 1][j + 1] == signal)
					return true;
			}
			if (j - 3 >= 0 && i - 3 >= 0) {
				//From this coordinate to up-left 3
				if (chessboardSize[i][j] == 0 && chessboardSize[i - 1][j - 1] == signal
						&& chessboardSize[i - 2][j - 2] == signal && chessboardSize[i - 3][j - 3] == signal)
					return true;
			}
			if (j + 2 < width && i + 2 < length && i - 1 >= 0 && j - 1 >= 0) {
				//From this coordinate to down-right 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i + 1][j + 1] == signal
						&& chessboardSize[i + 2][j + 2] == signal && chessboardSize[i - 1][j - 1] == signal)
					return true;
			}
			if (j + 3 < width && i + 3 < length) {
				//From this coordinate to down-right 3
				if (chessboardSize[i][j] == 0 && chessboardSize[i + 1][j + 1] == signal
						&& chessboardSize[i + 2][j + 2] == signal && chessboardSize[i + 3][j + 3] == signal)
					return true;
			}
			
			//Inclined right line 
			if (j + 2 < width && i - 2 >= 0 && i + 1 < length && j - 1 >= 0) {
				//From this coordinate to up-right 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i - 1][j + 1] == signal
						&& chessboardSize[i - 2][j + 2] == signal && chessboardSize[i + 1][j - 1] == signal)
					return true;
			}
			if (j + 3 < width && i - 3 >= 0) {
				//From this coordinate to up-right 2
				if (chessboardSize[i][j] == 0 && chessboardSize[i - 1][j + 1] == signal
						&& chessboardSize[i - 2][j + 2] == signal && chessboardSize[i - 3][j + 3] == signal)
					return true;
			}
			if (j - 2 >= 0 && i + 2 < length && i - 1 >= 0 && j + 1 < width) {
				//From this coordinate to down-left
				if (chessboardSize[i][j] == 0 && chessboardSize[i + 1][j - 1] == signal
						&& chessboardSize[i + 2][j - 2] == signal && chessboardSize[i - 1][j + 1] == signal)
					return true;
			}
			if (j - 3 >= 0 && i + 3 < length) {
				//From this coordinate to down-left
				if (chessboardSize[i][j] == 0 && chessboardSize[i + 1][j - 1] == signal
						&& chessboardSize[i + 2][j - 2] == signal && chessboardSize[i + 3][j - 3] == signal)
					return true;
			}	
			return false;
		}
}
