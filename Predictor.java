package CS112Project;

import java.util.ArrayList;
import java.util.List;

public abstract class Predictor extends DataPoint {
	abstract ArrayList<DataPoint> readData(String filename);
	abstract String test(DataPoint data);
	abstract Double getAccuracy(ArrayList<DataPoint> data);
	abstract Double getPercision(ArrayList<DataPoint> data);
}
