package CS112Project;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class KNNPredictor extends Predictor {
	private int k;
	private boolean alive;
	private int pclass = 0;
	private int survive = 0;
	private ArrayList ar1;
	private ArrayList ar;
	
	public KNNPredictor(int k) {
		this.k = k;
	
	}
	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try(Scanner rowScanner = new Scanner(line)){
			rowScanner.useDelimiter(",");
			while(rowScanner.hasNext()){
				values.add(rowScanner.next());
				values.remove("age");
				values.remove("sex");
				values.remove("survived");
				values.remove("pclass");
				values.remove("fare");
				values.remove("name");
			}
		}
		return values;
	}
		
	@Override
	ArrayList<DataPoint> readData(String filename) {
		ArrayList<DataPoint> ar = new ArrayList<DataPoint>();
		ArrayList<DataPoint> ar1 = new ArrayList<DataPoint>();
		try(Scanner scanner = new Scanner(new File(filename));){
			while(scanner.hasNextLine()) {
				List<String> records = getRecordFromLine(scanner.nextLine());
				Random rand = new Random();
				double randNum = rand.nextDouble();
				if(randNum < 0.9) {
					for(int i = 0; i <= records.size(); i ++) {
						if (i == 5) {
							String hi = records.get(1);
							if(hi.equals("0")) {
							}
							else if(hi.equals("1")){
								survive = survive +1;
							}
							if(records.get(5).isEmpty()){
								double x = 0;
								String s1 = records.get(6);
								double y = Double.parseDouble(s1);
								DataPoint hello = new DataPoint(x,y,"train", alive);
								ar1.add(hello);
							}
							else {
								String s = records.get(5);
								double x = Double.parseDouble(s);
								if(records.size() < 7) {
									double y = 0;
									DataPoint hello = new DataPoint(x,y,"train", alive);
									ar1.add(hello);
								}
								else {
									String s1 = records.get(6);
									double y = Double.parseDouble(s1);
									DataPoint hello = new DataPoint(x,y,"train", alive);
									ar1.add(hello);
							}
						}
					}
				}
					
				}else {
					for(int i = 0; i <= records.size(); i ++) {
						if (i == 5) {
							String hi = records.get(1);
								survive = survive +1;
							if(records.get(5).isEmpty()){
								double x = 0;
								String s1 = records.get(6);
								double y = Double.parseDouble(s1);
								DataPoint hello = new DataPoint(x,y,"test", alive);
								ar.add(hello);
							}
							else {
								String s = records.get(5);
								double x = Double.parseDouble(s);
								if(records.size() < 7) {
									double y = 0;
									DataPoint hello = new DataPoint(x,y,"test", alive);
									ar.add(hello);
								}
								else {
									String s1 = records.get(6);
									double y = Double.parseDouble(s1);
									DataPoint hello = new DataPoint(x,y,"test", alive);
									ar.add(hello);
							}
						}
					}
				}
			}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Total amount of survivers (Train): " +survive);
		return ar;
		
	}
	private double getDistance(DataPoint p1, DataPoint p2){
		double y2 = p1.getF1();
		double y1 = p1.getF2();
		double x2 = p2.getF1();
		double x1 = p2.getF2();
		
		return Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x2));
		
	}

	@Override
	String test(DataPoint data) {
		Double[][] arr = new Double[1000][2];
		if (data.getLabel().equals("test")) {
			for(int i = 0; i <= ar1.size(); i ++) {
				double fin = getDistance(data, (DataPoint) ar1.get(i));
				arr[i][i] = fin;
				arr[i][i+1] = 1.0;
			}
		}
		else if(data.getLabel().equals("train")) {
			for(int i = 0; i <= ar1.size(); i ++) {
				double fin = getDistance(data, (DataPoint) ar1.get(i));
				arr[i][i] = fin;
				arr[i][i+1] = 0.0;
				
			}
			
		}
		java.util.Arrays.sort(arr,new java.util.Comparator<Double[]>(){
			public int compare(Double[]a,Double[]b){
				return a[0].compareTo(b[0]);
				}
			});
		for (int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[i].length; ++j) {
                System.out.println(arr[i][j]);
            }
            }
		return "hello";
	}

	@Override
	Double getAccuracy(ArrayList<DataPoint> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Double getPercision(ArrayList<DataPoint> data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static void test() {
		Random rand = new Random();
		double randNum = rand.nextDouble();
		if(randNum < 0.9) {
			
		}else{
			
		}
		
	}

}
