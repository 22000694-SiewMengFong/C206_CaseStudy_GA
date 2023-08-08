package HomePage;

import HelperPackage.DBData;

public class tettttt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] AllegiesList = DBData.getAllAllegies();
		if (AllegiesList == null) {
			System.out.println("PPP");
		}

		
		 for (String allegies : AllegiesList) {
			
			System.out.println(allegies);
		}
		
		//System.out.println(String.join(",", AllegiesListDuplicate));
		System.out.println("DONE");
		
		String[][] allItems = DBData.getAllItem();
		for (String[] a : allItems) {
			for (String x : a) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
		
	}

}
