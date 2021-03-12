package PT2020.as2.Assignment2_LastVersion;
import java.util.ArrayList;

public class ConcreteStrategyTime implements Strategy {

	public int addClient(ArrayList<Coada> queues, Client c)
	{
		int min=10000000;
		int index=-1;
		for(int i=0;i<queues.size();i++)
		{
			if(queues.get(i).getQueueProcessingTime().intValue()<min)
			{
				int aux=queues.get(i).getQueueProcessingTime().intValue();
				min=aux;
				index=i;
			}
		}
		return index;
	}

}
