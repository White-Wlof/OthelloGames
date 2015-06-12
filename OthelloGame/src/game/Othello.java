package game;

import java.util.ArrayList;

/** Othelloクラス
 * @author Seazic1
 * オセロの処理全般を書いたクラス
 *  */
public class Othello {
	int lengthx;							//マス横の数
	int lengthy;							//マス縦の数
	int [][] board;							//ボード盤面
	int turnstone;							//現在のプレーヤー

	boolean finish;							//終了フラグ

	int [] stoneSet;						//石の色情報

	public static final int Turn_Black = 1;	//黒プレーヤー
	public static final int Turn_White = 2; //白プレイヤー　

	/** コンストラクタ
	 * @author Seazic1
	 * 初期の石の配置を行っている */
	public Othello() {
		//Point2D.Double P = new Point2D.Double(x, y);
		lengthx = 8;
		lengthy = 8;
		board = new int[lengthx][lengthy];
		stoneSet = new int[]{Turn_Black,Turn_White};

		for(int cnt_x = 0 ;cnt_x < lengthx;cnt_x++){
			for(int cnt_y = 0;cnt_y < lengthy;cnt_y++){
				board[cnt_x][cnt_y] = 0;
				if(cnt_x == (lengthx/2) || cnt_x == ((lengthx/2)-1)){
					if(cnt_y == (lengthy/2) || cnt_y == ((lengthy/2)-1)){
						board[cnt_x][cnt_y] = stoneSet[(cnt_x+cnt_y)%stoneSet.length];
					}
				}
			}
		}
		turnstone = Turn_Black;


	}
	/** コンストラクタ
	 * @param turnStone 初期プレイヤー変更 */
	public Othello(int turnStone){
		this();
		int flag = -1;
		for(int stone:stoneSet){
			if(stone == turnStone){
				flag = 1;
			}
		}
		if(flag == 1){
			turnstone = turnStone;
		}else{
			turnstone = Turn_Black;
		}

	}
	/**
	 * 置ける位置をリスト構造で格納して返す
	 */
	public ArrayList<int[]> getSetStoneArray(){
		ArrayList<int[]> setStoneArray = new ArrayList<int[]>();
		int length;
		if(lengthx < lengthy){
			length = lengthy;
		}else{
			length  = lengthx;
		}
		for(int cnt_x = 0;cnt_x < board.length;cnt_x++){
			for(int cnt_y = 0;cnt_y < board[cnt_x].length;cnt_y++){
				if(board[cnt_x][cnt_y] == 0){
					for(int cnt_around = 0;cnt_around < 9;cnt_around++){
						if(cnt_around == 4){
							continue;
						}
						int searchx = cnt_around%3-1;
						int searchy = cnt_around/3-1;
						for(int cnt = 1 ;cnt < length+1;cnt++){
							int searchPosx;
							int searchPosy;
							searchPosx = cnt_x+(searchx*cnt);
							searchPosy = cnt_y+(searchy*cnt);
							if(searchPosx < 0 || searchPosx > lengthx-1){
								break;
							}if(searchPosy < 0 || searchPosy > lengthy-1){
								break;
							}
							if(board[searchPosx][searchPosy] == 0){
								break;
							}
							if (board[searchPosx][searchPosy] == turnstone) {
								if(cnt == 1){
									break;
								}else{
									int[] a = new int[]{cnt_x,cnt_y};
									setStoneArray.add(a);
								}	
							}
						}
					}
				}
			}
		}
		return setStoneArray;
	}
	/**
	 * 置いた(x,y)の位置でひっくり返せるStoneの数を返す
	 * @return num
	 */
	public int getSetStoneChengeNum(int x,int y){
		int num = 0;
		int[][] board = this.board.clone();
		if(x < 0 || x > lengthx-1){
			return -1;
		}
		if(y < 0 || y > lengthy-1){
			return -1;
		}
		if(board[x][y] != 0){
			return -2;
		}
		int searchx = 0;
		int searchy = 0;
		int length;
		if(lengthx < lengthy){
			length = lengthy;
		}else{
			length  = lengthx;
		}
		for(int cnt_around = 0;cnt_around < 9;cnt_around++){
			searchx = cnt_around%3-1;
			searchy = cnt_around/3-1;
			if(cnt_around == 4){
				continue;
			}
			for(int cnt = 1;cnt < length+1;cnt++){
				int searchPosx;
				int searchPosy;
				searchPosx = x+(searchx*cnt);
				searchPosy = y+(searchy*cnt);
				if(searchPosx < 0 || searchPosx > lengthx-1){
					break;
				}if(searchPosy < 0 || searchPosy > lengthy-1){
					break;
				}
				if(board[searchPosx][searchPosy] == 0){
					break;
				}else if (board[searchPosx][searchPosy] == turnstone) {
					if(cnt == 1){
						break;
					}else{
						num+=cnt-1;
						break;
					}
				}
			}
		}
		return num;
	}
	public int getSetStoneNum(int x,int y){
		int num = 0;
		int[][] board = this.board.clone();
		if(x < 0 || x > lengthx-1){
			return -1;
		}
		if(y < 0 || y > lengthy-1){
			return -1;
		}
		if(board[x][y] != 0){
			return -2;
		}
		int searchx = 0;
		int searchy = 0;
		int length;
		if(lengthx < lengthy){
			length = lengthy;
		}else{
			length  = lengthx;
		}
		for(int cnt_around = 0;cnt_around < 9;cnt_around++){
			searchx = cnt_around%3-1;
			searchy = cnt_around/3-1;
			if(cnt_around == 4){
				continue;
			}
			for(int cnt = 1;cnt < length+1;cnt++){
				int searchPosx;
				int searchPosy;
				searchPosx = x+(searchx*cnt);
				searchPosy = y+(searchy*cnt);
				if(searchPosx < 0 || searchPosx > lengthx-1){
					break;
				}if(searchPosy < 0 || searchPosy > lengthy-1){
					break;
				}
				if(board[searchPosx][searchPosy] == 0){
					break;
				}else if (board[searchPosx][searchPosy] == turnstone) {
					if(cnt == 1){
						break;
					}else{
						num = getSetStoneArray().size();
						System.out.println("mySize:" + num);
						break;
					}
				}
			}
		}
		return num;
	}
	/** setStoneメソッド
	 * @param x 
	 * @param y
	 * (x,y)の位置に石を置けるか試す
	 * 置けるのならそのまま置き、ひっくり返す処理をする
	 * 置けないなら置けない状況に応じたエラーを返す*/
	public int setStone(int x,int y){
		if(x < 0 || x > lengthx-1){
			return -1;
		}
		if(y < 0 || y > lengthy-1){
			return -1;
		}
		if(board[x][y] != 0){
			return -2;
		}
		int searchx = 0;
		int searchy = 0;
		int length;
		int flag = -3;
		if(lengthx < lengthy){
			length = lengthy;
		}else{
			length  = lengthx;
		}
		for(int cnt_around = 0;cnt_around < 9;cnt_around++){
			searchx = cnt_around%3-1;
			searchy = cnt_around/3-1;
			if(cnt_around == 4){
				continue;
			}
			for(int cnt = 1;cnt < length+1;cnt++){
				int searchPosx;
				int searchPosy;
				searchPosx = x+(searchx*cnt);
				searchPosy = y+(searchy*cnt);
				if(searchPosx < 0 || searchPosx > lengthx-1){
					break;
				}if(searchPosy < 0 || searchPosy > lengthy-1){
					break;
				}
				if(board[searchPosx][searchPosy] == 0){
					break;
				}else if (board[searchPosx][searchPosy] == turnstone) {
					if(cnt == 1){
						break;
					}else{
						flag = 1;
						for(int cnt2 = 0; cnt2 < cnt;cnt2++){
							board[x+(searchx*cnt2)][y+(searchy*cnt2)] = turnstone;
						}
						board[x][y] = turnstone;
						break;
					}
				}
			}
		}
		if(flag == 1){
			turnnext();
		}
		return flag;
	}
	/** プレーヤー変更メソッド
	 * プレイヤを変更できたら変更する
	 * 全員が変更できなかったら終了フラグをtrueにする */
	private void turnnext(){
		int Index = 0;
		for(int stone : stoneSet){
			if(stone == turnstone){
				Index++;
				break;
			}
			Index++;
		}
		if(Index > stoneSet.length-1){
			Index = 0; 
		}
		if(turnCheck(stoneSet[Index])){
			turnstone = stoneSet[Index];
		}else{
			if(!turnCheck(turnstone)){
				finish = true;
			}
		}
	}
	/** 石置き確認メソッド
	 * @param turnStone
	 * turnStoneで指定したプレイヤーの
	 * 石が置けるかを確認するためのメソッド */
	private boolean turnCheck(int turnStone){
		int length;
		if(lengthx < lengthy){
			length = lengthy;
		}else{
			length  = lengthx;
		}
		for(int cnt_x = 0;cnt_x < board.length;cnt_x++){
			for(int cnt_y = 0;cnt_y < board[cnt_x].length;cnt_y++){
				if(board[cnt_x][cnt_y] == 0){
					for(int cnt_around = 0;cnt_around < 9;cnt_around++){
						if(cnt_around == 4){
							continue;
						}
						int searchx = cnt_around%3-1;
						int searchy = cnt_around/3-1;
						for(int cnt = 1 ;cnt < length+1;cnt++){
							int searchPosx;
							int searchPosy;
							searchPosx = cnt_x+(searchx*cnt);
							searchPosy = cnt_y+(searchy*cnt);
							if(searchPosx < 0 || searchPosx > lengthx-1){
								break;
							}if(searchPosy < 0 || searchPosy > lengthy-1){
								break;
							}
							if(board[searchPosx][searchPosy] == 0){
								break;
							}
							if (board[searchPosx][searchPosy] == turnStone) {
								if(cnt == 1){
									break;
								}else{
									return true;
								}	
							}
						}
					}
				}
			}
		}
		return false;
	}
	/** ゲットボードメソッド
	 * @return board
	 * ボード盤面を全て渡すメソッド */
	public int[][] getBoard(){
		return board;
	}
	/** ゲットターンメソッド
	 * @return turnstone
	 * 現在のターンプレイヤー渡すメソッド */
	public int getTurn(){
		return turnstone;
	}
	/**フィニッシュフラグ
	 * @return boolean
	 * ゲームが終了したかどうかを返す処理 */
	public boolean isFinish() {
		return finish;
	}
	/**　勝者
	 * @return int
	 * 勝った人を返すメソッド */
	public int winner(){
		int [] stone = new int[stoneSet.length];
		for(int cnt = 0;cnt < stone.length;cnt++){
			stone[cnt] = 0;
		}
		for(int cnt_x = 0;cnt_x < board.length;cnt_x++){
			for(int cnt_y = 0;cnt_y < board[cnt_x].length;cnt_y++){
				for(int cnt = 0;cnt < stone.length;cnt++){
					if(stoneSet[cnt] == board[cnt_x][cnt_y]){
						stone[cnt]++;
					}
				}
			}
		}
		int max = -1;
		int maxIndex = -1;
		for(int cnt = 0;cnt < stone.length;cnt++){
			if(max < stone[cnt]){
				max = stone[cnt];
				maxIndex = cnt;
			}else if(max == stone[cnt]){
				return stoneSet.length;
			}
		}
		return stoneSet[maxIndex];
	}
	public int[] getStoneset(){
		return stoneSet;
	}
	public int getStoneIndex(int Index){
		int index = -1;
		for (int i = 0; i < stoneSet.length; i++) {
			if(stoneSet[i] == Index){
				index = i;
			}
		}
		return Index;
	}
}
