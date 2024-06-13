
import java.io.*;
import java.net.*;
public class snwc
{
public static void main(String args[])
{
try
{
System.out.println("============== CLIENT ==============");
String frame = null;
String ack = null;
//1. creating a socket to connect to the server
Socket con = new Socket("localhost",123);
System.out.println("Connected with server - IP: "+
con.getInetAddress().getHostAddress());
//2. set Output and input streams
ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream()); 
ObjectInputStream in = new ObjectInputStream(con.getInputStream()); 
frame = "program";
//3. send the frame length to server to control loop operation in server 
out.writeObject(Integer.toString(frame.length()));
//4. frame sending and acknowledgment receiving process 
String subframe = null;
int frameno = 0;
for(int i=0; i< frame.length();i++)
{
subframe = frame.substring(i,i+1); 
out.writeObject("frame" + frameno + " : "+ subframe );
System.out.println("frame" + frameno + " Sent to Server : " + subframe); 
if(frameno == 0)
frameno = 1;
else
frameno = 0;
ack = (String)in.readObject();
System.out.println("Ack received from Server : " + ack);
}
//5. Close all objects
in.close();
out.close();
con.close();
}
catch(Exception e)
{
System.out.println("socket error:"+e);
}
}
}

