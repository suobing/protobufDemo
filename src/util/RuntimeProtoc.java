package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RuntimeProtoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userdir = System.getProperty("user.dir");
//		String currentDir= RuntimeProtoc.class.getResource("/").getPath();
		String[] commands = {"cmd","/c","protoc.exe","-I="+userdir+"/proto","--java_out="+userdir+"/src/",userdir+"/proto/testEmbed.proto"};
//		String[] commands = {"cmd","/c","protoc.exe","--java_out="+userdir+"/src/sample/demo/",userdir+"/proto/test.proto"};
//		String commands = " protoc.exe"+" --java_out="+userdir+"/src/sample/demo/ "+userdir+"/proto/test.proto";
		try {
			Process child = Runtime.getRuntime().exec(commands);
			child.waitFor();
			System.out.println("exit value: "+child.exitValue());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(child.getErrorStream()));
			String line="";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
