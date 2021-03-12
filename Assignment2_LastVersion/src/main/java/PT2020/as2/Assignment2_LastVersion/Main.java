package PT2020.as2.Assignment2_LastVersion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public static void main( String[] args ) throws IOException
    {
		
		DataReader dataReader = new DataReader();
		/*String path="";
		Scanner scanner= new Scanner(System.in);
		System.out.println("Introduceti numele fisierului de input:");
		path=scanner.next();
		System.out.println(path);*/
		dataReader.readFromFile(args[0]);
		/*String output="";
		if(args[0].equals("in-test-1.txt"))
		{
			output="output1.txt";
		}
		else if(args[0].equals("in-test-2.txt"))
		{
			output="output2.txt";
		}
		else if(args[0].equals("in-test-3.txt"))
		{
			output="output3.txt";
		}*/
		ArrayList<Client> clients=new ArrayList<Client>();
		int nrClients=dataReader.getNrClients();
		int nrQueues=dataReader.getNrQueues();
		int timeSimulation=dataReader.getTimeSimulation();
		int minArrivalTime=dataReader.getMinArrival();
		int maxArrivalTime=dataReader.getMaxArrival();
		int minServiceTime=dataReader.getMinService();
		int maxServiceTime=dataReader.getMaxService();
		//ArrayList<Client> c= new ArrayList<Client>(nrClients);
		System.out.println("Nr. clienti:"+nrClients);
		System.out.println("Nr. cozi:"+nrQueues);
		System.out.println("Timp simulare:"+timeSimulation);
		System.out.println("min. Arrival:"+minArrivalTime);
		System.out.println("max. Arrival:"+maxArrivalTime);
		System.out.println("min. Service:"+minServiceTime);
		System.out.println("max. Service:"+maxServiceTime);
		//ClientGenerator c=new ClientGenerator(nrClients,dataReader.getMinArrival(),dataReader.getMaxArrival(),dataReader.getMinService(),dataReader.getMaxService());
		//clients=c.generateClients();
		dataReader.generateClients(clients);
		Collections.sort(clients);
		int i=1;
		for(Client k: clients)
		{
			k.setID(i);
			i++;
		}
		System.out.println("Ordonare crescatoare clienti dupa ArrivalTime: "+clients);
		//Strategy strategy= new ConcreteStrategyTime();
		//Scheduler scheduler=new Scheduler(nrQueues,nrClients);
		
		SimulationManager sm = new SimulationManager(dataReader.getTimeSimulation(),dataReader.getNrQueues(),dataReader.getNrClients(),clients,args[1]);
		System.out.println("Average Waiting Time: "+sm.averageTime());
		Thread t = new Thread(sm);
		t.start();
		
		

		
		
    }
}