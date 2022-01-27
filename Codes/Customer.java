import java.util.*;
import java.io.IOException; 
public class Customer {
	static Scanner sc = new Scanner(System.in);
	static DAO obj = new DAO();

	public static void main(String[] args) throws Exception,IOException, InterruptedException {
		obj.connect();
                obj.clear();
		String CompName = obj.getOName();
		custHomePage(CompName,false);

	}
        
        public static void custHomePage(String CompName,boolean invalid) throws Exception {
                System.out.println("\n\n ----------------------------------------");
		System.out.println("\t      WELCOME TO " + CompName.toUpperCase() + "      \t");
		System.out.println(" ----------------------------------------\n");
                if(invalid){
                   System.out.println(" !!! Invalid Input Please Try Again !!! ");
                }
                System.out.println("\nEnter Your Choice: ");
                System.out.println("\n1.Book Service\t\t2.Close");    
                String option = sc.next();
                sc.nextLine();
		boolean check = obj.checkString(option);
		int select = 0;
		if (check) {
			select = Integer.parseInt(option);
		}
		if (check && (select== 1 || select == 2)) {
		   if(select==1){
                       printMenu(CompName,"Book Service");
                       customerName(CompName);
                       
                    }
                   else{
                       obj.waitMsg("Saving Data",1000,2);
                        obj.waitMsg("Closing Window",1000,3);
                       System.out.println("\nProgram Closed");          
                   }	
		} else {
			obj.clear();
                        custHomePage(CompName,true);
		}
		
        }

        public static void customerName(String CompName)throws Exception{
         System.out.print("Enter Your Name: ");
         String cname = sc.nextLine();
         addcustomer(CompName,false,cname);
        }

	public static void addcustomer(String CompName,boolean invalid,String cname) throws Exception {
		
            if(invalid){
             
              printMenu(CompName,"Book Service");
              System.out.println("\n !!! Invalid Input Please Try Again !!! \n");
            }

            System.out.println("\nAvailable Services: \n");
          
		ArrayList<String> list = obj.servicesList(CompName);
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ": " + list.get(i).toUpperCase());
		}
		System.out.println("\n-------------" +cname.toUpperCase()+", Please Choose Your Service--------\n");
		String option = sc.next();
            sc.nextLine();
		boolean check = obj.checkString(option);
		int select = 0;
		if (check) {
			select = Integer.parseInt(option);
		}
            if(check &&(select > 0 && select <= list.size() )){
                
		    String sname = "S_" + list.get(select - 1);
		    int tocken =  obj.addcustomer(cname, sname,CompName);
                System.out.println("\n                        =======");
		    System.out.println("Your tocken number is:  | "+tocken+" |");
                System.out.println("                        =======");
                obj.waitMsg("Returning To Main Menu",1000,15);
                custHomePage(CompName,false);
            }else{
                 obj.clear();
                 addcustomer(CompName,true,cname); 
               }
                
	}

        public static void printMenu(String compName, String heading)throws IOException, InterruptedException{
                obj.clear();
                System.out.println("\n\n ----------------------------------------");
		System.out.println("\t      WELCOME TO " + compName.toUpperCase() + "      \t");
		System.out.println(" ----------------------------------------\n");
		System.out.println("\n\t<---------"+heading.toUpperCase()+"----------->\n");
        }
 
        
}