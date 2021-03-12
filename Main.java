package project1;
/********************************************************************************************************* 
 * CLASS: Main (Main.java) 
 * 
 * DESCRIPTION 
 * A description of the contents of this file. 
 * 
 * COURSE AND PROJECT INFORMATION 
 * CSE205 Object Oriented Programming and Data Structures, semester and year 
 * Project Number: p1
 *
 * GROUP INFORMATION  
 * AUTHOR 1: Brandon Murata, bmurata1, bmurata1@asu.edu
 * AUTHOR 2: Brandon Billmeyer, bbillmey , bbillmey@asu.edu
 * AUTHOR 3: Delaney Claussen , djclaus1,djclaus1@asu.edu
 ********************************************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main{
    static int RUNS_UP = 1;
    static int RUNS_DN = -1;
    
    public static void main(String[] pArgs) {
    Main mainObject = new Main();
    mainObject.run();    
}   
    private void run()
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println(list);

    	try
    	{
    		list = new ArrayList<Integer>(readInputFile("p1-in.txt"));  
    		System.out.println(list);
    	}
    	catch(FileNotFoundException ex)
    	{
    		System.out.println("File does not exist, program will terminate");
    		System.exit(-100);
    	}

        ArrayList <Integer> listRunsUpCount = new ArrayList<>(findRuns(list, RUNS_UP));
        ArrayList <Integer> listRunsDnCount = new ArrayList<>(findRuns(list, RUNS_DN));
        ArrayList <Integer> listRunsCount = new ArrayList<>(mergeLists(listRunsUpCount, listRunsDnCount));
    	try
    	{
    		writeOutputFile("p1-runs.txt", listRunsCount);
    		System.out.println(listRunsDnCount);
    	}
    	catch(FileNotFoundException ex)
    	{
    		System.out.println("File does not exist, program will terminate");
    		System.exit(-200);
    	}
/*
*-- It is imperative that run() catches and handles the FileNotFoundExceptions that may get thrown in readInputFile() and
*-- writeOutputFile() when the input and output files cannot be opened for reading and writing.
*Declare ArrayList of Integers list
*        Try
*                list  readInputFile("p1-in.txt")
*        Catch (pException : FileNotFoundException)
*                Display error message as described in SWR 4
*                Terminate the program with exit code of -100
*        End
*        Declare and create an ArrayList of Integers named listRunsUpCount
*        Declare and create an ArrayList of Integers named listRunsDnCount
*        listRunsUpCount  findRuns(list, RUNS_UP)
*        listRunsDnCount  findRuns(list, RUNS_DN)
*        Declare ArrayList of Integers listRunsCount  mergeLists(listRunsUpCount, listRunsDnCount)
*        Try
*                writeOutputFile("p1-runs.txt", listRunsCount)
*        Catch (pException : FileNotFoundException)
*                Display error message as described in SWR 5
*                Terminate the program with exit code of -200 
*/

    }
    
public ArrayList<Integer> findRuns(ArrayList<Integer> pList, int pDir)
{
	ArrayList <Integer> listRunsCount = arrayListCreate(pList.size(),0);
	System.out.println("Line 88" + listRunsCount);
	System.out.println("pList size is" + pList.size());
	System.out.println("pDir ="+ pDir);
	int i=0;
	int k=0;
	while (i<pList.size()-1)
	{
		//if(pDir==RUNS_UP && pList.get(i) <= pList.get(i+1))
		if ((pDir > 0) && pList.get(i) <= pList.get(i + 1))
		{
			k++;
		}
		//else if(pDir==RUNS_DN && pList.get(i) >= pList.get(i+1))
		else if ((pDir < 0) && pList.get(i) >= pList.get(i + 1))
		{
			k++;
		}
		else
		{
			if(k!=0)
			{
				Integer value = listRunsCount.get(k); //gets the element value
				value = value + 1; //increments the element
				listRunsCount.set(k, value); // replaces the element w/ incremented value
				//listRunsCount.set(k, listRunsCount.get(k+1));
				k=0;
			}
		}
		i++;
	}
	if(k!=0)
	{
		//listRunsCount.set(k, listRunsCount.get(k+1));
		Integer value = listRunsCount.get(k); //gets the element value
		value = value + 1; //increments the element
		listRunsCount.set(k, value);
	}
/*
-- pList is the ArrayList of Integers that were read from "p1-in.txt". pDir is an int and is either RUNS_UP or RUNS_DN
-- which specifies in this method whether we are counting the number of runs up or runs down.
Method findRuns(pList : ArrayList of Integers, pDir : int) ReturnsArrayList of Integers
        listRunsCount arrayListCreate(pList.size(), 0) -- Create ArrayList of proper size and init each element to 0
        Declare int variables initialized to 0: i  0, k  0  -- the left arrow represents the assignment operator
        While i < pList.size() - 1 Do
            If pDir is RUNS_UP and pList element at i is  pList element at i + 1 Then
                    Increment k
            ElseIf pDir is RUNS_DN and pList element at i is   pList element at i + 1 Then
                    Increment k
            Else
                    If k does not equal 0 Then
                        Increment the element at index k of listRunsCount
                                k  0
                    End if
            End If
            Increment i
        End While
        If k does not equal 0 Then
            Increment the element at index k of listRunsCount
        End If
        Return listRunsCount
End Method findRuns
*/
	return listRunsCount;
}

public ArrayList<Integer> mergeLists(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount)
{
        int i;
	ArrayList<Integer> listRunsCount = new ArrayList <Integer>(arrayListCreate(pListRunsUpCount.size(),0));
	for (i=0; i <= pListRunsUpCount.size()-1; i++)
	{
		listRunsCount.set(i,pListRunsUpCount.get(i) + pListRunsDnCount.get(i));
	}
/*
Method mergeLists(pListRunsUpCount : ArrayList of Integers, pListRunsDnCount : ArrayList of Integers)
Returns ArrayList of Integers
        listRunsCount  arrayListCreate(pListRunsUpCount.size(), 0)
        For i  0 to pListRunsUpCount.size() - 1 Do
                Set element i of listRunsCount to the sum of the elements at i in pListRunsUpCount and pListRunsDnCount
        End For
        Return listRunsCount
End Method mergeLists
*/
return listRunsCount;
}

public ArrayList<Integer> arrayListCreate(int pSize, int pInitValue)
{
	ArrayList <Integer> list = new ArrayList <>();
	if (pSize == 0) 
	{
		return null;
	}
	for (int s=0; s<pSize; s++)
	{
		list.add(pInitValue);
	}

/*                               
Method arrayListCreate(pSize : int; pInitValue : int) Returns ArrayList of Integers
        Declare and create an ArrayList of Integers named list
        Write a for loop that iterates pSize times and each time call add(pInitValue) to list
        Return list
End Method arrayListCreate
*/
return list;
}

public void writeOutputFile(String pFilename, ArrayList<Integer> pListRuns)throws FileNotFoundException
{

	PrintWriter out = new PrintWriter(new File(pFilename));
	//File file = new File(pFilename);
	//int sumpListRuns = 0;
	//for (int index =0; index < pList.size(); index++)
	//for (int i:pListRuns)
		//System.out.println("Line 187 i is " + i);
		//System.out.println("Line 187 pListRuns is" + pListRuns);
	{
		//sumpListRuns += pList.get(index);
		//sumpListRuns +=1;
		//System.out.println("Line 190 is " + sumpListRuns);
	}
	System.out.println("runs_total: " + sumpListRuns(pListRuns));
	out.println("runs_total: " + sumpListRuns(pListRuns));
	
	for(int k =1; k<pListRuns.size()-1; k++)
	{
		System.out.println("runs_" + k + ": " + pListRuns.get(k));
		out.println("runs_" + k + ": " + pListRuns.get(k));
	}
	out.close();
	
/*                                     
-- Make sure to throw the FileNotFoundException that is raised when the output file cannot be opened for writing.
Method writeOutputFile(pFilename : String; pListRuns : ArrayList of Integers) Returns Nothing
        Throws FileNotFoundExceptionout    
                out  open pFilename for writing
                out.println("runs_total: ", the-sum-of-pListRuns)
        For k  1 to pListRuns.size() - 1 Do 
                out.println("runs_", k, " ", the element at index k of pListRuns)
        End For
        Close out
End Method output
*/
}
public Integer sumpListRuns(ArrayList<Integer>pList)
{
	Integer sumpListRuns =0;
	for (int i=0; i<pList.size(); i++)
	{
		sumpListRuns += pList.get(i);
	}
	return sumpListRuns;
}
public ArrayList<Integer> readInputFile(String pFilename)throws FileNotFoundException
{
	Scanner in = new Scanner(new File(pFilename));
	System.out.println(in);
	ArrayList<Integer> list = new ArrayList<>();
	while (in.hasNextInt())
	{
		list.add(in.nextInt());
	}
	in.close();
/*                                        
-- Make sure to throw the FileNotFoundException that is raised when the input file cannot be opened for reading.
Method readInputFile(pFilename : String; pListRuns : ArrayList of Integers) Returns ArrayList of Integers
Throws FileNotFoundExceptionin 
        in  open pFilename for reading
        Declare and create an ArrayList of Integers named list
        While there is more data to be read from in Do
                Read the next integer and add it to list
        End While
        Close in
        Return list
End Method readInputFile
*/
return list;
}
}




