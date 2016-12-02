package fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDfsTest {

	@Test
	public void testUpload() throws FileNotFoundException, IOException, MyException{
		
		ClientGlobal.init("D:\\workspace\\heimataotao\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
		//3.trackerClient
		TrackerClient client = new TrackerClient();
		//4.trackerser
		TrackerServer server = client.getConnection();
		//5.storageserver
		StorageServer storageServer = null;
		//6
		StorageClient storageClient = new StorageClient(server,storageServer);
		String[] str = storageClient.upload_file("C:\\Users\\admin\\Desktop\\show_fengjingta_281299_11.jpg", "jpg", null);
		
		for (String string : str) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testDFSClient() throws Exception{
		FastDFSClient client = new FastDFSClient("D:\\workspace\\heimataotao\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
		String str = client.uploadFile("C:\\Users\\admin\\Desktop\\show_fengjingta_281299_11.jpg","jpg");
		System.out.println(str);
	}
	@Test
	public void testTest(){
		System.out.println("aas");
	}
	
	
}
