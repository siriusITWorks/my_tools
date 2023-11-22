package grep_f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class grep_f {
	public static void main(String[] args) {
		
		// 検索対象の文字が記載されているテキストファイル（改行区切り）
		final  String filePath = "";
		// 検索対象のディレクトリパス
		final  String dirPath = "";
		
		// 検索対象の文字を抽出する
		ArrayList<String> targetMozi = getMozi(filePath);
		
		// 検索対象文字をGrepし、出力する
		grep(dirPath,targetMozi);
	}
	
	// 検索対象文字をGrepし、出力する
	private static void grep(String dirPath, ArrayList<String> targetMozi) {
		File dir = new File(dirPath);
		File[] fileList = dir.listFiles();
		StringBuffer bf = new StringBuffer();
		int findFlg = 0;
		
		for(String mozi:targetMozi) {
			bf.setLength(0);
			findFlg = 0;
			
			bf.append(mozi.concat(","));
			try {
				for(File file:fileList) {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line = br.readLine();
					while(line != null) {
						if(line.contains(mozi)) {
							bf.append(file.getName().concat(","));
							findFlg++;
						}
						line = br.readLine();
					}
					br.close();
				}	
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			bf.setLength(bf.length()-1);	// 末尾のカンマを削除する
			
			if(findFlg == 0) {
				bf.append("は見つかりませんでした。");
			}
			System.out.println(bf);
		}
		
	
	}

	// 検索対象の文字を抽出する
	public static ArrayList<String> getMozi(String filePath){
		ArrayList<String> mozis = new ArrayList<String>();
		
		try {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			while(line != null) {
				mozis.add(line);
				line = br.readLine();
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		if(mozis.size() == 0) {
			System.out.println("ファイルが空です。");
		}
		return mozis;
	}
	

}
