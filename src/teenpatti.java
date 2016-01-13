import java.util.*;
import java.io.*;
class teenpatti {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	static InputStreamReader x=new InputStreamReader(System.in);
	static BufferedReader y=new BufferedReader(x);
	
	static int totbet=0,resusr,rescmp,gameover=0,bet=0,cc=0;
	static String hands[]={"High Card","Pair","Colour","Sequence","Coloured Sequence","Trail"};
	static String card[]={"2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH","AH","2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS","AS","2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC","AC","2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD","AD"};
	static String play[]={"Fold","Raise","Raise","Bet","Bet","Show"};
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		// TODO Auto-generated method stub
		
		
        System.out.println("Welcome to Teenpatti!!!");
        System.out.println("Do you want to play? Y/N");
		char choicee=(char) y.read();
		// System.out.println("You currently have Rs. 1000 in your hand.");
        while(choicee=='Y'||choicee=='y')
        {
			cc=0;
			gameover=0;
        	System.out.println("Let's play!!!");
        	System.out.println("Shuffling cards...");
        	shuffle(card);
			waitfr(500);
        	System.out.println("Handing out the cards....");
			waitfr(500);
			System.out.println("You have received a card:"+card[0]);
			waitfr(1000);
			System.out.println("The computer receives a card:# ");
			waitfr(1000);
			System.out.println("You have received a card:"+card[0]+" "+card[2]);
			waitfr(1000);
			System.out.println("The computer receives a card:# # ");
			waitfr(1000);
			System.out.println("You have received a card:"+card[0]+" "+card[2]+" "+card[4]);
			waitfr(1000);
			System.out.println("The computer receives a card:# # #");
			waitfr(1000);
        	System.out.println("Your cards are as follows:");
        	System.out.println(card[0]+" "+card[2]+" "+card[4]);
			waitfr(300);
        	System.out.println("Computer has received his hand...");
        	resusr=checkhnd(card[0],card[2],card[4]);
        	rescmp=checkhnd(card[1],card[3],card[5]);
			int flag=0;
			int count=0;
			
			totbet=0;
			bet=100;
			int amt=0;
			waitfr(500);
			System.out.println("Let's begin... You can choose to bet, raise or fold!!!");
			System.out.println("Round 1");
			waitfr(200);
			System.out.println("The current bet is Rs. 100");
			//System.out.println("You have Rs. "+startamt);
			System.out.println("What do you want to do?");
			System.out.println("1. Bet\n2. Raise\n3. Show\n4. Fold");
			int choice=0;
			try
			{
				choice=Integer.parseInt(y.readLine());
			}
			catch(Exception e)
			{
			}
			while(gameover!=1)
			{
				if(choice==0)
					choice=Integer.parseInt(y.readLine());
				if(choice==1)
					lebet(bet);
				else if(choice==2)
				{
					System.out.println("By how much do you want to raise the bet?");
					amt=Integer.parseInt(y.readLine());
					totbet+=bet+amt;
					bet+=amt;
					System.out.println("The bet has now become Rs. "+bet);
				}
				else if(choice==3)
				{	
					choice=show(bet);
					gameover=1;
				}
				else
				{
					// if(totbet==0)
						// totbet=bet;
					System.out.println("You have folded cards.");
					System.out.println("Computer wins Rs. "+totbet);
					gameover=1;
					flag=1;
				}
				
				//Computer's Turn
				if(gameover!=1)
				{
					if(choice==1||choice==2)
					{
						count++;
						shuffle(play);
						Random rn=new Random();
						int index=rn.nextInt(play.length);
				
						//int index=1;
						System.out.println("The computer chose to "+play[index]);
						if(play[index].equalsIgnoreCase("bet")==true)
						{
							System.out.println("The computer has bet Rs. "+bet);
							lebet(bet);
						}
						else if(play[index].equalsIgnoreCase("fold")==true)
						{
							System.out.println("Computer has folded cards...");
							System.out.println("You win Rs. "+totbet);
							// startamt+=totbet;
							flag=1;
							gameover=1;
						}
						else if(play[index].equalsIgnoreCase("show")==true)
						{
							showcmp(bet);
							gameover=1;
						}
						else if(play[index].equalsIgnoreCase("raise")==true)
						{
							while(amt<10)
								amt=rn.nextInt(100);
							amt=amt/10;
							amt*=10;
							System.out.println("The computer has raised the bet by Rs."+amt);
							totbet+=bet+amt;
							bet+=amt;
							System.out.println("The bet amount has become Rs."+bet);
						}
					}	

					//cmpmind();
				}
				if(choice==3)
					gameover=1;
				if(gameover!=1)
				{
					System.out.println("What would you like to do now?");
					System.out.println("1. Bet\n2. Raise\n3. Show\n4. Fold");
					try
					{
						choice=Integer.parseInt(y.readLine());
					}
					catch(Exception e)
					{
					}
				}
			}
				
			if(flag==0&&gameover==0)
			{	
				result(resusr,rescmp);
			}
			System.out.println("Do you want to play? Y/N");
			try
			{
				choicee=(char)y.read();
			}
			catch(Exception e)
			{
			}
		}
		System.out.println("Come again!!");
	}
	
    public static void shuffle(String cc[])
    {
		Random rn=new Random();
        for(int i=0;i<cc.length;i++)
        {
            int rr=(int)rn.nextInt(i+1);
            String tmp=cc[i];
            cc[i]=cc[rr];
            cc[rr]=tmp;
			
        }
    }
    public static int checkhnd(String crd1,String crd2,String crd3)
    {
    	
    	int crd1num=0,crd2num=0,crd3num=0;
    	char crd1suit=' ',crd2suit=' ',crd3suit=' ';
    	int res=0;
    	crd1num=assignval(crd1.charAt(0));
    	crd2num=assignval(crd2.charAt(0));
    	crd3num=assignval(crd3.charAt(0));
		if(crd1.charAt(0)=='1'&&crd1.charAt(1)=='0')
		{
			crd1num=10;
			crd1suit=crd1.charAt(2);
		}
		if(crd2.charAt(0)=='1'&&crd2.charAt(1)=='0')
		{
			crd2num=10;
			crd2suit=crd2.charAt(2);
		}
		if(crd3.charAt(0)=='1'&&crd3.charAt(1)=='0')
		{
			crd3num=10;
			crd3suit=crd3.charAt(2);
		}
    	if(crd1suit==' ')
			crd1suit=crd1.charAt(1);
		if(crd2suit==' ')
			crd2suit=crd2.charAt(1);
    	if(crd3suit==' ')
			crd3suit=crd3.charAt(1);
    	int arr[]={crd1num,crd2num,crd3num};
    	Arrays.sort(arr);
    	if(crd1num==crd2num&&crd1num==crd3num)
    		res=5;
    	else if(crd1suit==crd2suit&&crd1suit==crd3suit)
    	{
    		if(arr[0]+1==arr[1]&&arr[1]+1==arr[2])
    			res=4;
    		else
    			res=2;
    	}
    	else if(arr[0]+1==arr[1]&&arr[1]+1==arr[2])
			res=3;
    	else if(crd1num==crd2num||crd2num==crd3num||crd3num==crd1num)
    		res=1;
    	else
    		res=0;
    	return res;
    }
    
    
    public static int findhigh(String crd1,String crd2,String crd3)
    {
    	int val=0;
    	int crd1num=assignval(crd1.charAt(0));
    	int crd2num=assignval(crd2.charAt(0));
    	int crd3num=assignval(crd3.charAt(0));
		if(crd1.charAt(0)=='1'&&crd1.charAt(1)=='0')
		{
			crd1num=10;
			
		}
		if(crd2.charAt(0)=='1'&&crd2.charAt(1)=='0')
		{
			crd2num=10;
			
		}
		if(crd3.charAt(0)=='1'&&crd3.charAt(1)=='0')
		{
			crd3num=10;
			
		} 	
		
		if(crd1num==1)
			crd1num=14;
		if(crd2num==1)
			crd2num=14;
		if(crd3num==1)
			crd3num=14;
    	if(crd1num>crd2num&&crd1num>crd3num)
    		val=crd1num;
    	else if(crd2num>crd3num)
    		val=crd2num;
    	else
    		val=crd3num;
    	return val;
    }
	
	public static int assignval(int val)
	{
		int res=0;
		if(val==65)
			res=1;
		else if(val==74)
			res=11;
		else if(val==75)
			res=13;
		else if(val==81)
			res=12;
		else
			res=val-48;
		return res;
	}
	
	public static void lebet(int amt)throws IOException
	{
		totbet+=amt;
		// if(amt<startamt)
			// startamt-=amt;
		// else
			// startamt=0;
		System.out.println("The bet has now become Rs. "+totbet);
	}
	
		
	public static void waitfr(int time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch(Exception e)
		{
		}
	}
	
	public static void result(int usr,int cmp)
	{
		System.out.println("The cards are as follows:");
		System.out.println("User's hands:"+card[0]+" "+card[2]+" "+card[4]);
		System.out.println("Computer's hands:"+card[1]+" "+card[3]+" "+card[5]);
		if(usr>cmp)
		{
			System.out.println("User wins on condition: "+hands[usr]);
			// startamt+=totbet;
			System.out.println("User wins Rs."+totbet);
			
			
		}
		else if(cmp>usr)
		{
			System.out.println("Computer wins on condition: "+hands[cmp]);
			System.out.println("Computer wins Rs."+totbet);
			
		}
		else
		{
			if((usr==0&&cmp==0)||(usr==3&&cmp==3)||(usr==4&&cmp==4)||(usr==5&&cmp==5))
			{
				int num1=findhigh(card[0],card[2],card[4]);
				int num2=findhigh(card[1],card[3],card[5]);
				if(num1>num2)
				{
					System.out.println("User wins on condition: "+hands[usr]);
					System.out.println("User wins Rs."+totbet);
					// startamt+=totbet;
				}
				else if(num2>num1)
				{
					System.out.println("Computer wins on condition: "+hands[cmp]);
					System.out.println("Computer wins Rs."+totbet);
				}
				else
				{
					System.out.println("It's a tie....");
					System.out.println("The money is divided...");
					System.out.println("User gets Rs."+(totbet/2));
					System.out.println("Computer gets Rs."+(totbet/2));
					// startamt+=(totbet/2);
				}
			}
			else if(usr==1&&cmp==1)
			{
				int usrcrd1=convert(card[0]);
				int usrcrd2=convert(card[2]);
				int usrcrd3=convert(card[4]);
				int cmpcrd1=convert(card[1]);
				int cmpcrd2=convert(card[3]);
				int cmpcrd3=convert(card[5]);
				int userc=0,cmpc=0;
				if(usrcrd1==usrcrd2||usrcrd1==usrcrd3)
					userc=usrcrd1;
				else if(usrcrd2==usrcrd3)
					userc=usrcrd3;
				if(cmpcrd1==cmpcrd2||cmpcrd1==cmpcrd3)
					cmpc=cmpcrd1;
				else if(cmpcrd2==cmpcrd3)
					cmpc=cmpcrd3;
				
				if(userc>cmpc)
				{
					System.out.println("User wins on condition: "+hands[usr]);
					System.out.println("User wins Rs."+totbet);
					// startamt+=totbet;
				}
				else if(cmpc>userc)
				{
					System.out.println("Computer wins on condition: "+hands[cmp]);
					System.out.println("Computer wins Rs."+totbet);
				}
				else
				{
					System.out.println("It's a tie...");
					System.out.println("The money is divided...");
					System.out.println("User gets Rs."+(totbet/2));
					System.out.println("Computer gets Rs."+(totbet/2));
					// startamt+=(totbet/2);
				}
			}
			else
			{
				System.out.println("It's a tie...");
				System.out.println("The money is divided...");
				System.out.println("User gets Rs."+(totbet/2));
				System.out.println("Computer gets Rs."+(totbet/2));
			}
				
		}
	}
	
	public static int show(int amt)
	{
		int choice=0;
		System.out.println("To show, you need to bet Rs."+(amt*2));
		System.out.println("Are you sure? Y/N");
		char cc=' ';
		try
		{
			cc=(char)y.read();
		}
		catch(Exception e)
		{
		}
		if(cc=='Y'||cc=='y')
		{
			totbet+=(amt*2);
			result(resusr,rescmp);
		}
		else
		{
			System.out.println("Please give your choice again.");
			try
			{
				choice=Integer.parseInt(y.readLine());
			}
			catch(Exception e)
			{
			}
		}
		return choice;
	}
	
	public static void showcmp(int amt)
	{
		System.out.println("The computer has chosen to Show");
		totbet+=(amt*2);
		gameover=1;
		result(resusr,rescmp);
	}
	
	public static void cmpmind()
	{
		int amt=0;
		Random rn=new Random();
		if(rescmp==5)
		{
			if(cc<=6)
			{
				int choi=rn.nextInt(10);
				if(choi>=6)
				{
					// Remember you can never fold.....
					while(amt<10)
						amt=rn.nextInt(100);
					amt=amt/10;
					amt*=10;
					System.out.println("The computer has raised the bet by Rs."+amt);
					totbet+=bet+amt;
					bet+=amt;
					System.out.println("The bet amount has become Rs."+bet);
					cc++;
				}
				else if(choi<=5)
				{
					System.out.println("The computer has bet Rs. "+bet);
					try
					{
						lebet(bet);
					}
					catch(Exception e)
					{
					}
					cc++;
				}
			}
			else
			{
				int ern=rn.nextInt();
				if(ern<0.5)
					showcmp(bet);
				else
				{
					int choi=rn.nextInt(10);
					if(choi>=6)
					{
						// Remember you can never fold.....
						while(amt<10)
							amt=rn.nextInt(100);
						amt=amt/10;
						amt*=10;
						System.out.println("The computer has raised the bet by Rs."+amt);
						totbet+=bet+amt;
						bet+=amt;
						System.out.println("The bet amount has become Rs."+bet);
						cc++;
					}
					else if(choi<=5)
					{
						System.out.println("The computer has bet Rs. "+bet);
						try
						{
							lebet(bet);
						}
						catch(Exception e)
						{
							
						}
						cc++;
					}
				}
			}
		}
		else if(rescmp==4)
		{
			
		}
	}
	public static int convert(String crd)
	{
		String str=crd.substring(0,crd.length()-1);
		int val=assignval(Integer.parseInt(str));
		return val;
	}
}
